package com.test.nutri.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="member")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer seq;
	
	@Column(nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String nickname;
	
	@Column(nullable = false)
	private String dob;
	
	@Column(nullable = false)
	private String gender;
	
	@Column(nullable = false)
	private String telephone;
	
	@Column(nullable = true)
	private String zipcode;
	
	@Column(nullable = true)
	private String address;
	
	@Column(nullable = true)
	private String addressDetail;
	
	@Column(nullable = true)
	private String addressExtra;
	
	@Column(nullable = false)
	private String status;
	
	@Column(nullable = false, updatable = false)
	private LocalDateTime createTime;
	
	/**
	 * 엔티티 생성시 호출되는 메서드, 
	 * 엔티티 생성시각(createTime)과 상태(status)를 초기화
	 *
	 * @author Sangsoo Jeon
	 */
	@PrePersist
	protected void onCreate() {
		this.createTime = LocalDateTime.now();
		this.status = "1";
	}
	
	@Column(nullable = true)
	private LocalDateTime updateTime;

	
}
