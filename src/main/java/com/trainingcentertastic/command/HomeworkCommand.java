package com.trainingcentertastic.command;

import com.trainingcentertastic.entity.Homework;
import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.HomeworkService;
import com.trainingcentertastic.service.TaskService;
import com.trainingcentertastic.validator.MarkValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class HomeworkCommand implements Command {

    private static final String PAGE = "WEB-INF/view/homework.jsp";
    private final HomeworkService homeworkService;
    private final TaskService taskService;

    public HomeworkCommand(HomeworkService homeworkService, TaskService taskService) {
        this.homeworkService = homeworkService;
        this.taskService = taskService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String nameCourse = request.getParameter("nameCourse");
        if (username == null || nameCourse == null) {
            username = (String) session.getAttribute("username");
            nameCourse = (String) session.getAttribute("nameCourse");
        }

        if (request.getParameter("mark") != null) {
            Long id = Long.parseLong(request.getParameter("taskId"));
            String stringMark = request.getParameter("mark");
            if (MarkValidator.checkMark(stringMark)) {
                int mark = Integer.parseInt(stringMark);
                homeworkService.updateMark(id, username, mark);
            } else {
                request.setAttribute("incorrectMark", "Incorrect mark");
            }
        }

        if (request.getParameter("review") != null) {
            Long id = Long.parseLong(request.getParameter("taskId"));
            String review = request.getParameter("review");
            homeworkService.updateReview(id, username, review);
        }

        List<Homework> homeworks = homeworkService.getAllHomeworksStudentByUsername(username, nameCourse);

        session.setAttribute("homeworks", homeworks);
        session.setAttribute("username", username);
        session.setAttribute("nameCourse", nameCourse);

        return CommandResult.forward(PAGE);
    }
}
