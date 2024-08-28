package kr.codeback.model.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

	DELETE(" 삭제가 완료되었습니다."),
	CREATE(" 생성이 완료되었습니다."),
	UPDATE(" 업데이트가 완료되었습니다.");

	private final String message;

}
