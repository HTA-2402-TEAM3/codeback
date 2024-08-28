package kr.codeback.exception.member;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class MemberException extends RuntimeException {

	private final HttpStatus status;

	public MemberException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}
}
