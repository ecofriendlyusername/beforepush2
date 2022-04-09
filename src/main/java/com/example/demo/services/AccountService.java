package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.AccountRepository;
import com.example.demo.model.Account;

@Service
public class AccountService {
	@Autowired
	AccountRepository accRepo;
	
	public void register(String sub, String email) {
		Account acc = new Account();
    	acc.setEmail(email);
    	acc.setSub(sub);
    	accRepo.save(acc);
	}
	
	public boolean existsBySub(String sub) {
		return accRepo.existsBySub(sub);
	}
}
