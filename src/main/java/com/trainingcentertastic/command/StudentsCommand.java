package com.trainingcentertastic.command;

import com.trainingcentertastic.entity.Student;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class StudentsCommand implements Command {

    public static final String PAGE = "WEB-INF/view/students.jsp";
    private final StudentService service;

    public StudentsCommand(StudentService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException, DaoException, com.google.protobuf.ServiceException {
        HttpSession session = request.getSession();

        int page = 1;
        int recordsPerPage = 5;
        String requestPage = request.getParameter("page");
        String command = request.getParameter("command");

        if(requestPage != null && !"".equals(requestPage)) {
            page = Integer.parseInt(requestPage);
        }

        List<Student> students = service.getLimit((page - 1) * recordsPerPage, recordsPerPage);

        int noOfRecords = service.getAll().size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        session.setAttribute("students", students);
        session.setAttribute("noOfPages",noOfPages);
        session.setAttribute("currentPage", page);
        session.setAttribute("command", command);

        return CommandResult.forward(PAGE);
    }
}
