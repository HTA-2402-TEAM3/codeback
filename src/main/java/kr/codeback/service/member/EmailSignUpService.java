package kr.codeback.service.member;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.codeback.model.entity.Member;
import kr.codeback.repository.MemberRepository;
import kr.codeback.util.JwtUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EmailSignUpService {
	private final JwtUtil jwtUtil;
	private final MemberRepository memberRepository;
	private final JavaMailSender emailSender;

	@Value("${spring.mail.username}")
	private String senderEmail;

	@Value("${moview.client.url}")
	private String clientUrl;

	public boolean registerMember(String email) {

		String nickname = substringEmail(email);

		Member member = Member.builder()
			.email(email)
			.nickname(nickname)
			.build();

		String jwtToken = jwtUtil.generateAccessToken(email,nickname);

		sendVerificationEmail(member,jwtToken);
		return verifyMember(jwtToken);

	}

	public void sendVerificationEmail(Member member, String token) {
		String subject = "Please verify your email";
		String verificationUrl = clientUrl + "/verify?token=" + token;

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(senderEmail);
		mailMessage.setTo(member.getEmail());
		mailMessage.setSubject(subject);
		mailMessage.setText("다음 링크를 클릭하시면 회원가입이 완료됩니다. " + verificationUrl);

		emailSender.send(mailMessage);
	}

	public boolean verifyMember(String token) {
		if (jwtUtil.validateToken(token,jwtUtil.extractEmail(token))) {
			String email = jwtUtil.extractEmail(token);
			Member member = memberRepository.findById(email).get();
			memberRepository.save(member);
			return true;
		}
		return false;
	}

	// private MemberRegisterService getSelf() {
	// 	return applicationContext.getBean(MemberRegisterService.class);
	// }

	public String substringEmail(String email){
		int atIndex = email.indexOf('@');

		if (atIndex != -1) { // 골뱅이가 문자열에 존재하는지 확인
			// 골뱅이 앞까지의 문자열 추출
			String username = email.substring(0, atIndex);
			return username;
		}
		return null;
	}
}
