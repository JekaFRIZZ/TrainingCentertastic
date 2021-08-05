package com.trainingcentertastic.command;

import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewCourseCommand implements Command {
    private static final String PAGE = "WEB-INF/view/newCourse.jsp";
    private final CourseService courseService;

    public NewCourseCommand(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String courseName = request.getParameter("newCourseName");
        String courseRequirement = request.getParameter("newCourseRequirement");
        String username = request.getParameter("teachers");
        System.out.println(username);
        if (courseName == null || courseRequirement == null || username == null) {
            return CommandResult.forward(PAGE);
        }

        try {
            courseService.createCourse(courseName, courseRequirement, username);
            request.setAttribute("successCreateCourse", "Course created!");
        } catch (ServiceException e) {
            request.setAttribute("notUniqueCourseName", "Course name is not unique");
        }

        return CommandResult.forward(PAGE);
    }
}
