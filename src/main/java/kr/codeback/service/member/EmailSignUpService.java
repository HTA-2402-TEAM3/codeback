package kr.codeback.service.member;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.codeback.repository.MemberRepository;
import kr.codeback.service.interfaces.MemberService;
import kr.codeback.util.JwtUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EmailSignUpService {

	private final JavaMailSender emailSender;
	private final MemberRepository memberRepository;

	@Value("${spring.mail.username}")
	private String senderEmail;

	@Value("${spring.mail.client-url}")
	private String clientUrl;

	public void sendVerificationEmail(String email, String token) {

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(senderEmail);
		mailMessage.setTo(email);
		String verificationUrl = clientUrl + "/registration?code=" + token;
		String subject = null;

		if(memberRepository.findByEmail(email).isPresent()){
			subject = "Code Back 에서 로그인 요청을 보냈습니다.";
			mailMessage.setText("다음 링크를 클릭하시면 로그인이 완료됩니다. " + verificationUrl);
		}else{
			subject = "Code Back 에서 회원가입 요청을 보냈습니다.";
			mailMessage.setText("다음 링크를 클릭하시면 회원가입이 완료됩니다. " + verificationUrl);
		}

		mailMessage.setSubject(subject);
		emailSender.send(mailMessage);
	}

	public String substringEmail(String email) {
		int atIndex = email.indexOf('@');

		if (atIndex != -1) { // 골뱅이가 문자열에 존재하는지 확인
			// 골뱅이 앞까지의 문자열 추출
			String username = email.substring(0, atIndex)+"#codeback";
			return username;
		}
		return null;
	}
}
