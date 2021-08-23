package com.trainingcentertastic.filter;

import com.trainingcentertastic.command.Commands;
import com.trainingcentertastic.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides access to specific commands depending on the role
 *
 * @author Eugene Lapko
 */
public class AccessFilter implements Filter {

    private static final Map<String, List<Role>> ACCESS_COMMANDS = new HashMap<>();
    private static final String ERROR_PAGE = "WEB-INF/view/errorPage.jsp";
    private static final String LOGIN_PAGE = "index.jsp";
    private static final String LOGIN = "login";

    @Override
    public void init(FilterConfig filterConfig) {
        ACCESS_COMMANDS.put(Commands.LOGIN, Arrays.asList(Role.ADMIN, Role.STUDENT, Role.TEACHER));
        ACCESS_COMMANDS.put(Commands.MAIN_PAGE, Arrays.asList(Role.ADMIN, Role.STUDENT, Role.TEACHER));
        ACCESS_COMMANDS.put(Commands.COURSES, Arrays.asList(Role.ADMIN, Role.STUDENT));
        ACCESS_COMMANDS.put(Commands.MY_PROFILE, Arrays.asList(Role.STUDENT, Role.TEACHER));
        ACCESS_COMMANDS.put(Commands.LOGOUT, Arrays.asList(Role.ADMIN, Role.STUDENT, Role.TEACHER));
        ACCESS_COMMANDS.put(Commands.CHANGE_LANGUAGE, Arrays.asList(Role.ADMIN, Role.STUDENT, Role.TEACHER));
        ACCESS_COMMANDS.put(Commands.STUDENTS, Arrays.asList(Role.ADMIN));
        ACCESS_COMMANDS.put(Commands.FIND_STUDENT, Arrays.asList(Role.ADMIN));
        ACCESS_COMMANDS.put(Commands.MY_PROFILE_STUDENT, Arrays.asList(Role.TEACHER, Role.STUDENT));
        ACCESS_COMMANDS.put(Commands.NEW_REQUIREMENT, Arrays.asList(Role.ADMIN));
        ACCESS_COMMANDS.put(Commands.SUBMIT_STUDENT, Arrays.asList(Role.STUDENT));
        ACCESS_COMMANDS.put(Commands.STUDY_COURSE, Arrays.asList(Role.STUDENT));
        ACCESS_COMMANDS.put(Commands.TASK, Arrays.asList(Role.STUDENT));
        ACCESS_COMMANDS.put(Commands.MY_COURSES_TEACHER, Arrays.asList(Role.ADMIN, Role.STUDENT, Role.TEACHER));
        ACCESS_COMMANDS.put(Commands.SUBJECT_TAUGHT, Arrays.asList(Role.TEACHER));
        ACCESS_COMMANDS.put(Commands.TASK_VIEWER, Arrays.asList(Role.TEACHER));
        ACCESS_COMMANDS.put(Commands.CHANGE_LINK, Arrays.asList(Role.STUDENT));
        ACCESS_COMMANDS.put(Commands.TEACHERS, Arrays.asList(Role.ADMIN));
        ACCESS_COMMANDS.put(Commands.COURSE, Arrays.asList(Role.ADMIN, Role.STUDENT));
        ACCESS_COMMANDS.put(Commands.FIND_TEACHER, Arrays.asList(Role.ADMIN));
        ACCESS_COMMANDS.put(Commands.DELETE_TEACHER, Arrays.asList(Role.ADMIN));
        ACCESS_COMMANDS.put(Commands.NEW_COURSE_PAGE, Arrays.asList(Role.ADMIN));
        ACCESS_COMMANDS.put(Commands.NEW_COURSE, Arrays.asList(Role.ADMIN));
        ACCESS_COMMANDS.put(Commands.CREATE_TASK, Arrays.asList(Role.TEACHER));
        ACCESS_COMMANDS.put(Commands.CREATE_TEACHER, Arrays.asList(Role.ADMIN));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpSession session = request.getSession();

        String command = request.getParameter("command");

        if(command == null) {
            RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("index.jsp");
            dispatcher.forward(servletRequest, servletResponse);
        }

        if (command.equals("changeLanguage")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }

        Role currentRole = (Role) session.getAttribute("role");
        if (currentRole == null) {
            authentication(servletRequest, servletResponse, filterChain, command);
            if (session.getAttribute("username") == null) {
                RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("index.jsp");
                dispatcher.forward(servletRequest, servletResponse);
            }
        } else {

            List<Role> roles = ACCESS_COMMANDS.get(command);
            if (roles.contains(currentRole)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                RequestDispatcher dispatcher = servletRequest.getRequestDispatcher(ERROR_PAGE);
                request.setAttribute("errorMessage", "not access");
                dispatcher.forward(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {
    }

    public void authentication(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain, String command) throws ServletException, IOException {
        if (command.equals("registrationPage")) {
            RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("WEB-INF/view/registration.jsp");
            dispatcher.forward(servletRequest, servletResponse);
        } else if (command.equals("registration")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (!command.equals(LOGIN)) {
            RequestDispatcher dispatcher = servletRequest.getRequestDispatcher(LOGIN_PAGE);
            dispatcher.forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
