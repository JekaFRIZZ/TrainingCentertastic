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

public class NewRequirementCommand implements Command {

    public static final String PAGE = "WEB-INF/view/course.jsp";
    private final CourseService service;

    public NewRequirementCommand(CourseService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException, DaoException, com.google.protobuf.ServiceException {
        HttpSession session = request.getSession();

        String name = (String) session.getAttribute("nameCourse");
        String requirement = request.getParameter("newRequirement");

        Course course = service.getCourseByName(name).get();
        service.updateRequirement(requirement, course.getName());
        course = service.getCourseByName(name).get();

        session.setAttribute("course", course);
        request.setAttribute("successRequirement", "Requirements changed!");
        return CommandResult.forward(PAGE);
    }
}
