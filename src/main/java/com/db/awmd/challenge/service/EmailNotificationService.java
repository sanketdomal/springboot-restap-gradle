package com.db.awmd.challenge.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.db.awmd.challenge.domain.Account;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailNotificationService implements NotificationService {

	@Autowired
	Logger log;
  @Override
  public void notifyAboutTransfer(Account account, String transferDescription) {
    //THIS METHOD SHOULD NOT BE CHANGED - ASSUME YOUR COLLEAGUE WILL IMPLEMENT IT
    log
      .info("Sending notification to owner of {}: {}", account.getAccountId(), transferDescription);
  }

}
