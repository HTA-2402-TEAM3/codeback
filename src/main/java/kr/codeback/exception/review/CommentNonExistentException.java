package kr.codeback.exception.review;

import org.springframework.http.HttpStatus;

public class CommentNonExistentException extends ReviewException{
    public CommentNonExistentException(HttpStatus status, String message) {
        super(status, message);
    }
}
