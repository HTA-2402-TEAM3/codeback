package kr.codeback.common;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.codeback.model.constant.CustomOAuth2User;
import kr.codeback.util.CookieUtil;
import kr.codeback.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private final JwtUtil jwtUtil;

	public CustomSuccessHandler(JwtUtil jwtUtil) {

		this.jwtUtil = jwtUtil;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws
		IOException, java.io.IOException {

		authentication = SecurityContextHolder.getContext().getAuthentication();

		//OAuth2User
		CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

		String username = customUserDetails.getUsername();
		String email = customUserDetails.getName();

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
		GrantedAuthority auth = iterator.next();
		String role = auth.getAuthority();

		String accessToken = jwtUtil.generateAccessToken( email,username, role);
		String refreshToken = jwtUtil.generateRefreshToken(email);

		//액세스 토큰 생성
		CookieUtil.createCookie(response,"access_token",accessToken,3600000);
		CookieUtil.createCookie(response,"refresh_token",refreshToken,36000000);

		//redirect위치
		response.sendRedirect("http://localhost:8080/");
	}

}
