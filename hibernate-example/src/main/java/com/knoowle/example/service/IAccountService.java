package com.knoowle.example.service;

import com.knoowle.example.model.Account;

import java.util.List;

public interface IAccountService {
    Account getAccountById(Long id);
    List<Account> getAccountList(int limit);
    Account addAccount(Account account);
}
