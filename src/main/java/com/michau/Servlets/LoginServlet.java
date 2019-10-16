package com.michau.Servlets;

import com.michau.DbUtils.UserDao;
import com.michau.Model.User;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionFactory sessionFactory = (SessionFactory) req.getServletContext().getAttribute("sessionFactory");
        UserDao userDao = new UserDao(sessionFactory);
        checkLogin(userDao, req, resp);

    }

    private void checkLoginAndPassword(UserDao userDao, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userDao.getAllByUsernameAndPassword(req.getParameter("login"), req.getParameter("password"));
        if (users == null || users.size() == 0) {
            System.out.println("nieprawidłowe hasło");
            req.getRequestDispatcher("WEB-INF/views/login.jsp?login=" + req.getParameter("login")).forward(req, resp);
        } else {
            System.out.println("zalogowany");
            User user=users.get(0);
//            unieważnij bieżącą sesję,
//            pobierz nową sesję (metoda getSession(true)),
//            dodaj do sesji obiekt User pod atrybutem user,
//            wykonaj zewnętrzne przekierowanie na ścieżkę /user/skills.
            req.getSession().invalidate();
            HttpSession httpSession=req.getSession(true);
            httpSession.setAttribute("user", user);
            resp.sendRedirect("/user/skills");
        }
    }

    private void checkLogin(UserDao userDao, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userDao.getAllByUsername(req.getParameter("login"));
        if (users == null || users.size() == 0) {
            System.out.println("brak loginu");
            req.getRequestDispatcher("WEB-INF/views/register.jsp?userNotFound=yes").forward(req, resp);
        } else {
            checkLoginAndPassword(userDao, req, resp);
        }
    }
}
