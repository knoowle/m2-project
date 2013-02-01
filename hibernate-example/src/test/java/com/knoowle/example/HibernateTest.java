package com.knoowle.example;

import com.knoowle.example.model.Account;
import com.knoowle.example.model.Event;
import com.knoowle.example.service.IAccountService;
import com.knoowle.example.service.impl.AccountService;
import com.knoowle.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class HibernateTest {
    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void beforeClass() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @AfterClass
    public static void afterClass() {
        sessionFactory.close();
    }

    @Test
    public void testAddAccount() {
        Session session = sessionFactory.getCurrentSession();
        Transaction trans = session.beginTransaction();
        Account account = new Account();
        account.setName("john");
        account.setPassword("jimgreen");
        session.save(account);
        trans.commit();
        System.out.println(account.getId());
    }

    @Test
    public void testAddEvent() {
        Session session = sessionFactory.getCurrentSession();
        Transaction trans = session.beginTransaction();
        Account account = (Account) session.load(Account.class, new Long(1));
        Event event = new Event();
        event.setAuthor(account);
        event.setDescription("desc");
        event.setName("name");
        session.save(event);
        trans.commit();
    }

    @Test
    public void testAccountService() {
        IAccountService service = new AccountService();
        Account account = new Account();
        account.setName("john1");
        account.setPassword("jimgreen1");
        service.addAccount(account);

        List accounts = service.getAccountList(100);

        Iterator iter = accounts.iterator();

        while (iter.hasNext()) {
            Account a = (Account) iter.next();
            System.out.println(a.getId());
        }
    }
}
