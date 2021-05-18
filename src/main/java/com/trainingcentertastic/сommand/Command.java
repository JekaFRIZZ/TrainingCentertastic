package com.trainingcentertastic.сommand;

import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException, DaoException, com.google.protobuf.ServiceException;
}
