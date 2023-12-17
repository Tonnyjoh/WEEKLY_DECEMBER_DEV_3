package com.ti.forms;

import java.util.HashMap;
import java.util.Map;

import com.ti.beans.Client;
import com.ti.dao.ClientDAO;

import jakarta.servlet.http.HttpServletRequest;

public class ConnexionForm {

	public static final String CHAMP_EMAIL = "emailClient";
	public static final String CHAMP_MDP = "mdpClient";
	public static final String ATT_LISTE = "listeClient";
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	private Map<Long, Client> listeClients;
	private ClientDAO clientDao;

	public ConnexionForm(ClientDAO clientDao) {
		this.clientDao = clientDao;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public Map<Long, Client> getListeClients() {
		return this.listeClients;
	}

	public String getResultat() {
		return resultat;
	}

	public Client connecterUtilisateur(HttpServletRequest request) throws Exception {

		String email = getValeurChamp(request, CHAMP_EMAIL);
		String mdp = getValeurChamp(request, CHAMP_MDP);

		Client clientTest = null;
		try {
			validationEmail(email);
		} catch (Exception e) {
			setErreur(CHAMP_EMAIL, e.getMessage());
		}
		email = email.toLowerCase();
		email = email.trim();

		if (erreurs.isEmpty()) {
			clientTest = clientDao.find(email);
			if (clientTest != null) {

				System.out.println("Client password: " + clientTest.getMdpass());
				System.out.println("Entered password: " + mdp);

				if (clientTest.getMdpass().equals(mdp)) {
					resultat = "Connected.";
					System.out.println(resultat);
				} else {
					setErreur(CHAMP_MDP, "Invalid password.");
					System.out.println("Passwords do not match.");
					clientTest = null;
				}
			} else {
				setErreur(CHAMP_EMAIL, "Unknown mail.");
			}

		} else {

			resultat = "Failed to connect.";
		}
		return clientTest;
	}

	private void validationEmail(String email) throws Exception {
		if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
			throw new Exception("Invalid mail.");
		} else if (email == null) {
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
