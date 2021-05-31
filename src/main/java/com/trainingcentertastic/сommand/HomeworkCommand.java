package com.trainingcentertastic.сommand;

import com.trainingcentertastic.entity.Homework;
import com.trainingcentertastic.exception.DaoException;
import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.HomeworkService;
import com.trainingcentertastic.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class TaskViewerCommand implements Command {

    private final HomeworkService homeworkService;
    private final TaskService taskService;

    public TaskViewerCommand(HomeworkService homeworkService, TaskService taskService) {
        this.homeworkService = homeworkService;
        this.taskService = taskService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException, DaoException, com.google.protobuf.ServiceException {
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String nameCourse = request.getParameter("nameCourse");
        //refactor
        if(request.getParameter("taskId") != null && request.getParameter("mark") != null) {
            Long id = Long.parseLong(request.getParameter("taskId"));
            int mark = Integer.parseInt(request.getParameter("mark"));
            homeworkService.updateMark(id, username, mark);
        }
        if(request.getParameter("review") != null) {
            String review = request.getParameter("review");
        }


        List<Homework> homeworks = homeworkService.getAllHomeworksStudentByUsername(username, nameCourse);

        session.setAttribute("homeworks", homeworks);

        return CommandResult.forward("WEB-INF/view/homework.jsp");
    }


}
