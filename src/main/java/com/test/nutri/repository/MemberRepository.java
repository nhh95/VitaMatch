package com.test.nutri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.nutri.entity.Member;

/**
 * 회원 엔티티에 대한 데이터베이스 작업을 수행하는 리포지토리 인터페이스입니다.
 * 
 * @author Sangsoo Jeon
 */
public interface MemberRepository extends JpaRepository<Member, Integer> {

	/**
	 * 주어진 사용자 ID가 이미 존재하는지 확인합니다.
	 * 
	 * @param username 확인할 사용자 ID
	 * @return 이미 존재하는 경우 true, 그렇지 않으면 false
	 */
	boolean existsByUsername(String username);

	/**
	 * 주어진 사용자 ID에 대한 회원 정보를 조회합니다.
	 * 
	 * @param username 확인할 사용자 ID
	 * @return 회원 정보
	 */
	Member findByUsername(String username);

}
