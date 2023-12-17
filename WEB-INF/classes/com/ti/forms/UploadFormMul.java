package com.ti.forms;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ti.beans.Fichier;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class UploadFormMul {

	private static final String CHAMP_FICHIER = "fichier";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public List<Fichier> enregistrerFichier(HttpServletRequest request, String chemin) throws IOException, ServletException {
		List<Fichier> listFile = new ArrayList<Fichier>();
		Fichier fichier = new Fichier();
		// Récupérer la collection de parties (fichiers) de la requête et traitement
		Collection<Part> parts = request.getParts();
		for (Part part : parts) {
			String nomFichier = null;
			InputStream contenuFichier = null;
			try {
				nomFichier = getFileName(part);
				if (nomFichier != null && !nomFichier.isEmpty()) {
					contenuFichier = part.getInputStream();
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
				setErreur(CHAMP_FICHIER, "Les données envoyées sont trop volumineuses.");
			} catch (IOException e) {
				e.printStackTrace();
				setErreur(CHAMP_FICHIER, "Erreur de configuration du serveur.");
			}

			if (erreurs.isEmpty()) {

				try {
					validationFichier(nomFichier, contenuFichier);
				} catch (Exception e) {
					setErreur(CHAMP_FICHIER, e.getMessage());
				}
				fichier.setNom(nomFichier);
			}
			if (erreurs.isEmpty()) {
				try {
					// Sauvegarder le fichier
					listFile.add(fichier);
					part.write(chemin + nomFichier);
				} catch (Exception e) {
					setErreur(CHAMP_FICHIER, "Erreur lors de l'écriture du fichier sur le disque.");
				}
			}

			if (erreurs.isEmpty()) {
				resultat = "Sent successfully.";
			} else {
				resultat = "Failed to send file.";
			}
		}
		return listFile;

	}

	private void validationFichier(String nomFichier, InputStream contenuFichier) throws Exception {
		if (nomFichier == null || contenuFichier == null) {
			throw new Exception("File required.");
		}
	}

	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	private String getFileName(final Part part) {
		final String partHeader = part.getHeader("content-disposition");
		for (String content : partHeader.split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
}
