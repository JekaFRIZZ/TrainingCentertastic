package com.trainingcentertastic.сommand;

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

    private final StudentService service;

    public StudentsCommand(StudentService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException, DaoException, com.google.protobuf.ServiceException {
        HttpSession session = request.getSession();

        List<Student> students = service.getAll();

        session.setAttribute("students", students);

        return CommandResult.forward("WEB-INF/view/students.jsp");
    }
}
