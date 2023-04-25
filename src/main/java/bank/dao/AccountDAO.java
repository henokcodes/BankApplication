package bank.dao;

import java.util.*;

import bank.domain.Account;
import bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class AccountDAO implements IAccountDAO {

	@Autowired
	AccountRepository accountRepository;
	Collection<Account> accountlist = new ArrayList<Account>();

	public static Account createAccount(Account account){
		return new Account(account.getAccountnumber());
	}
	public void saveAccount(Account account) {
		// System.out.println("AccountDAO: saving account with accountnr ="+account.getAccountnumber());
//		accountlist.add(account); // add the new
		accountRepository.save(account);
	}

	public void updateAccount(Account account) {
		// System.out.println("AccountDAO: update account with accountnr ="+account.getAccountnumber());
		Account accountexist = loadAccount(account.getAccountnumber());
		if (accountexist != null) {
//			accountlist.remove(accountexist); // remove the old
//			accountlist.add(account); // add the new
			accountRepository.delete(accountexist);
			accountRepository.save(account);
		}

	}

	public Account loadAccount(long accountnumber) {
		// System.out.println("AccountDAO: loading account with accountnr ="+accountnumber);
		for (Account account : accountRepository.findAll()) {
			if (account.getAccountnumber() == accountnumber) {
				return account;
			}
		}
		return null;
	}

	public Collection<Account> getAccounts() {
		return accountRepository.findAll();
	}

}
