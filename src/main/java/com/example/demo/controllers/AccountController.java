package com.example.demo.controllers;

import com.example.demo.model.Account;
import com.example.demo.services.AccountService;
import com.example.demo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    private AccountService accService;

    @Autowired
    public AccountController(AccountService accService) {
        this.accService = accService;
    }

    @GetMapping("/get")
    public ResponseEntity<Account> getUserInfo(@AuthenticationPrincipal OAuth2User principal) {
        Account acc = accService.getUserInfo(principal.getAttribute("sub").toString());
        return ResponseEntity.ok(acc);
    }

    @PostMapping("/update")
    public void update(@AuthenticationPrincipal OAuth2User principal, String email) {
        accService.update(principal.getAttribute("sub").toString(), email);
    }
}
