package com.ti.forms;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.ti.beans.Fichier;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class CompressFile {

	private static final String CHAMP_FICHIER = "fichier";
	private static final int TAILLE_TAMPON = 1024; // 1 ko

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public Fichier compress(HttpServletRequest request, String chemin) throws IOException {
		Fichier fichier = new Fichier();
		String nomFichier = null;
		InputStream contenuFichier = null;
		try {
			Part part = request.getPart(CHAMP_FICHIER);
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

			e.printStackTrace();
			setErreur(CHAMP_FICHIER, "Ce type de requête n'est pas supporté, merci d'utiliser le formulaire prévu pour envoyer votre fichier.");
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
				compressFichier(contenuFichier, nomFichier, chemin);
			} catch (Exception e) {
				setErreur(CHAMP_FICHIER, "Failed to compress.");
			}
		}

		if (erreurs.isEmpty()) {
			resultat = "File compressed.";
		} else {
			resultat = "Failed to compress.";
		}

		return fichier;

	}

	private void compressFichier(InputStream contenu, String nomFichier, String chemin) throws Exception {
		/* Prépare les flux. */
		BufferedInputStream entree = null;
		ZipOutputStream sortie = null;
		String nomFichierZip = nomFichier + ".zip";
		try {

			entree = new BufferedInputStream(contenu, TAILLE_TAMPON);
			CheckedOutputStream checksum = new CheckedOutputStream(new FileOutputStream(new File(chemin + nomFichierZip)), new Adler32());
			sortie = new ZipOutputStream(checksum);
			sortie.setMethod(ZipOutputStream.DEFLATED);
			sortie.setLevel(9);
			ZipEntry entreeZip = new ZipEntry(nomFichier);
			sortie.putNextEntry(entreeZip);
			byte[] tampon = new byte[TAILLE_TAMPON];
			int longueur = 0;
			while ((longueur = entree.read(tampon)) > 0) {
				sortie.write(tampon, 0, longueur);
			}
		} finally {
			try {
				sortie.finish();
				sortie.close();
			} catch (IOException ignore) {
			}
			try {
				entree.close();
			} catch (IOException ignore) {
			}
		}
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
}