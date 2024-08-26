package kr.codeback.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.codeback.common.MessageResponseDTO;
import kr.codeback.model.constant.SuccessMessage;
import kr.codeback.model.dto.response.MembersWithPageResponseDTO;
import kr.codeback.model.entity.Member;
import kr.codeback.service.interfaces.CodeReviewService;
import kr.codeback.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

	private final MemberService memberService;

	@GetMapping("/members")
	public ResponseEntity<MembersWithPageResponseDTO> findAllMembers(
		@RequestParam(required = false, defaultValue = "0", value = "pageNum") int pageNum,
		@RequestParam(required = false, defaultValue = "10", value = "pageSize") int pageSize) {

		// 쿠키로 이메일 가져오는 기능

		Member adminMember = memberService.findByEmail("joosung@google.com");
		MembersWithPageResponseDTO membersWithPageResponseDTO = memberService.findAllUnderAdmin(adminMember, pageNum,
			pageSize);

		return ResponseEntity.status(HttpStatus.OK).body(membersWithPageResponseDTO);
	}

	@DeleteMapping("/member/{email}")
	public ResponseEntity<MessageResponseDTO> deleteMember(@PathVariable(name = "email") String deleteEmail) {

		// 쿠키로 이메일 가져오는 기능

		Member adminMember = memberService.findAdminMemberByEmail("joosung@google.com");

		memberService.deleteByEmail(deleteEmail);

		return ResponseEntity.status(HttpStatus.OK)
			.body(new MessageResponseDTO(deleteEmail + SuccessMessage.DELETE.getMessage()));
	}

}
