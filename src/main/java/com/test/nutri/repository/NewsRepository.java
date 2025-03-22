package com.test.nutri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.nutri.entity.News;

/**
 * News 엔티티에 대한 기본 CRUD 작업을 제공하는 JPA Repository 인터페이스.
 * 
 * @author chimy2
 */
public interface NewsRepository extends JpaRepository<News, Long> {

}
