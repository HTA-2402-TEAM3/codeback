package kr.codeback.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "MEMBER")
@NoArgsConstructor
@Getter
@ToString
public class Member {

	@Id
	private String email;

	@Column(name = "nickname", nullable = false, unique = true)
	private String nickname;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "authority_id", nullable = false)
	private Authority authority;

	@Builder
	private Member(String email, String nickname, Authority authority) {
		this.email = email;
		this.nickname = nickname;
		this.authority = authority;
	}

}
