package com.trainingcentertastic.command;

import com.trainingcentertastic.entity.Course;
import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MyProfileStudentCommand implements Command, Paginating {

    private static final String PAGE = "WEB-INF/view/myProfile.jsp";
    private final CourseService courseService;


    public MyProfileStudentCommand(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();

        String command = request.getParameter("command");
        String username = (String) session.getAttribute("username");

        paginate(request, response, username);
        request.setAttribute("command", command);

        return CommandResult.forward(PAGE);
    }

    @Override
    public void paginate(HttpServletRequest request, HttpServletResponse response, String... params) throws ServiceException {
        int page = 1;
        String requestPage = request.getParameter("page");

        if(requestPage != null && !"".equals(requestPage)) {
            page = Integer.parseInt(requestPage);
        }

        List<Course> courses = courseService.getLimitByUsername((page - 1) * RECORDS_PER_PAGE_FOR_COURSES,
                RECORDS_PER_PAGE_FOR_COURSES, params[0]);

        int noOfRecords = courses.size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE_FOR_COURSES);

        request.setAttribute("courses", courses);
        request.setAttribute("noOfPages",noOfPages);
        request.setAttribute("currentPage", page);
    }
}
