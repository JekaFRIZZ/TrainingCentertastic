package com.trainingcentertastic.command;

import com.trainingcentertastic.entity.Course;
import com.trainingcentertastic.entity.User;
import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.parser.RequirementParser;
import com.trainingcentertastic.service.CourseService;
import com.trainingcentertastic.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class NewRequirementCommand implements Command {

    private static final String PAGE = "WEB-INF/view/course.jsp";
    private final CourseService courseService;
    private final UserService userService;
    private final RequirementParser requirementParser;

    public NewRequirementCommand(CourseService courseService, UserService userService, RequirementParser requirementParser) {
        this.courseService = courseService;
        this.userService = userService;
        this.requirementParser = requirementParser;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String nameCourse = (String) request.getSession().getAttribute("nameCourse");
        String requirement = request.getParameter("newRequirement");

        if(requirement == null) {
            Course course = courseService.getCourseByName(nameCourse).get();
            requirement = course.getRequirement();
            List<String> requirements = requirementParser.parseRequirement(requirement);
            List<User> students = userService.getStudentsByCourseName(nameCourse);
            request.setAttribute("students", students);
            request.setAttribute("requirements",requirements);
            return CommandResult.forward(PAGE);
        }

        List<User> students = userService.getStudentsByCourseName(nameCourse);
        Course course = courseService.getCourseByName(nameCourse).get();
        courseService.updateRequirement(requirement, course.getName());
        course = courseService.getCourseByName(nameCourse).get();
        List<String> requirements = requirementParser.parseRequirement(requirement);

        request.getSession().setAttribute("course", course);
        request.setAttribute("students", students);
        request.getSession().setAttribute("nameCourse", nameCourse);
        request.setAttribute("requirements",requirements);
        request.setAttribute("successRequirement", "Requirements changed!");

        return CommandResult.forward(PAGE);
    }
}
