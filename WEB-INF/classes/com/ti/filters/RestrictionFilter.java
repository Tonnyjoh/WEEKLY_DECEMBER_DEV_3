package com.ti.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RestrictionFilter implements Filter {

	public static final String ACCES_CONNEXION = "/home";

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String chemin = request.getRequestURI().substring(request.getContextPath().length());
		if (chemin.startsWith("/inc")) {
			chain.doFilter(request, response);
			return;
		}

		request.getRequestDispatcher(ACCES_CONNEXION).forward(request, response);

	}

	@Override
	public void destroy() {
	}
}