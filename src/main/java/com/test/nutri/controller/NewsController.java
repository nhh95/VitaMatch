package com.test.nutri.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.nutri.model.NewsDTO;
import com.test.nutri.service.NewsService;

import lombok.RequiredArgsConstructor;

/**
 * 뉴스 관련 정보를 처리하는 컨트롤러 클래스입니다.
 * 뉴스 목록을 페이징하여 반환하고, 각 페이지의 뉴스 목록과 페이지 네비게이션을 생성합니다.
 * 
 * @author chimy2
 */
@Controller
@RequiredArgsConstructor
public class NewsController {

    /**
     * News Service 객체.
     * 뉴스와 관련된 비즈니스 로직을 처리하는 서비스 클래스입니다.
     */
	private final NewsService newsService;

    /**
     * 뉴스 목록을 반환하고, 페이징 처리를 위한 HTML을 생성하는 메소드입니다.
     *
     * @param model Spring MVC Model 객체로, 뷰에 데이터를 전달합니다.
     * @param page 클라이언트에서 요청한 페이지 번호. 기본값은 1입니다.
     * @return "page/news" 뷰 이름, 뉴스 목록과 페이지 네비게이션을 포함하는 HTML 페이지를 반환합니다.
     */
	@GetMapping("/news")
	public String news(Model model, @RequestParam(defaultValue = "1", name = "page") Integer page) {
		
		newsService.updateNews();
		
		int count = newsService.getCount();
		double maxListSize = 6;
		double maxPageSize = 10;
		int pageCount = (int) Math.ceil(count / maxListSize);
		int offset = (int) ((page - 1) * maxListSize);

		List<NewsDTO> list = newsService.getNewsList(offset, (int) maxListSize);

		StringBuilder sb = new StringBuilder();

		int firstPage = (int) (Math.floor((page - 1) / maxPageSize) * maxPageSize + 1);
		int beforePage = (int) (firstPage - maxPageSize);
		int nextPage = (int) (firstPage + maxPageSize);

		if (beforePage < 0) {
			beforePage = 1;
		}

		if (nextPage > pageCount) {
			nextPage = pageCount;
		}

		sb.append(String.format("<a href=\"/news?page=%d\">&lt;</a>", beforePage));

		for (int i = firstPage; i <= pageCount && i < firstPage + maxPageSize; i++) {
			if (page == i) {
				sb.append(String.format("<a href=\"/news?page=%d\" class=\"active\">%d</a>", i, i));
			} else {
				sb.append(String.format("<a href=\"/news?page=%d\">%d</a>", i, i));
			}
		}

		//퍼가요~♥
		
		sb.append(String.format("<a href=\"/news?page=%d\">&gt;</a>", nextPage));

		model.addAttribute("list", list);
		model.addAttribute("page", sb.toString());

		return "page/news";
	}
}
