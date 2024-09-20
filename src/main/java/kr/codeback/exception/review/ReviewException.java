package kr.codeback.exception.review;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ReviewException extends RuntimeException{
    private final HttpStatus status;

    public ReviewException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
