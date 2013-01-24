package com.knoowle.example;

import com.knoowle.example.model.Account;
import com.knoowle.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

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
        Session session = sessionFactory.openSession();
        Transaction trans = session.beginTransaction();
        Account account = new Account();
        account.setName("john");
        account.setPassword("jimgreen");
        session.save(account);
        trans.commit();
        System.out.println(account.getId());
    }

}
