package com.trainingcentertastic.command;

import com.trainingcentertastic.entity.User;
import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.CourseService;
import com.trainingcentertastic.service.UserService;
import com.trainingcentertastic.validator.NameCourseValidator;
import com.trainingcentertastic.validator.NameValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class NewCourseCommand implements Command {
    private static final String PAGE = "WEB-INF/view/newCourse.jsp";
    private final CourseService courseService;
    private final UserService userService;

    public NewCourseCommand(CourseService courseService, UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String courseName = request.getParameter("newCourseName");
        String courseRequirement = request.getParameter("newCourseRequirement");
        String username = request.getParameter("teachers");
        if (courseName == null || courseRequirement == null || username == null) {
            return CommandResult.forward(PAGE);
        }

        if(!NameCourseValidator.check(courseName)) {
            request.setAttribute("invalidName", "Invalid name");
            return CommandResult.forward(PAGE);
        }

        Optional<User> optionalUser = userService.getTeacherByUsername(username);
        if (!optionalUser.isEmpty()) {
            try {
                courseService.addTeacher(courseName, username);
                courseService.createCourse(courseName, courseRequirement, username);
                request.setAttribute("successCreateCourse", "Course created!");
            } catch (ServiceException e) {
                request.setAttribute("notUniqueCourseName", "Course name is not unique");
            }
        } else {
            request.setAttribute("courseNotCreated", "Course not created");
        }

        return CommandResult.forward(PAGE);
    }
}
