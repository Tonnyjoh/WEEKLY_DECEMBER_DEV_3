package com.ti.forms;

import java.util.HashMap;
import java.util.Map;

import com.ti.beans.Client;
import com.ti.dao.ClientDAO;

import jakarta.servlet.http.HttpServletRequest;

public final class CreationClientForm {

	public static final String CHAMP_NOM = "nomClient";
	public static final String CHAMP_EMAIL = "emailClient";
	public static final String CHAMP_MDP = "mdpClient";
	public static final String ATT_LISTE = "listeClient";
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	private ClientDAO clientDao;

	public CreationClientForm(ClientDAO clientDao) {
		this.clientDao = clientDao;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	public Client createClient(HttpServletRequest request, String chemin) {
		String nom = getValeurChamp(request, CHAMP_NOM);
		String email = getValeurChamp(request, CHAMP_EMAIL);
		String mdp = getValeurChamp(request, CHAMP_MDP);
		Client client = new Client();
		try {
			validationMdp(mdp);
		} catch (Exception e) {
			setErreur(CHAMP_MDP, e.getMessage());
		}
		client.setMdpass(mdp);
		try {
			validationEmail(email);
			email = email.toLowerCase();
			email = email.trim();
		} catch (Exception e) {
			setErreur(CHAMP_EMAIL, e.getMessage());
		}

		client.setEmail(email);
		try {
			validationNom(nom);
		} catch (Exception e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}
		client.setNom(nom);
		if (erreurs.isEmpty()) {
			resultat = "Account created successfully.";
			System.out.println(clientDao);
			clientDao.create(client);
			System.out.println("test1");

		} else {
			resultat = "Error.";
		}

		return client;
	}

	private void validationEmail(String email) throws Exception {
		if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
			throw new Exception("Invalid mail.");
		} else if (email == null) {
			throw new Exception("Required");
		}

	}

	private void validationNom(String nom) throws Exception {
		if (nom != null && nom.length() < 2) {
			throw new Exception("2 characters min.");
		} else if (nom == null) {
			throw new Exception("Required");
		}
	}

	private void validationMdp(String nom) throws Exception {
		if (nom != null && nom.length() < 2) {
			throw new Exception("2 characters min.");
		} else if (nom == null) {
			throw new Exception("Required");
		}
	}

	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur.trim();
		}
	}

}
