package kr.codeback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.codeback.model.dto.request.PreferenceRequestDTO;
import kr.codeback.model.dto.response.PreferenceResponseDTO;
import kr.codeback.model.entity.Member;
import kr.codeback.model.entity.Preference;
import kr.codeback.service.interfaces.MemberService;
import kr.codeback.service.interfaces.NotificationService;
import kr.codeback.service.interfaces.PreferenceService;
import kr.codeback.util.JwtUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping()
public class PreferenceController {

	private final PreferenceService preferenceService;
	private final MemberService memberService;
	private final JwtUtil jwtUtil;
	private final NotificationService notificationService;

	@PutMapping("/like/{entityID}")
	public ResponseEntity<PreferenceResponseDTO> saveOrChange(
		@PathVariable(name = "entityID") String entityID,
        @RequestBody PreferenceRequestDTO preferenceRequestDTO,
        @CookieValue(name = "access_token") String accessToken) {

		String email = jwtUtil.extractEmail(accessToken);
		Member member = memberService.findByEmail(email);
		Preference preference = preferenceService.save(member, entityID);
		notificationService.save(preference, preferenceRequestDTO.getType());

		return ResponseEntity.ok()
			.body(PreferenceResponseDTO.builder()
				.entityID(preference.getEntityID())
				.likeSign(preference.isLike())
				.build());
	}
}
