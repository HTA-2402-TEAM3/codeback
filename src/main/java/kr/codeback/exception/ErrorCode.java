package kr.codeback.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	NOT_EXIST_USER(HttpStatus.NOT_FOUND, "User does not exist"),
	WRONG_AUTHORITY(HttpStatus.BAD_REQUEST, "You do not have permission");

	private final HttpStatus status;
	private final String message;

}
