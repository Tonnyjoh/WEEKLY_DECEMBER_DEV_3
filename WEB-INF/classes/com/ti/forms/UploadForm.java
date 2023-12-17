package com.ti.forms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.ti.beans.Fichier;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public final class UploadForm {

	private static final String CHAMP_FICHIER = "fichier";
	private static final int TAILLE_TAMPON = 10240; // 10 ko

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public Fichier enregistrerFichier(HttpServletRequest request, String chemin) {
		Fichier fichier = new Fichier();

		String nomFichier = null;
		InputStream contenuFichier = null;
		try {
			Part part = request.getPart(CHAMP_FICHIER);
			/*
			 * determiner s'il s'agit bien d'un champ de type fichier
			 */
			nomFichier = getNomFichier(part);
			if (nomFichier != null && !nomFichier.isEmpty()) {
				nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1).substring(nomFichier.lastIndexOf('\\') + 1);
				contenuFichier = part.getInputStream();
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
			setErreur(CHAMP_FICHIER, "Les données envoyées sont trop volumineuses.");
		} catch (IOException e) {

			e.printStackTrace();
			setErreur(CHAMP_FICHIER, "Erreur de configuration du serveur.");
		} catch (ServletException e) {
			/*
			 * Exception retournée si la requête n'est pas de type multipart/form-data. Cela ne peut arriver que si l'utilisateur essaie de contacter la servlet d'upload par un
			 * formulaire différent de celui qu'on lui propose... pirate ! :|
			 */
			e.printStackTrace();
			setErreur(CHAMP_FICHIER, "Ce type de requête n'est pas supporté, merci d'utiliser le formulaire prévu pour envoyer votre fichier.");
		}

		if (erreurs.isEmpty()) {
			try {
				System.out.print("validation");
				validationFichier(nomFichier, contenuFichier);
			} catch (Exception e) {
				setErreur(CHAMP_FICHIER, e.getMessage());
			}
			fichier.setNom(nomFichier);
		}
		if (erreurs.isEmpty()) {
			try {
				ecrireFichier(contenuFichier, nomFichier, chemin);
			} catch (Exception e) {
				setErreur(CHAMP_FICHIER, "Erreur lors de l'écriture du fichier sur le disque.");
			}
		}

		if (erreurs.isEmpty()) {
			resultat = "Sent successfully.";
		} else {
			resultat = "Failed to send file.";
		}

		return fichier;
	}

	private void validationFichier(String nomFichier, InputStream contenuFichier) throws Exception {
		if (nomFichier == null || contenuFichier == null) {
			throw new Exception("File required.");
		}
	}

	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	private static String getNomFichier(Part part) {

		for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
			/* Recherche de l'éventuelle présence du paramètre "filename". */
			if (contentDisposition.trim().startsWith("filename")) {

				return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
			}
		}

		return null;
	}

	/*
	 * écrire le fichier sur le disque,
	 */
	private void ecrireFichier(InputStream contenu, String nomFichier, String chemin) throws Exception {
		/* Prépare les flux. */
		BufferedInputStream entree = null;
		BufferedOutputStream sortie = null;
		try {
			/* Ouvre les flux. */
			entree = new BufferedInputStream(contenu, TAILLE_TAMPON);
			sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);

			byte[] tampon = new byte[TAILLE_TAMPON];
			int longueur = 0;
			while ((longueur = entree.read(tampon)) > 0) {
				sortie.write(tampon, 0, longueur);
			}
		} finally {
			try {
				sortie.close();
			} catch (IOException ignore) {
			}
			try {
				entree.close();
			} catch (IOException ignore) {
			}
		}
	}
}