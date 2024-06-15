package com.nhnacademy.shoppingmall.common.mvc.servlet;

import com.nhnacademy.shoppingmall.common.mvc.exception.ControllerNotFoundException;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.common.mvc.view.ViewResolver;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.mvc.controller.ControllerFactory;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = {"*.do"})
@MultipartConfig
public class FrontServlet extends HttpServlet {
    private ControllerFactory controllerFactory;
    private ViewResolver viewResolver;

    @Override
    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        controllerFactory = (ControllerFactory) servletContext.getAttribute(ControllerFactory.CONTEXT_CONTROLLER_FACTORY_NAME);
        if (controllerFactory == null) {
            throw new IllegalStateException("ControllerFactory is null");
        }
        viewResolver = new ViewResolver();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        try {
            DbConnectionThreadLocal.initialize();

            BaseController baseController = (BaseController) controllerFactory.getController(req);
            if (baseController == null) {
                throw new ControllerNotFoundException("Controller not found for request: " + req.getRequestURI());
            }
            String viewName = baseController.execute(req, resp);

            if (viewResolver.isRedirect(viewName)) {
                String redirectUrl = viewResolver.getRedirectUrl(viewName);
                log.debug("redirectUrl: {}", redirectUrl);
                resp.sendRedirect(redirectUrl);
            } else {
                String layout = viewResolver.getLayOut(viewName);
                log.debug("viewName: {}", viewResolver.getPath(viewName));
                req.setAttribute(ViewResolver.LAYOUT_CONTENT_HOLDER, viewResolver.getPath(viewName));
                RequestDispatcher rd = req.getRequestDispatcher(layout);
                rd.include(req, resp);
            }
        } catch (Exception e) {
            log.error("error: {}", e);
            DbConnectionThreadLocal.setSqlError(true);

            req.setAttribute("errorMessage", e.getMessage());
            req.setAttribute("error", e);
            req.setAttribute("status", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/error/error.jsp");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            DbConnectionThreadLocal.reset();
        }
    }
}
