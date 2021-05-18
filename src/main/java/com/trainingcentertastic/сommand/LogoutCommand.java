package com.trainingcentertastic.сommand;

import com.trainingcentertastic.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand implements Command{
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException {
        request.getSession().invalidate();
        return CommandResult.forward("index.jsp");
    }
}
