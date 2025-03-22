package com.test.nutri.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.test.nutri.api.NewsAPI;
import com.test.nutri.model.NewsDTO;
import com.test.nutri.model.NewsListDTO;
import com.test.nutri.repository.NewsQueryDSLRepository;
import com.test.nutri.repository.NewsRepository;

import lombok.RequiredArgsConstructor;

/**
 * News Service Class
 * 
 * 이 클래스는 최신 뉴스 데이터를 외부 뉴스 API에서 가져오거나, 이미 DB에 저장된 뉴스 데이터를 관리하는 기능을 제공합니다. 
 * 뉴스 데이터를 업데이트하거나, DB에서 페이징 처리된 뉴스를 조회하는 메서드를 제공합니다.
 * 
 * @author chimy2
 */
@Service
@RequiredArgsConstructor
public class NewsService {

    /**
     * 외부 뉴스 API와 통신하기 위한 뉴스 API 객체.
     * 
     * 뉴스 데이터를 가져오기 위해 사용됩니다. 이 객체를 통해 뉴스 API의 각종 엔드포인트에 접근합니다.
     */
	private final NewsAPI newsAPI;

    /**
     * 뉴스 데이터 저장소.
     * 
     * DB에 뉴스를 저장하거나 조회할 때 사용됩니다. 
     * 뉴스 정보를 저장, 삭제, 수정하는 기본적인 CRUD 작업을 처리하는 역할을 합니다.
     */
	private final NewsRepository newsRepository;
	
	/**
     * 뉴스 쿼리 DSL 저장소.
     * 
     * 뉴스 데이터를 조건에 맞게 조회하기 위해 QueryDSL을 사용한 Repository입니다.
     * 이 레포지토리를 통해 복잡한 조건을 만족하는 뉴스 데이터들을 쿼리할 수 있습니다.
     */
	private final NewsQueryDSLRepository newsQueryDSLRepository;
	
	public void updateNews() {
//		최신 뉴스부터 최대 2024년 11월 뉴스까지 DB에 저장
		if (newsRepository.count() > 0) {
			updateLatestNewsBinary();
		} else {
			insertAllNews();
		}
	}

    /**
     * 주어진 오프셋과 리밋에 따라 뉴스 목록을 반환합니다.
     * 
     * 뉴스 목록을 반환하기 전에 DB에 저장된 뉴스 데이터가 최신 상태인지 확인하고, 
     * 최신 뉴스가 필요한 경우 API를 통해 뉴스를 가져와 DB에 저장합니다.
     * 
     * @param offset 데이터 페이징을 위한 오프셋
     * @param limit 페이징된 뉴스의 최대 개수
     * @return 뉴스 목록
     */
	public List<NewsDTO> getNewsList(Integer offset, Integer limit) {

		return newsQueryDSLRepository.findAllPagenation(offset, limit).stream()
				.map(news -> NewsDTO.builder().title(news.getTitle()).originallink(news.getOriginalLink())
						.link(news.getLink()).description(news.getDescription()).pubDate(news.getRegDate()).build())
				.toList();
	}

    /**
     * 최신 뉴스 데이터를 외부 뉴스 API에서 가져와 DB에 저장합니다.
     * 
     * 뉴스 API에서 데이터를 가져와 뉴스 제목, 링크, 날짜 등을 기준으로 이미 DB에 저장된 뉴스가 있는지 확인하고,
     * 중복되지 않는 뉴스만 DB에 저장합니다.
     */
	void updateLatestNews() {

		int start = 1;
		boolean isFind = false;
		
		NewsListDTO list;
		NewsDTO news;

		while (start > 0) {
			list = newsAPI.getNewsList(start);
			
			if(isFind) {
				for (int i = list.getItems().size() - 1; i >= 0; i--) {
					
					news = list.getItems().get(i);
					newsRepository.save(news.toEntity());
				}
				start--;
				return;
			} else if(!isFind && newsQueryDSLRepository.countByTitleAndLinkAndRegDate(list.getItems().getLast()) == 0) {
				start++;
				return;
			}
			
			for (int i = list.getItems().size() - 1; i >= 0; i--) {
				
				news = list.getItems().get(i);
				
				if (newsQueryDSLRepository.countByTitleAndLinkAndRegDate(news) == 0) {
					newsRepository.save(news.toEntity());
				}
			}
			isFind = true;
			start--;
		}
	}

    /**
     * DB에 저장된 뉴스가 없을 경우, 외부 API에서 모든 뉴스 데이터를 가져와 DB에 삽입합니다.
     * 
     * 2024년 11월 1일 이후의 뉴스부터 가져와 저장하며, 날짜를 기준으로 구분하여 뉴스 데이터를 DB에 삽입합니다.
     */
	public void insertAllNews() {

		int start = 1;
		boolean isFind = false;
		LocalDateTime baseDate = LocalDateTime.of(2024, 11, 1, 0, 0);

		NewsListDTO list;
		NewsDTO news;

		while (start > 0) {
			list = newsAPI.getNewsList(start);

//			API 검색 시작 위치 제한 때문에 조건 추가
			if (list.getItems().getLast().getPubDate().compareTo(baseDate) <= 0 || start == 10) {
				for (int i = list.getItems().size() - 1; i >= 0; i--) {
					
					news = list.getItems().get(i);

					if (news.getPubDate().compareTo(baseDate) >= 0) {
						newsRepository.save(news.toEntity());
					}
				}
				start--;
				isFind = true;
			} else if (isFind) {
				for (int i = list.getItems().size() - 1; i >= 0; i--) {
					
					news = list.getItems().get(i);
					newsRepository.save(news.toEntity());
				}
				start--;
			} else {
				start++;
			}

		}
	}

    /**
     * 뉴스의 총 개수를 반환합니다.
     * 
     * @return 뉴스의 총 개수
     */
	public int getCount() {
		return (int) newsRepository.count();
	}

    /**
     * 최신 뉴스 데이터를 이진 검색을 통해 API에서 가져와 DB에 저장합니다.
     * 
     * 이 메서드는 최신 뉴스만 삽입하도록 이진 검색 방식을 사용하여 뉴스 데이터를 추가합니다.
     */
	void updateLatestNewsBinary() {


		int start = 1;
		boolean isFind = false;
		
		NewsListDTO list;
		NewsDTO news;

		while (start > 0) {
			list = newsAPI.getNewsList(start);
			
			if(isFind) {
				for (int i = list.getItems().size() - 1; i >= 0; i--) {
					
					news = list.getItems().get(i);
					newsRepository.save(news.toEntity());
				}
				start--;
				return;
			} else if(!isFind && newsQueryDSLRepository.countByTitleAndLinkAndRegDate(list.getItems().getLast()) == 0) {
				start++;
				return;
			}
			
			int findIndex = binarySearch(list.getItems());
			
			for (int i = findIndex - 1; i >= 0; i--) {
				
				news = list.getItems().get(i);
				
				newsRepository.save(news.toEntity());
			}
			isFind = true;
			start--;
		}
	}

    /**
     * 주어진 뉴스 리스트에서 뉴스의 중복 여부를 확인하기 위해 이진 검색을 수행합니다.
     * 
     * @param list 뉴스 DTO 리스트
     * @return 중복되지 않는 뉴스의 인덱스
     */
	public int binarySearch(List<NewsDTO> list) {
		int left = 0;
		int right = list.size() - 1;
		int mid = (left + right) / 2;
		
		while(left < right) {
			mid = (left + right) / 2;
			if(newsQueryDSLRepository.countByTitleAndLinkAndRegDate(list.get(mid)) == 0) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		
		return left;
	}
}
