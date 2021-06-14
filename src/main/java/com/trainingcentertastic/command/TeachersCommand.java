package com.trainingcentertastic.command;

import com.trainingcentertastic.entity.Role;
import com.trainingcentertastic.entity.User;
import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TeachersCommand implements Command, Paginating {

    private static final String PAGE = "WEB-INF/view/teachers.jsp";
    private final UserService userService;

    public TeachersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String command = request.getParameter("command");

        paginate(request, response);
        request.setAttribute("command", command);

        return CommandResult.forward(PAGE);
    }

    @Override
    public void paginate(HttpServletRequest request, HttpServletResponse response, String... params) throws ServiceException {
        int page = 1;

        String requestPage = request.getParameter("page");

        if(requestPage != null && !requestPage.isEmpty()) {
            page = Integer.parseInt(requestPage);
        }

        List<User> teachers = userService.getLimitByRole(Role.TEACHER,(page - 1) * RECORDS_PER_PAGE, RECORDS_PER_PAGE);

        int noOfRecords = userService.getAllByRole(Role.TEACHER).size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE);

        request.setAttribute("teachers", teachers);
        request.setAttribute("noOfPages",noOfPages);
        request.setAttribute("currentPage", page);
    }
}
