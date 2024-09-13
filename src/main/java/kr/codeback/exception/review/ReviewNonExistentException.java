package kr.codeback.exception.review;

import org.springframework.http.HttpStatus;

public class ReviewNonExistentException extends ReviewException{
    public ReviewNonExistentException(HttpStatus status, String message) {
        super(status, message);
    }
}
