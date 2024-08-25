package kr.codeback.exception.member;

import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends MemberException {
	public MemberNotFoundException(HttpStatus status, String message) {
		super(status, message);
	}
}
