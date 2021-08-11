package com.trainingcentertastic.filter;

import com.trainingcentertastic.entity.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

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
        ACCESS_COMMANDS.put(LOGIN, Arrays.asList(Role.ADMIN, Role.STUDENT, Role.TEACHER));
        ACCESS_COMMANDS.put("mainPage", Arrays.asList(Role.ADMIN, Role.STUDENT, Role.TEACHER));
        ACCESS_COMMANDS.put("courses", Arrays.asList(Role.ADMIN, Role.STUDENT));
        ACCESS_COMMANDS.put("myProfile", Arrays.asList(Role.STUDENT, Role.TEACHER));
        ACCESS_COMMANDS.put("logout", Arrays.asList(Role.ADMIN, Role.STUDENT, Role.TEACHER));
        ACCESS_COMMANDS.put("changeLanguage", Arrays.asList(Role.ADMIN, Role.STUDENT, Role.TEACHER));
        ACCESS_COMMANDS.put("students", Arrays.asList(Role.ADMIN));
        ACCESS_COMMANDS.put("findStudent", Arrays.asList(Role.ADMIN));
        ACCESS_COMMANDS.put("myProfileStudent", Arrays.asList(Role.TEACHER, Role.STUDENT));
        ACCESS_COMMANDS.put("newRequirement", Arrays.asList(Role.ADMIN));
        ACCESS_COMMANDS.put("submitStudent", Arrays.asList(Role.STUDENT));
        ACCESS_COMMANDS.put("studyCourse", Arrays.asList(Role.STUDENT));
        ACCESS_COMMANDS.put("task", Arrays.asList(Role.STUDENT));
        ACCESS_COMMANDS.put("myCoursesTeacher", Arrays.asList(Role.ADMIN, Role.STUDENT, Role.TEACHER));
        ACCESS_COMMANDS.put("subjectTaught", Arrays.asList(Role.TEACHER));
        ACCESS_COMMANDS.put("taskViewer", Arrays.asList(Role.TEACHER));
        ACCESS_COMMANDS.put("changeLink", Arrays.asList(Role.STUDENT));
        ACCESS_COMMANDS.put("teachers", Arrays.asList(Role.ADMIN));
        ACCESS_COMMANDS.put("course", Arrays.asList(Role.ADMIN, Role.STUDENT));
        ACCESS_COMMANDS.put("findTeacher", Arrays.asList(Role.ADMIN));
        ACCESS_COMMANDS.put("deleteTeacher", Arrays.asList(Role.ADMIN));
        ACCESS_COMMANDS.put("newCoursePage", Arrays.asList(Role.ADMIN));
        ACCESS_COMMANDS.put("newCourse", Arrays.asList(Role.ADMIN));
        ACCESS_COMMANDS.put("createTask", Arrays.asList(Role.TEACHER));
        ACCESS_COMMANDS.put("createTeacher", Arrays.asList(Role.ADMIN));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        String command = request.getParameter("command");

        if (command.equals("changeLanguage")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }

        Role currentRole = (Role) session.getAttribute("role");
        if (currentRole == null) {
            authentication(servletRequest, servletResponse, filterChain, command);
        } else {
            if (session.getAttribute("username") == null) {
                RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("index.jsp");
                dispatcher.forward(servletRequest, servletResponse);
            }

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
        //Move to a separate method for verification command
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
