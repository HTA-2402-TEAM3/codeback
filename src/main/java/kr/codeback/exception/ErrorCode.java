package kr.codeback.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	NOT_EXIST_USER(HttpStatus.NOT_FOUND, "User does not exist"),
	WRONG_AUTHORITY(HttpStatus.BAD_REQUEST, "You do not have permission"),
//	Review
	NONEXISTENT_REVIEW(HttpStatus.NOT_FOUND, "Review does not exist"),
	FAIL_UPLOAD_IMAGE(HttpStatus.ACCEPTED, "Fail upload Image to  aws S3..."),
//	Comment
	NONEXISTENT_COMMENT(HttpStatus.NOT_FOUND, "Comment does not exist");


    private final HttpStatus status;
	private final String message;
}
