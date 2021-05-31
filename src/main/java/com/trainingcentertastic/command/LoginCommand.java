package com.trainingcentertastic.command;

import com.trainingcentertastic.entity.User;
import com.trainingcentertastic.exception.ServiceException;
import com.trainingcentertastic.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class LoginCommand implements Command{

    public static final String INVALID_INPUT = "Invalid username or password";
    public static final String MAIN_PAGE = "/controller?command=mainPage";
    public static final String PAGE = "index.jsp";
    private final UserService service;

    public LoginCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(username == null || password == null) {
            return CommandResult.redirect("index.jsp");
        }

        Optional<User> optionalUser = service.login(username, password);

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            HttpSession session = request.getSession();
            session.setAttribute("name", user.getUsername());
            session.setAttribute("role", user.getRole());

            return CommandResult.forward(MAIN_PAGE);
        }
        request.getSession().setAttribute("errorMessage", INVALID_INPUT);
        return CommandResult.redirect(PAGE);


    }
}
