package kr.codeback.controller;

import kr.codeback.model.dto.response.PreferenceResponseDTO;
import kr.codeback.model.entity.Member;
import kr.codeback.model.entity.Preference;
import kr.codeback.service.interfaces.MemberService;
import kr.codeback.service.interfaces.PreferenceService;
import kr.codeback.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping()
public class PreferenceController {

    private final PreferenceService preferenceService;
    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    @PutMapping("/like/{entityID}")
    public ResponseEntity<PreferenceResponseDTO> saveOrChange(@PathVariable(name = "entityID") String entityID, @CookieValue(name = "access_token") String accessToken) {

        String email = jwtUtil.extractEmail(accessToken);
        Member member = memberService.findByEmail(email);
        Preference preference = preferenceService.save(member, entityID);

        return ResponseEntity.ok().body(PreferenceResponseDTO.builder().entityID(preference.getEntityID()).likeSign(preference.isLike()).build());
    }
}
