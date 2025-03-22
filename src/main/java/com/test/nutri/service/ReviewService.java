package com.test.nutri.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.test.nutri.entity.Review;
import com.test.nutri.entity.ReviewImage;
import com.test.nutri.repository.ReviewImageRepository;
import com.test.nutri.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

/**
 * ReviewService는 사용자가 작성한 리뷰와 관련된 비즈니스 로직을 처리하는 서비스 클래스입니다.
 * 리뷰와 리뷰 이미지를 저장하는 메서드를 제공하며, 파일 업로드를 처리합니다.
 * 
 * @author jiyun
 */
@Service
@RequiredArgsConstructor
public class ReviewService {

	private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;

    /**
     * 리뷰와 이미지를 저장하는 메서드입니다.
     * 
     * 리뷰 객체를 먼저 저장한 후, 해당 리뷰의 ID를 얻어옵니다.
     * 업로드된 이미지 파일이 있을 경우, 해당 이미지를 서버의 지정된 디렉토리에 저장하고,
     * 저장된 이미지 정보를 ReviewImage 객체에 매핑하여 데이터베이스에 저장합니다.
     * 
     * @param review 저장할 리뷰 객체
     * @param image 리뷰와 관련된 이미지 파일
     * @throws IOException 이미지 파일 처리 중 발생할 수 있는 예외
     */
    public void saveReview(Review review, MultipartFile image) throws IOException {
        
    	// 업로드 디렉토리 주소
        String uploadDir = "src/main/resources/static/img/review";

        // 1. Review 객체를 먼저 저장
        reviewRepository.save(review);

        // 2. 저장된 Review의 ID를 얻어옴
        Long reviewSeq = review.getSeq();

        // 3. 이미지 처리 및 저장
        if (image != null && !image.isEmpty()) {
            String originalFileName = image.getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "_" + originalFileName;

            File uploadDirPath = new File(uploadDir);
            if (!uploadDirPath.exists()) {
                uploadDirPath.mkdirs(); // 디렉토리가 없으면 생성
            }

            File destinationFile = new File(uploadDirPath, storedFileName);
            image.transferTo(destinationFile);

            // 4. ReviewImage 객체에 reviewSeq를 외래키로 설정
            ReviewImage reviewImage = ReviewImage.builder()
									    		 .review(review)  // review 객체를 설정
									             .image("/img/review/" + storedFileName)
									             .build();
            // 5. ReviewImage 저장
            reviewImageRepository.save(reviewImage);
        }
    }
}
