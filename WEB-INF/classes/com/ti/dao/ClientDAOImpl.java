package com.ti.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.ti.beans.Client;

public class ClientDAOImpl implements ClientDAO {

	private static final String SQL_SELECT = "SELECT id, nom, email,mdpass FROM Client ORDER BY id";
	private static final String SQL_SELECT_PAR_EMAIL = "SELECT id, nom,email,mdpass FROM Client WHERE email = ?";
	private static final String SQL_INSERT = "INSERT INTO Client (nom, email,mdpass) VALUES (?,?,?)";
	private static final String SQL_DELETE_PAR_ID = "DELETE FROM Client WHERE id = ?";
	private DAOFactory daoFactory;

	ClientDAOImpl(DAOFactory daoFact) {
		this.daoFactory = daoFact;
	}

	@Override
	public void create(Client client) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_INSERT, true, client.getNom(), client.getEmail(), client.getMdpass());
			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new DAOException("Échec de la création du client, aucune ligne ajoutée dans la table.");
			}
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {
				client.setId(valeursAutoGenerees.getLong(1));
			} else {
				throw new DAOException("Échec de la création du client en base, aucun ID auto-généré retourné.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}
	}

	@Override
	public Client find(String email) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Client client = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT_PAR_EMAIL, false, email);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				client = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return client;

	}

	@Override
	public void delete(long id) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_DELETE_PAR_ID, false, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(preparedStatement, connexion);
		}

	}

	@Override
	public Map show() throws DAOException {

		Map<Long, Client> listeClients = new HashMap<Long, Client>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connexion = daoFactory.getConnection();

			preparedStatement = DAOUtilitaire.initialisationRequetePreparee(connexion, SQL_SELECT, false);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Client client = null;
				client = map(resultSet);
				listeClients.put(client.getId(), client);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return listeClients;
	}

	private static Client map(ResultSet resultSet) throws SQLException {
		Client utilisateur = new Client();
		utilisateur.setId(resultSet.getLong("id"));
		utilisateur.setEmail(resultSet.getString("email"));
		utilisateur.setNom(resultSet.getString("nom"));
		utilisateur.setMdpass(resultSet.getString("mdpass"));

		return utilisateur;
	}
}
