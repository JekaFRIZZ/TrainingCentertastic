package com.trainingcentertastic.сommand;

import com.mysql.cj.exceptions.StreamingNotifiable;
import com.trainingcentertastic.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowPageCommand implements Command {
    private final String page;

    public ShowPageCommand(String page) {
        this.page = page;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException {
        return CommandResult.forward(page);
    }
}
