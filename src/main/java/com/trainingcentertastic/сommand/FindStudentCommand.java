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

public class FindStudentCommand implements Command{

    private final StudentService service;

    public FindStudentCommand(StudentService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException, DaoException, com.google.protobuf.ServiceException {
        HttpSession session = request.getSession();
        String idString = request.getParameter("idStudent");

        if(idString == null) {
            return CommandResult.forward("WEB-INF/view/students.jsp");
        }

        Student student = service.getStudentById(Long.parseLong(idString)).get();

        Long id = student.getId();
        String username = student.getUsername();
        String review = student.getReview();

        session.setAttribute("id", id);
        session.setAttribute("username", username);
        session.setAttribute("review", review);

        return CommandResult.forward("WEB-INF/view/students.jsp");
    }
}
