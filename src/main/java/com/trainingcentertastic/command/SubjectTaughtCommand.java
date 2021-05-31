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

public class SubjectTaughtCommand implements Command {

    public static final String PAGE = "WEB-INF/view/subjectTaught.jsp";
    private final StudentService studentService;

    public SubjectTaughtCommand(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException, DaoException, com.google.protobuf.ServiceException {
        HttpSession session = request.getSession();

        String nameCourse = request.getParameter("nameCourse");
        if(nameCourse == null) {
            nameCourse = (String) session.getAttribute("nameCourse");
        }

        List<Student> students = studentService.getStudentsByCourseName(nameCourse);

        session.setAttribute("students", students);
        session.setAttribute("nameCourse", nameCourse);

        return CommandResult.forward(PAGE);
    }
}
