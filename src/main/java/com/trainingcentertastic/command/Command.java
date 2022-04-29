package com.trainingcentertastic.command;

import com.trainingcentertastic.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The command interface {@code Command} is designed to process the request and return a response
 *
 * @author Eugene Lapko
 * @see com.trainingcentertastic.command.CommandFactory
 */
public interface Command {
    /**
     *
     * @param request Interface serving to provide request information for HTTP Servlets
     * @param response provide HTTP-specific functionality in sending a response
     * @return {@code CommandResult} which contains the page and does forward or redirect
     * @throws ServiceException is thrown when there is an error in the business logic of the application
     * or when the request is incorrect
     */
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
