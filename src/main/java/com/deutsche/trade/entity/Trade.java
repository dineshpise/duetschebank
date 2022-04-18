package com.deutsche.trade.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
public class Trade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, insertable = false, updatable = false)
	private Long id;

	@NotBlank
	@Column(name = "trade_id", nullable = false)
	private String tradeId;

	@Column(name = "version", nullable = false)
	private Long version;

	@NotBlank
	@Column(name = "counter_party", nullable = false)
	private String counterParty;

	@NotBlank
	@Column(name = "book_id", nullable = false)
	private String bookId;

	@Column(name = "maturity_date", nullable = false)
	private LocalDate maturityDate;

	@Column(name = "created_date", nullable = false, updatable = false)
	private LocalDate createdDate;

	@NotBlank
	@Column(name = "expired_flag", nullable = false, insertable = false, columnDefinition = "varchar(1) default 'N'")
	private String expiredFlag;

	@Override
	public String toString() {
		return "Trade [id=" + id + ", tradeId=" + tradeId + ", version=" + version + ", counterParty=" + counterParty
				+ ", bookId=" + bookId + ", maturityDate=" + maturityDate + ", createdDate=" + createdDate
				+ ", expiredFlag=" + expiredFlag + "]";
	}

}