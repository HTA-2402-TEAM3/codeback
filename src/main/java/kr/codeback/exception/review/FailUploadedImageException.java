package kr.codeback.exception.review;

import org.springframework.http.HttpStatus;

public class FailUploadedImageException extends ReviewException{
    public FailUploadedImageException(HttpStatus status, String message) {
        super(status, message);
    }
}
