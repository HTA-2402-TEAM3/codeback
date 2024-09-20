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
	public ResponseEntity<MessageResponseDTO> handleMemberException(MemberException memberException) {

		MessageResponseDTO errorResponse = new MessageResponseDTO(memberException.getMessage());
		return ResponseEntity.status(memberException.getStatus()).body(errorResponse);
	}

	@ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
	public ResponseEntity<MessageResponseDTO> handleIllegalException(RuntimeException runtimeException) {
		return ResponseEntity.badRequest().body(new MessageResponseDTO(runtimeException.getMessage()));
	}

	@ExceptionHandler(ClassCastException.class)
	public ResponseEntity<MessageResponseDTO> handleClassCastException(ClassCastException classCastException){

		MessageResponseDTO errorResponse = new MessageResponseDTO(classCastException.getMessage());
		return ResponseEntity.ok().body(errorResponse);
	}

}
