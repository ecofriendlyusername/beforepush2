package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.AccountRepository;
import com.example.demo.model.Account;

import javax.persistence.EntityNotFoundException;


@Service
public class AccountService {
	AccountRepository accRepo;

	@Autowired
	public AccountService(AccountRepository accRepo) {
		this.accRepo = accRepo;
	}
	
	public void register(String sub, String email) {
		try {
			Account acc = new Account();
			acc.setEmail(email);
			acc.setSub(sub);
			accRepo.save(acc);
		} catch(IllegalArgumentException illegalArgumentException) {

		} catch(EntityNotFoundException entityNotFoundException) {

		}
	}

	public void update(String sub, String email) {
		try {
			Account acc = accRepo.getBySub(sub);
			acc.setEmail(email);
			accRepo.save(acc);
		} catch (IllegalArgumentException illegalArgumentException) {

		} catch (EntityNotFoundException entityNotFoundException) {

		}
	}

	public void delete(String sub) {
		try {
			accRepo.delete(accRepo.getBySub(sub));
		} catch (IllegalArgumentException illegalArgumentException) {

		} catch (EntityNotFoundException entityNotFoundException) {

		}
		Account acc = accRepo.getBySub(sub);
		accRepo.delete(acc);
	}

	public Account getUserInfo(String sub) {
		try {
			return accRepo.getBySub(sub);
		} catch (EntityNotFoundException entityNotFoundException) {

		}
		return null;
	}
	
	public boolean existsBySub(String sub) {
		return accRepo.existsBySub(sub);
	}
}
