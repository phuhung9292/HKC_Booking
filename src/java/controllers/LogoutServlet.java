/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import guard.UseGuard;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import variables.Routers;

@WebServlet(name = "LogoutServlet", urlPatterns = {"/" + Routers.LOGOUT_SERVLET})
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Remove session
            UseGuard useGuard = new UseGuard(request, response);
            useGuard.clearSession();
        } finally {
            // forward on success
            response.sendRedirect(Routers.INDEX_SERVLET);
        }
    }

}
