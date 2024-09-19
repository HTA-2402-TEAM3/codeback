package kr.codeback.exception.review;

import org.springframework.http.HttpStatus;

public class ReviewNotAuthorizedException extends ReviewException {
    public ReviewNotAuthorizedException(HttpStatus status, String message) {
        super(status, message);
    }
}
