//package kr.codeback.controller;
//
//import kr.codeback.model.entity.Member;
//import kr.codeback.repository.PreferenceRepository;
//import kr.codeback.service.interfaces.MemberService;
//import kr.codeback.service.interfaces.PreferenceService;
//import kr.codeback.util.JwtUtil;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.UUID;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping()
//public class PreferenceController {
//
//    private final PreferenceService preferenceService;
//    private final MemberService memberService;
//    private final JwtUtil jwtUtil;
//
//
//    @PutMapping("/like/{entityID}")
//    public String saveOrChange(@RequestParam(name = "entityID") String entityID,
//    @CookieValue(name = "access_token") String accessToken) {
//
//        String email = jwtUtil.extractEmail(accessToken);
//        Member member = memberService.findByEmail(email);
//        preferenceService.save(member, entityID);
//
//        return "";
//    }
//}
//
//
//
