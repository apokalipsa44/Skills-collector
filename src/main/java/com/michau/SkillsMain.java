package com.michau;

import com.michau.DbUtils.DbConnectionManager;
import com.michau.Model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import java.awt.*;


public class SkillsMain {
    public static void main(String[] args) {
        User user =new User();
        user.setUserName("user1");
        user.setPassword("pass");
        DbConnectionManager connectionManager=new DbConnectionManager();
        EntityManager entityManager=connectionManager.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        System.out.println("Person saved successfully");
        entityManager.close();
        connectionManager.shutdown();


    }
}
