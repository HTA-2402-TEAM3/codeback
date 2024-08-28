package kr.codeback.model.entity;

import java.util.UUID;

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
	private UUID id;

	@Column(name="email", nullable = false, unique = true)
	private String email;

	@Column(name = "nickname", nullable = false, unique = true)
	private String nickname;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "authority_id", nullable = false)
	private Authority authority;

	@Column(name = "is_delete", columnDefinition = "TINYINT(1)")
	private boolean deleteSign = false;

	@Builder
	private Member(UUID id, String email, String nickname, Authority authority) {
		this.id = id;
		this.email = email;
		this.nickname = nickname;
		this.authority = authority;
	}

	public boolean isAdmin() {
		return authority.getName().equals("ROLE_ADMIN");
	}

	public void deleteMember() {

		String uuid = String.valueOf(UUID.randomUUID());
		email = uuid;
		nickname = uuid;
		deleteSign = true;
	}

	public String getNickname() {
		if(deleteSign) {
			return "탈퇴한 회원";
		}

		return nickname;
	}

}
