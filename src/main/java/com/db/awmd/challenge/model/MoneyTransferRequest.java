package com.db.awmd.challenge.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MoneyTransferRequest {
	private String accountFromId;
	private String accountToId;
	private BigDecimal amount;

	// public MoneyTransferRequest(String accountFromId, String accountToId,
	// BigDecimal amount) {
	// this.accountFromId = accountFromId;
	// this.accountToId = accountToId;
	// this.amount = amount;
	// }

	@JsonCreator
	public MoneyTransferRequest(@JsonProperty("accountFromId") String accountFromId,
			@JsonProperty("accountToId") String accountToId, @JsonProperty("amount") BigDecimal amount) {

		this.accountFromId = accountFromId;
		this.accountToId = accountToId;
		this.amount = amount;
	}

	public String getAccountFromId() {
		return accountFromId;
	}

	public void setAccountFromId(String accountFromId) {
		this.accountFromId = accountFromId;
	}

	public String getAccountToId() {
		return accountToId;
	}

	public void setAccountToId(String accountToId) {
		this.accountToId = accountToId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
