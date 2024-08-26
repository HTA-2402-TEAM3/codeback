package kr.codeback.service.interfaces;

public interface CodeReviewCommentService {

	// 이메일로 삭제
	void deleteAllByEmail(String deleteEmail);

	// 이메일로 찾기
	void findByEmail(String email);

	void deleteAllByBaseCommentID(String baseCommentID);

}
