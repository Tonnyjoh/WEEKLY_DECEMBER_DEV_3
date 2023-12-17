package com.ti.servlets;

import java.io.IOException;

import com.ti.beans.Fichier;
import com.ti.forms.CompressFile;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Compress extends HttpServlet {

	public static final String CHEMIN = "chemin";

	public static final String ATT_FICHIER = "fichier";
	public static final String ATT_COMPRESS = "formCompress";
	public static final String VUE = "/WEB-INF/index.jsp";

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String chemin = this.getServletConfig().getInitParameter(CHEMIN);
		CompressFile compress = new CompressFile();
		Fichier fichier = compress.compress(request, chemin);
		request.setAttribute(ATT_COMPRESS, compress);
		request.setAttribute(ATT_FICHIER, fichier);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
}
