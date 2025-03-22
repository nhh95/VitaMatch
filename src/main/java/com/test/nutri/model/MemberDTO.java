package com.test.nutri.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {
	
	private Integer seq;
	private String username;
	private String email;
	private String password;
	private String name;
	private String nickname;
	private String dob;
	private String gender;
	private String telephone;
	private String zipcode;
	private String address;
	private String addressDetail;
	private String addressExtra;
	private String status;
	private String createTime;
	private String updateTime;
	
	private String telephone1;
	private String telephone2;
	private String telephone3;
	
	public void combineTelephone() {
		if (telephone1 != null && telephone2 != null && telephone3 != null) {
			this.telephone = telephone1 + "-" + telephone2 + "-" + telephone3;
		}
	}
	
}