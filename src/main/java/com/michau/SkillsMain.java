package com.michau;

import com.michau.DbUtils.UserDao;
import com.michau.Model.User;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/gucio")
public class SkillsMain extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionFactory sessionFactory= (SessionFactory) req.getServletContext().getAttribute("sessionFactory");
        UserDao userDao=new UserDao(sessionFactory);
        User user=new User();
        user.setUserName("login");
        user.setPassword("pass");
        userDao.save(user);
    }
}
