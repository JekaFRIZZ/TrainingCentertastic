package com.trainingcentertastic.command;

import com.trainingcentertastic.entity.Course;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.CourseService;
import com.trainingcentertastic.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class MyProfileStudentCommand implements Command {

    public static final String PAGE = "WEB-INF/view/myProfile.jsp";
    private final CourseService courseService;
    private final StudentService studentService;

    public MyProfileStudentCommand(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException, DaoException, com.google.protobuf.ServiceException {
        HttpSession session = request.getSession();


        String username = (String) session.getAttribute("name");

        List<Course> courses = courseService.getCoursesByUsername(username);

        session.setAttribute("courses", courses);

        return CommandResult.forward(PAGE);
    }
}
