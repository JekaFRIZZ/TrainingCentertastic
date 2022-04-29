package com.trainingcentertastic.command;

import com.trainingcentertastic.entity.Role;
import com.trainingcentertastic.entity.User;
import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command{

    private static final String INVALID_INPUT = "Invalid username or password";
    private static final String MAIN_PAGE = "/controller?command=mainPage";
    private static final String PAGE = "index.jsp";
    private final UserService service;

    public LoginCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(username == null || password == null) {
            return CommandResult.forward(PAGE);
        }

        Optional<User> optionalUser = service.login(username, password);

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            Role role = user.getRole();
            session.setAttribute("user", user);
            session.setAttribute("username", username);
            session.setAttribute("role", role);
            return CommandResult.forward(MAIN_PAGE);
        }
        request.setAttribute("errorMessage", INVALID_INPUT);
        return CommandResult.forward(PAGE);
    }
}
