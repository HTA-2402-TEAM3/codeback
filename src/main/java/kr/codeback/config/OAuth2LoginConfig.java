// package kr.codeback.config;
//
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.oauth2.client.registration.ClientRegistration;
// import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
// import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
// import org.springframework.security.oauth2.core.AuthorizationGrantType;
// import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
//
// @Configuration
// public class OAuth2LoginConfig {
//
// 	@Value("${spring.security.oauth2.client.registration.github.client-id}") String clientId;
// 	@Value("${spring.security.oauth2.client.registration.github.client-secret}") String clientSecret;
// 	@Value("${spring.security.oauth2.client.registration.github.redirect-uri}") String redirectURI;
//
// 	@Bean
// 	public ClientRegistrationRepository clientRegistrationRepository() {
// 		return new InMemoryClientRegistrationRepository(this.githubClientRegistration());
// 	}
//
// 	private ClientRegistration githubClientRegistration() {
// 		return ClientRegistration.withRegistrationId("github")
// 			.clientId(clientId) // GitHub client ID
// 			.clientSecret(clientSecret) // GitHub client secret
// 			.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
// 			.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
// 			.redirectUri(redirectURI)
// 			.scope("read:user", "user:email") // 필요한 권한
// 			.authorizationUri("https://github.com/login/oauth/authorize")
// 			.tokenUri("https://github.com/login/oauth/access_token")
// 			.userInfoUri("https://api.github.com/user")
// 			.userNameAttributeName("id") // GitHub에서 반환하는 사용자 ID
// 			.clientName("GitHub")
// 			.build();
// 	}
//
// 	private ClientRegistration googleClientRegistration() {
// 		return ClientRegistration.withRegistrationId("google")
// 			.clientId(clientId) // GitHub client ID
// 			.clientSecret(clientSecret) // GitHub client secret
// 			.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
// 			.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
// 			.redirectUri(redirectURI)
// 			.scope("read:user", "user:email") // 필요한 권한
// 			.authorizationUri("https://github.com/login/oauth/authorize")
// 			.tokenUri("https://github.com/login/oauth/access_token")
// 			.userInfoUri("https://api.github.com/user")
// 			.userNameAttributeName("id") // GitHub에서 반환하는 사용자 ID
// 			.clientName("GitHub")
// 			.build();
// 	}
// }
