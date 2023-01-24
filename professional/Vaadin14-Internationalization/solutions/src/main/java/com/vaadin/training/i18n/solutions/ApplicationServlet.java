package com.vaadin.training.i18n.solutions;

import com.vaadin.flow.server.Constants;
import com.vaadin.flow.server.VaadinServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/*",
        initParams = {@WebInitParam(name = Constants.I18N_PROVIDER, value = "com.vaadin.training.i18n.solutions.TranslationProvider")})
public class ApplicationServlet extends VaadinServlet {
}
