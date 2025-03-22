package com.test.nutri.repository;

import static com.test.nutri.entity.QNews.news;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.test.nutri.entity.News;
import com.test.nutri.model.NewsDTO;

import lombok.RequiredArgsConstructor;

/**
 * 뉴스 데이터 조회를 위한 QueryDSL 기반의 Repository Class.
 * 
 * 이 클래스는 JPAQueryFactory를 사용하여 뉴스 데이터를 효율적으로 조회하기 위한 메서드를 제공합니다.
 * 주요 기능으로는 뉴스 목록을 페이징 처리하여 조회하거나, 특정 조건에 맞는 뉴스의 개수를 조회하는 기능 등이 있습니다.
 * 
 * @author chimy2
 */
@Repository
@RequiredArgsConstructor
public class NewsQueryDSLRepository {
	
	/**
     * JPAQueryFactory 객체.
     * 
     * QueryDSL을 사용하여 JPA 쿼리를 작성하고 실행하는 데 사용됩니다. 
     * 이 객체를 통해 DB에서 데이터를 효율적으로 조회할 수 있습니다.
     */
	private final JPAQueryFactory jpaQueryFactory;
	
	/**
     * 모든 뉴스 데이터를 조회합니다.
     * 
     * DB에서 뉴스 데이터를 전체 조회하여 반환합니다. 정렬이나 페이징 없이 모든 뉴스 데이터를 가져옵니다.
     * 
     * @return 전체 뉴스 목록
     */
	public List<News> findAll() {
		return jpaQueryFactory.selectFrom(news).fetch();
	}


    /**
     * 주어진 오프셋과 리밋을 기반으로 뉴스 데이터를 페이징하여 조회합니다.
     * 
     * @param offset 페이징을 위한 오프셋
     * @param limit 페이징을 위한 최대 개수
     * @return 페이징된 뉴스 목록
     */
	public List<News> findAllPagenation(Integer offset, Integer limit) {
		return jpaQueryFactory.selectFrom(news)
				.orderBy(news.regDate.desc())
				.offset(offset)
				.limit(limit)
				.fetch();
	}

	/**
     * 주어진 뉴스 DTO 객체의 제목, 링크, 날짜를 기준으로 중복된 뉴스가 존재하는지 확인하고 개수를 반환합니다.
     * 
     * @param dto 뉴스 데이터 전송 객체(NewsDTO)
     * @return 주어진 제목, 링크, 날짜에 해당하는 뉴스의 개수
     */
	public Long countByTitleAndLinkAndRegDate(NewsDTO dto) {
		return jpaQueryFactory.select(news.count()).from(news)
				.where(news.title.eq(dto.getTitle())
						.and(news.link.eq(dto.getLink())
								.and(news.regDate.eq(dto.getPubDate()))))
				.fetchOne();
	}
}
