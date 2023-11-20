package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MemberVO {

	private String userid;
	private String userpw;
	private String username;
	private String useremail;
	
	private Timestamp regdate;
	private Timestamp updatedate;
	
	// get,set 메서드 , toString 메서드 만들었는데 우리는 안만들거야 롬복이 알아서 만들었어
	
	
	
	
	
}
