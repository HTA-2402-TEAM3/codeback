package kr.codeback.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.codeback.model.dto.request.AuthorityRequestDTO;
import kr.codeback.model.dto.response.AuthorityResponseDTO;
import kr.codeback.model.dto.response.MembersWithPageResponseDTO;
import kr.codeback.model.entity.Authority;
import kr.codeback.model.entity.Member;
import kr.codeback.service.interfaces.AuthorityService;
import kr.codeback.service.interfaces.MemberService;
import kr.codeback.util.JwtUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminRestController {

	private final JwtUtil jwtUtil;
	private final MemberService memberService;
	private final AuthorityService authorityService;

	@GetMapping("/members")
	public ResponseEntity<MembersWithPageResponseDTO> findAllMembers(
		@RequestParam(required = false, defaultValue = "0", value = "pageNum") int pageNum,
		@RequestParam(required = false, defaultValue = "10", value = "pageSize") int pageSize,
		@CookieValue(value = "access_token") String jwtToken) {

		String email = jwtUtil.extractEmail(jwtToken);
		memberService.validateAdminMemberByEmail(email);

		MembersWithPageResponseDTO membersWithPageResponseDTO = memberService.findAllUnderAdmin(pageNum,
			pageSize);

		return ResponseEntity.ok().body(membersWithPageResponseDTO);
	}

	@DeleteMapping("/member/{email}")
	public ResponseEntity<Void> deleteMember(
		@PathVariable(name = "email") String deleteEmail,
		@CookieValue(value = "access_token") String jwtToken) {

		String email = jwtUtil.extractEmail(jwtToken);
		memberService.validateAdminMemberByEmail(email);

		memberService.softDeleteByEmail(deleteEmail);

		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/member/{email}/authority")
	public ResponseEntity<AuthorityResponseDTO> changeAuthority(
		@PathVariable(name = "email") String email,
		@RequestBody AuthorityRequestDTO authorityRequestDTO) {

		Authority authority = authorityService.findByName(authorityRequestDTO.getAuthorityName());
		Member member = memberService.findByEmail(email);

		member.changeAuthority(authority);
		memberService.save(member);

		return ResponseEntity.status(HttpStatus.OK)
			.body(new AuthorityResponseDTO(member.getEmail(), authority.getName()));
	}

}
