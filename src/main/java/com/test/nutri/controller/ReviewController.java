package com.test.nutri.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.test.nutri.entity.Review;
import com.test.nutri.entity.VwReview;
import com.test.nutri.model.CustomUserDetails;
import com.test.nutri.repository.ReviewQueryDSLRepository;
import com.test.nutri.service.ReviewService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * ReviewController는 리뷰와 관련된 모든 요청을 처리하는 컨트롤러 클래스입니다.
 * 리뷰 검색, 페이징 처리, 리뷰 작성, 수정, 삭제, 및 상세 조회와 같은 기능을 제공합니다.
 * 
 * 주요 역할
 * - 사용자가 입력한 키워드로 리뷰를 검색하고, 결과를 페이징 처리하여 반환
 * - 리뷰 작성 및 수정 페이지 제공
 * - 작성된 리뷰를 데이터베이스에 저장하고, 수정된 리뷰를 업데이트
 * - 리뷰 삭제 요청 처리
 * 
 * @author jiyun
 */
@Controller
@RequiredArgsConstructor
public class ReviewController {
	
	private final ReviewQueryDSLRepository reviewQueryDSLRepository;
	private final ReviewService reviewService;
   
	/**
	 * 리뷰 목록 페이지를 처리하는 메서드입니다.
	 * 리뷰 목록을 페이징 처리하여 반환하며, 사용자가 입력한 검색 키워드로 리뷰를 검색할 수도 있습니다.
	 *
	 * @param model - 화면에 전달할 데이터 모델
	 * @param keyword - 검색 키워드 (옵션, 없으면 전체 리뷰 조회)
	 * @param page - 현재 페이지 번호 (기본값: 1)
	 * @return - 리뷰 목록 페이지 ("page/review")
	 */
	@GetMapping("/review")
	public String review(Model model 
							, @RequestParam(name = "keyword", required = false) String keyword
		   					, @RequestParam(defaultValue = "1", name = "page") Integer page) {
		
		// 사용자가 입력한 keyword 확인
		System.out.println(">>>>>>>>> keyword: " + keyword);
		
		// 총 리뷰 개수 조회
		int count = reviewQueryDSLRepository.count(keyword);
		
		// 페이지 당 리뷰 개수 및 최대 페이지 개수 
		double maxListSize = 15;								//페이지 당 보여줄 최대 리뷰 수
		double maxPageSize = 15;								//페이지 블록 당 최대 페이지 수
		int pageCount = (int) Math.ceil(count / maxListSize); 	//전체 페이지 수
		int offset = (int) ((page - 1) * maxListSize);			// 데이터 조회 시작 위치 계산

		// DB에서 리뷰 검색
		List<VwReview> list = reviewQueryDSLRepository.search(offset, (int) maxPageSize, keyword);
		
        // 페이징 처리
		StringBuilder sb = new StringBuilder();
		int firstPage = (int) (Math.floor((page - 1) / maxPageSize) * maxPageSize + 1); //현재 페이징 블록의 첫번째 페이지
		int beforePage = (int) (firstPage - maxPageSize);								//이전 페이징 블록
		int nextPage = (int) (firstPage + maxPageSize);									//다음 페이징 블록
	
		// 이전 블록이 0 이하인 경우 1로 설정
		if (beforePage < 0) {
	   	 	beforePage = 1;			
	   	}
	
		// 다음 블록이 전체 페이지 수를 초과하면 마지막 페이지로 설정
		if (nextPage > pageCount) {	
			nextPage = pageCount;	
		}
		
		// 이전 페이지 링크 추가
		if (page > 1) {
			if (keyword != null && !keyword.trim().isEmpty()) { // 검색 키워드가 있는 경우
			     sb.append(String.format("<a href=\"/review?page=%d&keyword=%s\">&lt;</a>", beforePage, keyword));
			} else { // 검색 키워드가 없는 경우
				 sb.append(String.format("<a href=\"/review?page=%d\">&lt;</a>", beforePage));
			}
		}
		
		// 현재 블록 페이지 링크 추가
		for (int i = firstPage; i <= pageCount && i < firstPage + maxPageSize; i++) {
			if (page == i) {
				sb.append(String.format("<a href=\"/review?page=%d\" class=\"active\">%d</a>", i, i));
			} else {
				sb.append(String.format("<a href=\"/review?page=%d\">%d</a>", i, i));
			}
		}
		
		// 다음 페이지 링크 추가
		if (page < pageCount) { 
			if (keyword != null && !keyword.trim().isEmpty()) {
				sb.append(String.format("<a href=\"/review?page=%d&keyword=%s\">&gt;</a>", nextPage, keyword));
			} else {
				sb.append(String.format("<a href=\"/review?page=%d\">&gt;</a>", nextPage));
			}
		}

	    model.addAttribute("list", list);
	    model.addAttribute("page", sb.toString());
	    
	    return "page/review";
	}

	
	/**
	 * 리뷰 작성 페이지를 보여주는 메서드입니다.
	 * 현재 로그인된 사용자의 닉네임을 모델에 추가하여 사용자에게 리뷰 작성 페이지를 제공합니다.
	 * 인증되지 않은 사용자의 경우 로그인 페이지로 리다이렉트됩니다.
	 *
	 * @param model - 화면에 전달할 데이터 모델
	 * @return - 리뷰 작성 페이지 ("page/addReview")
	 */
	@GetMapping("/addReview")
	public String getAddReview(Model model) {
		
		// 현재 인증된 사용자 정보를 가져옴
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    Object principal = authentication.getPrincipal();

	    String nickname = null;  // nickname 저장할 변수

	    if (principal instanceof CustomUserDetails) {  
	    	CustomUserDetails userDetails = (CustomUserDetails) principal;
	        nickname = userDetails.getNickname();  	//사용자 nickname 가져옴
	        System.out.println("현재 로그인한 닉네임: " + nickname);  // 닉네임 출력
	    } else {
	        System.out.println("인증되지 않은 사용자입니다.");
	        return "redirect:/login";  // 인증되지 않은 사용자일 경우 로그인 페이지로 리다이렉트
	    }

	    // 모델에 nickname 추가
	    model.addAttribute("nickname", nickname);
	 
		return "page/addReview";
	}
	
	
	/**
	 * 사용자가 작성한 리뷰를 처리하고 저장하는 메서드입니다.
	 * 작성된 리뷰는 데이터베이스에 저장되며, 성공적으로 저장되면 리뷰 목록 페이지로 리다이렉트합니다.
	 * 인증되지 않은 사용자는 로그인 페이지로 리다이렉트됩니다.
	 *
	 * @param model - 화면에 전달할 데이터 모델
	 * @param response - HTTP 응답 객체
	 * @param title - 리뷰 제목
	 * @param category - 리뷰 카테고리
	 * @param name - 영양제명
	 * @param content - 리뷰 내용
	 * @param image - 첨부된 이미지 파일 (옵션)
	 * @return - 리뷰 목록 페이지로 리다이렉트 ("redirect:/review")
	 * @throws IOException - 이미지 파일 저장 중 발생할 수 있는 예외
	 */
	@PostMapping("/addReview")
    public String postAddReview(Model model, HttpServletResponse response
    							, @RequestParam("title") String title
    							, @RequestParam("category") String category
    							, @RequestParam("name") String name
    							, @RequestParam("content") String content 
    							, @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
	    
		
		// 현재 인증된 사용자 정보를 가져옴
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	Object principal = authentication.getPrincipal();
    	
    	Integer seq = null;  
    	if (principal instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) principal;
            seq = userDetails.getMember().getSeq();
            System.out.println("현재 회원 seq: " + seq);
        } else {
            System.out.println("인증되지 않은 사용자입니다.");
            response.sendRedirect("/login");
            return null;
        }
    	
	    
		// Review 객체 생성
        Review review = Review.builder()
                              .title(title)
                              .category(category)
                              .name(name)
                              .content(content)
                              .member_seq(seq)
                              .build();

