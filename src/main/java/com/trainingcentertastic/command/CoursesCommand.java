package com.trainingcentertastic.command;

import com.trainingcentertastic.entity.Course;
import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CoursesCommand implements Command, Paginating {

    private static final String PAGE = "WEB-INF/view/courses.jsp";
    private final CourseService service;

    public CoursesCommand(CourseService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String command = request.getParameter("command");

        request.setAttribute("command", command);

        paginate(request, response);

        return CommandResult.forward(PAGE);
    }

    @Override
    public void paginate(HttpServletRequest request, HttpServletResponse response, String... params) throws ServiceException {
        int page = 1;
        String requestPage = request.getParameter("page");

        if(requestPage != null && !requestPage.isEmpty()) {
            page = Integer.parseInt(requestPage);
        }

        List<Course> courses = service.getLimit((page - 1) * RECORDS_PER_PAGE_FOR_COURSES, RECORDS_PER_PAGE_FOR_COURSES);

        int noOfRecords = service.getAll().size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE_FOR_COURSES);

        request.setAttribute("courses", courses);
        request.setAttribute("noOfPages",noOfPages);
        request.setAttribute("currentPage", page);
    }
}
