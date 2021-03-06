package com.trainingcentertastic.command;

import com.trainingcentertastic.entity.Course;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class MyCoursesTeacherCommand implements Command {

    private static final String PAGE = "WEB-INF/view/myProfile.jsp";
    private final CourseService courseService;

    public MyCoursesTeacherCommand(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");
        String nameCourse = request.getParameter("nameCourse");

        List<Course> courses = courseService.getCoursesByUsername(username);

        request.setAttribute("courses", courses);
        request.setAttribute("nameCourse", nameCourse);

        return CommandResult.forward(PAGE);
    }
}
