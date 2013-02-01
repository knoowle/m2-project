package com.knoowle.example.service.impl;

import com.knoowle.example.model.Account;
import com.knoowle.example.service.IAccountService;
import com.knoowle.example.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AccountService implements IAccountService {
    @Override
    public Account getAccountById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trans = session.beginTransaction();
        Account account = (Account) session.get(Account.class, id);
        trans.commit();
        return account;
    }

    @Override
    public List<Account> getAccountList(int limit) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trans = session.beginTransaction();
        Query query  = session.createQuery("from com.knoowle.example.model.Account a");
        query.setMaxResults(limit);
        List accounts = query.list();
        trans.commit();
        return accounts;
    }

    @Override
    public Account addAccount(Account account) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trans = session.beginTransaction();
        session.save(account);
        trans.commit();
        return account;
    }
}
