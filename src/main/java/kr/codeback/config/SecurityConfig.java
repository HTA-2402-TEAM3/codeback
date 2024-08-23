package kr.codeback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import kr.codeback.service.OAuth2Service;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final OAuth2Service oAuth2Service;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
			.oauth2Login((oauth2) -> oauth2
				.userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
					.userService(oAuth2Service)));

		http

		// token을 사용하는 방식이기 때문에 csrf disable
			.csrf(AbstractHttpConfigurer::disable)

			// .exceptionHandling(exceptionHandling -> exceptionHandling
			// 	.accessDeniedHandler(jwtAccessDeniedHandler)
			// 	.authenticationEntryPoint(jwtAuthenticationEntryPoint)
			// )

				// .oauth2Login(withDefaults())
			.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
				.requestMatchers("/**").permitAll()
				// .requestMatchers("/member/info").authenticated()
				// .requestMatchers("/admin/**").hasRole("ADMIN")
				// .requestMatchers(HttpMethod.GET).permitAll()
				// .anyRequest().authenticated()
			)

			// 세션을 사용하지 않기 때문에 STATELESS로 설정
			.sessionManagement(sessionManagement ->
				sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			);

			// .with(new JwtSecurityConfig(tokenProvider), customizer -> {
			// });
		return http.build();
	}


}
