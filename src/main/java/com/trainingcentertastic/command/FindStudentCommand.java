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
import java.util.Optional;

public class FindStudentCommand implements Command{

    public static final String PAGE = "WEB-INF/view/students.jsp";
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


        Optional<Student> student = service.getStudentById(Long.parseLong(idString));

        if(student.isPresent()) {
            Long id = student.get().getId();
            String username = student.get().getUsername();
            String review = student.get().getReview();
            request.setAttribute("id", id);
            request.setAttribute("username", username);
            request.setAttribute("review", review);
        }
        else {
            request.setAttribute("notExist", "Student isn`t exist");
        }

        return CommandResult.forward(PAGE);
    }
}
