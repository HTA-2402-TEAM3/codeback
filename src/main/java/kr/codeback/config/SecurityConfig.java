package kr.codeback.config;

import kr.codeback.filter.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import kr.codeback.service.member.MyUserDetailsService;
import kr.codeback.service.member.OAuth2Service;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final MyUserDetailsService userDetailsService;
	private final OAuth2Service oAuth2Service;
	private final JwtRequestFilter jwtRequestFilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
			// token을 사용하는 방식이기 때문에 csrf disable
			.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
				.requestMatchers("/**").permitAll()
				// .anyRequest().permitAll()
			)
			// 세션을 사용하지 않기 때문에 STATELESS로 설정
			.sessionManagement(sessionManagement ->
				sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.oauth2Login((oauth2) -> oauth2
			.userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
				.userService(oAuth2Service)))
			.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

}

