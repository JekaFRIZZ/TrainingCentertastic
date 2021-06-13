package com.trainingcentertastic.command;

import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.HomeworkService;
import com.trainingcentertastic.validator.LinkValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLinkCommand implements Command {

    private static final String PAGE = "WEB-INF/view/task.jsp";
    private static final String INCORRECT_LINK = "Link to github required";
    private final HomeworkService homeworkService;
    private static final LinkValidator LINK_VALIDATOR = new LinkValidator();

    public ChangeLinkCommand(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");
        String taskName = request.getParameter("taskName");
        String link = request.getParameter("link");

        if(LINK_VALIDATOR.isLink(link)) {
            homeworkService.updateLink(taskName, username, link);
        }
        else {
            request.setAttribute("incorrectLink", INCORRECT_LINK);
        }

        return CommandResult.forward(PAGE);
    }
}
