package kr.codeback.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MONTHS")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Month {

	@Id
	private int month;
}
