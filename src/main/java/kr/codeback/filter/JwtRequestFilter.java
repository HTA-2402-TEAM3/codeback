package kr.codeback.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.codeback.model.constant.CustomOAuth2User;
import kr.codeback.model.constant.OAuthProfile;
import kr.codeback.model.entity.Member;
import kr.codeback.repository.MemberRepository;
import kr.codeback.service.member.MyUserDetailsService;
import kr.codeback.util.CookieUtil;
import kr.codeback.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String accessToken = CookieUtil.getCookieValue(request,"access_token");
        String refreshToken = CookieUtil.getCookieValue(request,"refresh_token");

        //Authorization 헤더 검증
        if (accessToken == null) {

            if(refreshToken==null) {
                filterChain.doFilter(request, response);
                return;
            }else{
                Member member = memberRepository.findByEmail(jwtUtil.extractEmail(refreshToken)).get();
                jwtUtil.generateAccessToken(member.getEmail(),member.getNickname(),member.getAuthority().getName());
            }
        }

        //토큰 소멸 시간 검증
        if (!jwtUtil.validateToken(accessToken)) {
            if(!jwtUtil.validateToken(refreshToken)) {
                System.out.println("token expired");
                filterChain.doFilter(request, response);
                return;
            }else{
                Member member = memberRepository.findByEmail(jwtUtil.extractEmail(refreshToken)).get();
                jwtUtil.generateAccessToken(member.getEmail(),member.getNickname(),member.getAuthority().getName());
            }
        }

        //토큰에서 nickname과 role 획득
        String username = jwtUtil.extractNickname(accessToken);
        String email = jwtUtil.extractEmail(accessToken);
        String role = jwtUtil.extractRole(accessToken);

        log.info(username);
        log.info(email);
        log.info(role);

        //userDTO를 생성하여 값 set
        OAuthProfile oAuthProfile = OAuthProfile.builder()
            .username(username)
            .name(email)
            .role(role)
            .build();

        //UserDetails에 회원 정보 객체 담기
        CustomOAuth2User customOAuth2User = new CustomOAuth2User(oAuthProfile);

        //스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, "", customOAuth2User.getAuthorities());
        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
