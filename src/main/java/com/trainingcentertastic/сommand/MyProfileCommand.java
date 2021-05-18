package com.trainingcentertastic.сommand;

import com.trainingcentertastic.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyProfileCommand implements Command{
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException {
        return CommandResult.redirect("WEB-INF/view/myProfile.jsp");
    }
}
