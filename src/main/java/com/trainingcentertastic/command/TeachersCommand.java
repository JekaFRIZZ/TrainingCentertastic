package com.trainingcentertastic.command;

import com.trainingcentertastic.entity.CourseUsers;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.CourseUsersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class TeachersCommand implements Command {

    public static final String PAGE = "WEB-INF/view/teachers.jsp";
    private final CourseUsersService courseUsersService;

    public TeachersCommand(CourseUsersService courseUsersService) {
        this.courseUsersService = courseUsersService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException, DaoException, com.google.protobuf.ServiceException {
        HttpSession session = request.getSession();

        int page = 1;
        int recordsPerPage = 2;
        String requestPage = request.getParameter("page");
        String command = request.getParameter("command");

        if(requestPage != null && !"".equals(requestPage)) {
            page = Integer.parseInt(requestPage);
        }

        List<CourseUsers> teachers = courseUsersService.getLimitByTeacher((page - 1) * recordsPerPage, recordsPerPage);

        int noOfRecords = courseUsersService.getAllTeacher().size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        session.setAttribute("teachers", teachers);
        session.setAttribute("noOfPages",noOfPages);
        session.setAttribute("currentPage", page);
        session.setAttribute("command", command);

        return CommandResult.forward(PAGE);
    }
}
