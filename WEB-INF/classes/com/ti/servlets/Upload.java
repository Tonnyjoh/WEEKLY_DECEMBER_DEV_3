package com.ti.servlets;

import java.io.IOException;

import com.ti.beans.Client;
import com.ti.beans.Fichier;
import com.ti.forms.UploadForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Upload extends HttpServlet {

	public static final String CHEMIN_PRIVATE = "chemin_private";
	public static final String CHEMIN_PUBLIC = "chemin_public";

	public static final String ATT_CLIENT = "client";
	public static final String ATT_FICHIER = "fichier";
	public static final String ATT_FORM = "formUpload";

	public static final String VUE = "/WEB-INF/index.jsp";

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page d'upload */
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Client client = (Client) session.getAttribute(ATT_CLIENT);
		String chemin = this.getServletConfig().getInitParameter(CHEMIN_PUBLIC);
		if (client != null) {
			chemin = this.getServletConfig().getInitParameter(CHEMIN_PRIVATE);
		}
		System.out.println(chemin);
		UploadForm form = new UploadForm();

		/* Traitement de la requête et récupération du bean en résultant */
		Fichier fichier = form.enregistrerFichier(request, chemin);

		/* Stockage du formulaire et du bean dans l'objet request */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_FICHIER, fichier);

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

}