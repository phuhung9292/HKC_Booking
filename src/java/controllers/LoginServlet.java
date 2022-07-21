/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.User;
import guard.UseGuard;
import helper.GetVariable;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import repositories.UserRepository;
import variables.Routers;

@WebServlet(name = "LoginServlet", urlPatterns = {"/" + Routers.LOGIN_SERVLET})
public class LoginServlet extends HttpServlet {

    protected boolean processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {

        // Get login form variable
        GetVariable gv = new GetVariable(request);
        String username = gv.getString("username", "Username", 8, 30, null);
        String password = gv.getString("password", "Password", 6, 30, null);

        if (username == null || password == null) {
            return false;
        }

        // Get user from database
        UserRepository ad = new UserRepository();
        User user = ad.getUserByUsername(username);

        //Check user exist
        if (user == null) {
            request.setAttribute("usernameError", "Username or password is incorect");
            return false;
        }

        // Check password
        if (user.getPassword() != null && !password.equals(user.getPassword())) {
            request.setAttribute("usernameError", "Username or password is incorect");
            return false;
        }

        HttpSession session = request.getSession();
        session.setAttribute("userRole", user.getRole());
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("fullname", user.getFullname());
        return true;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UseGuard useGuard = new UseGuard(request, response);

        if (useGuard.useAuth()) {
            response.sendRedirect(Routers.INDEX_SERVLET);
            return;
        }

        request.getRequestDispatcher(Routers.LOGIN_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (processRequest(request, response)) {
                response.sendRedirect(Routers.INDEX_SERVLET);
                return;
            }
            request.getRequestDispatcher(Routers.LOGIN_PAGE).forward(request, response);
        } catch (Exception ex) {
            request.getRequestDispatcher(Routers.ERROR_500_PAGE).forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
