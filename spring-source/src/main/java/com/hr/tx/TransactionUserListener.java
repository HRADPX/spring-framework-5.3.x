package com.hr.tx;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-04-16
 */
@Component
public class TransactionUserListener {

	@TransactionalEventListener
	public void handleTransactionComplete(String message) {
		System.out.println("transaction event receive, " + message);
	}

	@TransactionalEventListener
	public void handleTransactionComplete(TransactionUserEvent event) {
		System.out.println("transaction event receive, " + event.getSource());
	}


	@TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
	public void handleTransactionEx(TransactionUserEvent event) {
		System.out.println("update user error, userId: " + event.getId());
	}
}
