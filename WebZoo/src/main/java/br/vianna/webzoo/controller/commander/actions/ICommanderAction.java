package br.vianna.webzoo.controller.commander.actions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ICommanderAction {
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    boolean isAuthorized(HttpServletRequest req);
}
