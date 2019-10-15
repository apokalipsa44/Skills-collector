package com.michau.Servlets;

import com.michau.DbUtils.UserDao;
import com.michau.Model.User;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("WEB-INF/views/register.jsp").forward(req, resp);
    }
//    src/main/webapp/WEB-INF/views/register.jsp
//    scA9E6oZfX
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionFactory sessionFactory= (SessionFactory) req.getServletContext().getAttribute("sessionFactory");
        UserDao userDao=new UserDao(sessionFactory);
        User user = new User();
        user.setUsername(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        if(userDao.isUsernameAvailable(user.getUsername())) {
            userDao.save(user);
            req.getRequestDispatcher("WEB-INF/views/register.jsp").forward(req, resp);
        }else {
//            resp.sendRedirect("WEB-INF/views/register.jsp?login="+user.getUsername());
//            todo https://stackoverflow.com/questions/7220241/whats-the-difference-between-requestdispatcher-forward-and-httpservletrespons/7220417

            System.out.println("jest w bazie");
            req.getRequestDispatcher("WEB-INF/views/register.jsp?login="+user.getUsername()).forward(req, resp);
        }



    }
}

