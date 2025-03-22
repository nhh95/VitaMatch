package com.test.nutri.config;

import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

//root-context.xml 같은 설정 파일 역할 클래스
@Configuration
@RequiredArgsConstructor
public class QueryDSLConfig {

	private final EntityManager em;	
//	JPA에서 SQL을 실행하는 객체 > 평소에는 감춰져 있음
//	cf. Statement 객체, SqlSessionTemplate 객체
	
	@Bean
	public JPAQueryFactory jPAQueryFactory() {
		return new JPAQueryFactory(em);
	}
	
}
