package com.trainingcentertastic.controller;

import com.trainingcentertastic.command.Command;
import com.trainingcentertastic.command.CommandFactory;
import com.trainingcentertastic.command.CommandResult;
import com.trainingcentertastic.connetion.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private final CommandFactory commandFactory = new CommandFactory();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    @Override
    public void destroy() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.closeAllConnection();
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandType = request.getParameter("command");
        Command action = commandFactory.create(commandType);
        String page;
        boolean isRedirect = false;
        try {
            CommandResult result = action.execute(request, response);
            page = result.getPage();
            isRedirect = result.isRedirect();
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            page = "WEB-INF/view/errorPage.jsp";
        }
        if (!isRedirect) {
            request.getRequestDispatcher(page).forward(request, response);
        } else {
            response.sendRedirect(page);
        }
    }
}
