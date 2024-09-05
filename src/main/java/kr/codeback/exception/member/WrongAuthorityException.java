package kr.codeback.exception.member;

import org.springframework.http.HttpStatus;

public class WrongAuthorityException extends MemberException {

	public WrongAuthorityException(HttpStatus status, String message) {
		super(status, message);
	}
}