        try {
            // Service 호출
            reviewService.saveReview(review, image);
            
        } catch (IOException e) {
        	
        	System.out.println(">>>>>>>>> 새 리뷰 작성에 실패했습니다.");
            e.printStackTrace();
            
            return "page/addReview"; // 에러 발생 시 다시 작성 페이지로 이동
        }	
		
        return "redirect:/review"; 
    }
	
	
	/**
	 * 선택한 리뷰의 상세 정보를 보여주는 메서드입니다.
	 * 리뷰 일련번호 (seq)에 해당하는 리뷰의 상세 정보를 조회하여 반환합니다.
	 * 리뷰가 존재하지 않으면, 리뷰 목록 페이지로 리다이렉트됩니다.
	 *
	 * @param model - 화면에 전달할 데이터 모델
	 * @param seq - 리뷰 일련번호
	 * @return - 리뷰 상세 페이지 ("page/viewReview")
	 */
	@GetMapping("/viewReview")
	public String viewReview(Model model, @RequestParam(value = "seq", required = false) Long seq) {
		
		if (seq == null) {
			return "redirect:/review";  
		}
		
		VwReview vwreview = reviewQueryDSLRepository.findReviewBySeq(seq);
		
		model.addAttribute("review", vwreview);
		
		return "page/viewReview";
	}
	
	
	/**
	 * 수정할 리뷰의 정보를 가져와 수정 페이지를 보여주는 메서드입니다.
	 * 해당 리뷰가 존재하지 않으면, 리뷰 목록 페이지로 리다이렉트됩니다.
	 *
	 * @param model - 화면에 전달할 데이터 모델
	 * @param seq - 수정할 리뷰의 일련번호
	 * @return - 리뷰 수정 페이지 (뷰 이름: "page/editReview")
	 */
	@GetMapping("/editReview")
	public String getEditReview(Model model, @RequestParam(value = "seq") Long seq) {
       
	   // seq를 기준으로 해당 리뷰 조회 
	   VwReview vwreview = reviewQueryDSLRepository.findReviewBySeq(seq);
	    
	   if (vwreview == null) {
	   
		    System.out.println(">>>>>>>>> " + seq + "번째 리뷰를 찾을 수 없습니다.");
	        return "redirect:/review";
	    }
	   
	   model.addAttribute("review", vwreview);
	   
	   return "page/editReview";
	}
	
   
	/**
	 * 수정된 리뷰 정보를 저장하는 메서드입니다.
	 * 리뷰가 성공적으로 수정되면, 해당 리뷰의 상세 페이지로 리다이렉트합니다.
	 * 수정 중 오류가 발생하면 오류 메시지를 출력하고 수정 페이지로 리다이렉트됩니다.
	 *
	 * @param model - 화면에 전달할 데이터 모델
	 * @param seq - 수정할 리뷰 일련번호
	 * @param category - 수정된 리뷰 카테고리
	 * @param name - 수정된 리뷰 영양제 이름
	 * @param title - 수정된 리뷰 제목
	 * @param content - 수정된 리뷰 내용
	 * @return - 수정된 리뷰의 상세 페이지로 리다이렉트 ("redirect:/viewReview?seq={seq}")
	 */
	@PostMapping("/editReview")
	public String postEditReview(Model model
									, @RequestParam("seq") Long seq
									, @RequestParam("category") String category
	    							, @RequestParam("name") String name
									, @RequestParam("title") String title
									, @RequestParam("content") String content) {
		
		try {
			//리뷰 수정 처리
			reviewQueryDSLRepository.updateReview(seq, category, name, title, content);
			System.out.println(">>>>>>>>> " + seq + "번째 리뷰가 성공적으로 수정되었습니다.");
			
			// 수정된 리뷰를 다시 조회하여 모델에 추가
	        VwReview updateReview = reviewQueryDSLRepository.findReviewBySeq(seq);
	        model.addAttribute("review", updateReview);
			
		} catch (Exception e) {
			
	        System.out.println(">>>>>>>>> " + seq + "번째 리뷰 수정 중 오류가 발생했습니다.");
	        e.printStackTrace();
	    }
		
		return "redirect:/viewReview?seq=" + seq;
	}
	
	
	/**
	 * 리뷰 삭제 요청을 처리하는 메서드입니다.
	 * 삭제할 리뷰의 일련번호를 받아 삭제 확인 페이지를 보여줍니다.
	 *
	 * @param model - 화면에 전달할 데이터 모델
	 * @param seq - 삭제할 리뷰 일련번호
	 * @return - 리뷰 삭제 확인 페이지 ("page/delReview")
	 */
	@GetMapping("/delReview") 
	public String getDeleteReview(Model model, @RequestParam("seq") Long seq) {
		
		model.addAttribute("seq", seq);
		
		return "page/delReview";
	}
	
	
	/**
	 * 리뷰를 삭제하는 메서드입니다.
	 * 리뷰 삭제가 성공적으로 처리되면 리뷰 목록 페이지로 리다이렉트됩니다.
	 *
	 * @param seq - 삭제할 리뷰의 일련번호
	 * @return - 리뷰 목록 페이지로 리다이렉트 ("redirect:/review")
	 */
	@PostMapping("/delReview")
	public String postDeleteReview(@RequestParam("seq") Long seq) {
	   
		try {
	        // DB에서 해당 리뷰 삭제
			reviewQueryDSLRepository.deleteReviewBySeq(seq);
	        System.out.println(">>>>>>>>> " + seq + "번째 리뷰가 성공적으로 삭제되었습니다.");
	        
	    } catch (Exception e) {
	    	
	    	System.out.println(">>>>>>>>> " + seq + "번째 리뷰 삭제 중 오류가 발생했습니다.");
	        e.printStackTrace();
	        
	    }
		
	    return "redirect:/review";
	
	}
      
}