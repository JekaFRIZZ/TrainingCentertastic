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
import java.util.Optional;

public class SubmitStudentCommand implements Command {

    private static final String PAGE = "WEB-INF/view/course.jsp";
    private final CourseUsersService courseUsersService;

    public SubmitStudentCommand(CourseUsersService courseUsersService) {
        this.courseUsersService = courseUsersService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");
        String nameCourse = request.getParameter("nameCourse");

        Optional<CourseUsers> courseUsersOptional = courseUsersService.checkSubmit(nameCourse, username);

        if(courseUsersOptional.isPresent()) {
            request.setAttribute("submit", "You are already subscribed to this course");
        }
        else {
            courseUsersService.updateSubmit(nameCourse, username);
            request.setAttribute("submit", "Submit is succeed");
        }

        return CommandResult.forward(PAGE);
    }
}
