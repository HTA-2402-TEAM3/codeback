package kr.codeback.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kr.codeback.exception.member.MemberException;
import kr.codeback.exception.member.MemberNotFoundException;
import kr.codeback.exception.member.WrongAuthorityException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({MemberNotFoundException.class, WrongAuthorityException.class})
	public ResponseEntity<MessageResponseDTO> handleIllegalException(MemberException memberException) {

		MessageResponseDTO errorResponse = new MessageResponseDTO(memberException.getMessage());
		return ResponseEntity.status(memberException.getStatus()).body(errorResponse);
	}

}
