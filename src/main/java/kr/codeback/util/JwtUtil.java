package kr.codeback.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {

	private static final long accessTokenValidityInMilliseconds = 3600000; // 1시간 (액세스 토큰 유효 기간)
	private static final long refreshTokenValidityInMilliseconds = 604800000; // 7일 (리프레시 토큰 유효 기간)
	private String secretKey;

	@PostConstruct
	protected void init() {
		// secretKey = Base64.getEncoder().encodeToString(key.getEncoded()); // 시크릿 키 생성 (한 번 하고 저장)
		// secretkey는 후에 properties에 추가
		secretKey = "wKiLdOXjE24YA3h8ETn1p19EnXRjUBMKDbY0sdw/jjA=";
	}

	//이메일 추출
	public String extractEmail(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public String extractNickname(String token) {
		return extractClaim(token, claims -> claims.get("nickname", String.class));
	}

	//만료시간 추출
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	//토큰의 특정한 Claim 추출
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	//모든 Claim 추출
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}

	//토큰이 만료되었는지 확인
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	//토큰 생성하기
	public String generateRefreshToken(String email) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, email,refreshTokenValidityInMilliseconds);
	}

	public String generateAccessToken(String email, String nickname) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("nickname", nickname);
		return createToken(claims, email,accessTokenValidityInMilliseconds);
	}

    public String generateRegistrationToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims,email,3600000);
    }

	//토큰 생성하기
	private String createToken(Map<String, Object> claims, String subject, long time) {
		return Jwts.builder()
			.setClaims(claims)
			.setSubject(subject)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + time))
			.signWith(SignatureAlgorithm.HS256, secretKey)
			.compact();
	}

	//토큰 판별하기
	public boolean validateToken(String token) {
		try {
			// Claims 객체 추출
			Claims claims = extractAllClaims(token);
			// 만료 여부 확인
			return !isTokenExpired(token);
		} catch (Exception e) {
			return false;
		}
	}

}