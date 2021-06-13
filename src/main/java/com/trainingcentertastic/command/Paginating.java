package com.trainingcentertastic.command;

import com.trainingcentertastic.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Eugene Lapko
 */
public interface Paginating {
    int RECORDS_PER_PAGE = 5;
    int RECORDS_PER_PAGE_FOR_COURSES = 3;

    void paginate(HttpServletRequest request, HttpServletResponse response, String... params) throws ServiceException;
}
