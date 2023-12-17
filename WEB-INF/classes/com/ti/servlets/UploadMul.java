package com.ti.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ti.beans.Client;
import com.ti.beans.Fichier;
import com.ti.forms.UploadFormMul;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/UploadMul")
@MultipartConfig
public class UploadMul extends HttpServlet {

	public static final String ATT_LISTE = "listeFichier";
	public static final String ATT_FORM = "formMul";

	public static final String CHEMIN_PRIVATE = "chemin_private";
	public static final String CHEMIN_PUBLIC = "chemin_public";

	public static final String ATT_CLIENT = "client";
	public static final String ATT_FICHIER = "fichier";
	public static final String VUE = "/WEB-INF/index.jsp";

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page d'upload */
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Fichier> lstFile = new ArrayList<Fichier>();
		HttpSession session = request.getSession();
		Client client = (Client) session.getAttribute(ATT_CLIENT);
		String chemin = this.getServletConfig().getInitParameter(CHEMIN_PUBLIC);
		if (client != null) {
			chemin = this.getServletConfig().getInitParameter(CHEMIN_PRIVATE);
		}
		UploadFormMul form = new UploadFormMul();
		lstFile = form.enregistrerFichier(request, chemin);
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_LISTE, lstFile);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

	}

}
