package com.db.awmd.challenge.service;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.model.MoneyTransferRequest;
import com.db.awmd.challenge.repository.AccountsRepository;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {
	
	@Autowired
	NotificationService notificationService;

	@Getter
	private final AccountsRepository accountsRepository;

	@Autowired
	public AccountsService(AccountsRepository accountsRepository) {
		this.accountsRepository = accountsRepository;
	}

	public void createAccount(Account account) {
		this.accountsRepository.createAccount(account);
	}

	public Account getAccount(String accountId) {
		return this.accountsRepository.getAccount(accountId);
	}

	public AccountsRepository getAccountsRepository() {
		return accountsRepository;
	}

	public void transferAmount(MoneyTransferRequest moneyTransferRequest) {
		
		Optional<MoneyTransferRequest> om = Optional.of(moneyTransferRequest);
		if(om.isPresent()){
			Optional<Account> fromAcc = Optional.of(getAccount(moneyTransferRequest.getAccountFromId()));
			Optional<Account> toAcc = Optional.of(getAccount(moneyTransferRequest.getAccountToId()));
			
			if(fromAcc.isPresent() && toAcc.isPresent() && om.get().getAmount().compareTo(fromAcc.get().getBalance()) < 0){
				//if(fromAcc.get().getBalance().subtract(moneyTransferRequest.getAmount()) == new BigDecimal(0))
				toAcc.get().setBalance(toAcc.get().getBalance().add(moneyTransferRequest.getAmount()));
				fromAcc.get().setBalance(fromAcc.get().getBalance().subtract(moneyTransferRequest.getAmount()));
				this.accountsRepository.updateAccount(toAcc.get());
				this.accountsRepository.updateAccount(fromAcc.get());	
				System.out.println("Account "+toAcc.get().getAccountId()+ " has been credited with INR " + moneyTransferRequest.getAmount() + " and debited from account "+ fromAcc.get().getAccountId());
				System.out.println("Account "+ fromAcc.get().getAccountId() +" has been debited with INR " + moneyTransferRequest.getAmount() + " and credited to account "+ toAcc.get().getAccountId());
				notificationService.notifyAboutTransfer(toAcc.get(), "Account "+toAcc.get().getAccountId()+ " has been credited with INR " + moneyTransferRequest.getAmount() + " and debited from account "+ fromAcc.get().getAccountId());				
				notificationService.notifyAboutTransfer(fromAcc.get(), "Account "+ fromAcc.get().getAccountId() +" has been debited with INR " + moneyTransferRequest.getAmount() + " and credited to account "+ toAcc.get().getAccountId());
				
			}
					
		}		
		
	}

}
