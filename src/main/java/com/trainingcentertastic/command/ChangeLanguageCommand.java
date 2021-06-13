package com.trainingcentertastic.command;

import com.trainingcentertastic.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command {
    private final String LOCAL = "local";
    private final String COMMAND = "/controller?command=";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String local = request.getParameter(LOCAL);
        HttpSession session = request.getSession();
        session.setAttribute(LOCAL, local);
        String path = COMMAND;

        String page = request.getParameter("currentPage");
        if(page.isEmpty()) {
            path += "login";
        }
        path += page;
        return CommandResult.forward(path);
    }
}
