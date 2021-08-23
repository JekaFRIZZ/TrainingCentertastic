package com.trainingcentertastic.command;

import com.trainingcentertastic.entity.Course;
import com.trainingcentertastic.entity.User;
import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.parser.RequirementParser;
import com.trainingcentertastic.service.CourseService;
import com.trainingcentertastic.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class CourseCommand implements Command {
    private static final String PAGE = "WEB-INF/view/course.jsp";
    private final CourseService courseService;
    private final UserService userService;
    private final RequirementParser requirementParser;

    public CourseCommand(CourseService courseService, UserService userService, RequirementParser requirementParser) {
        this.courseService = courseService;
        this.userService = userService;
        this.requirementParser = requirementParser;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        String name = request.getParameter("nameCourse");
        if(name == null) {
            name = (String) session.getAttribute("nameCourse");
        }

        Optional<Course> courseOptional = courseService.getCourseByName(name);

        Course course = courseOptional.get();
        String oneStringRequirements = course.getRequirement();

        List<String> requirements = requirementParser.parseRequirement(oneStringRequirements);
        List<User> students = userService.getStudentsByCourseName(name);

        session.setAttribute("nameCourse", course.getName());
        request.setAttribute("requirements", requirements);
        request.setAttribute("students", students);

        return CommandResult.forward(PAGE);
    }
}
