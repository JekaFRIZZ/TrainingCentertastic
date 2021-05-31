package com.trainingcentertastic.command;

import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.HomeworkService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLinkCommand implements Command {

    public static final String PAGE = "WEB-INF/view/task.jsp";
    private final HomeworkService homeworkService;

    public ChangeLinkCommand(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException, DaoException, com.google.protobuf.ServiceException {
        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("name");
        String taskName = request.getParameter("taskName");
        String link = request.getParameter("link");

        homeworkService.updateLink(taskName, username, link);

        return CommandResult.forward(PAGE);
    }
}
