package kr.codeback.exception.review;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
public class ReviewException extends RuntimeException{
    private final HttpStatus status;

    public ReviewException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
