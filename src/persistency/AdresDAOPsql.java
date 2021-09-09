package persistency;

import model.Adres;
import model.Reiziger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql implements AdresDAO {
    Connection connection;
    ReizigerDAO reizigerDAO;

    public AdresDAOPsql(Connection connection) {
        this.connection = connection;
    }

    public ReizigerDAO getReizigerDAO() {
        return reizigerDAO;
    }

    public void setReizigerDAO(ReizigerDAO reizigerDAO) {
        this.reizigerDAO = reizigerDAO;
    }

    @Override
    public boolean save(Adres adres) {
        try {
            String query =
                    "INSERT INTO adres (adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id) " +
                            "VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, adres.getAdres_id());
            preparedStatement.setString(2, adres.getPostcode());
            preparedStatement.setString(3, adres.getHuisnummer());
            preparedStatement.setString(4, adres.getStraat());
            preparedStatement.setString(5, adres.getWoonplaats());
            preparedStatement.setInt(6, adres.getReiziger_id());
            preparedStatement.executeUpdate();
            reizigerDAO.findById(adres.getReiziger_id()).setAdres(adres);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Adres adres) {
        try {
            String query =
                    "UPDATE adres SET postcode = ?, huisnummer = ?, straat = ?, woonplaats = ?, reiziger_id = ? " +
                            "WHERE adres_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, adres.getPostcode());
            preparedStatement.setString(2, adres.getHuisnummer());
            preparedStatement.setString(3, adres.getStraat());
            preparedStatement.setString(4, adres.getWoonplaats());
            preparedStatement.setInt(5, adres.getReiziger_id());
            preparedStatement.setInt(6, adres.getAdres_id());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Adres adres) {
        try {
            String query =
                    "DELETE FROM adres WHERE adres_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, adres.getAdres_id());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public Adres findById(int id) {
        try {
            String query =
                    "SELECT * FROM adres WHERE adres_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            int adresId = resultSet.getInt("adres_id");
            String adresPostcode = resultSet.getString("postcode");
            String adresHuisnummer = resultSet.getString("huisnummer");
            String adresStraat = resultSet.getString("straat");
            String adresWoonplaats = resultSet.getString("woonplaats");
            int adresReizigerId =  resultSet.getInt("reiziger_id");
            Adres adres = new Adres(adresId, adresPostcode, adresHuisnummer, adresStraat, adresWoonplaats, adresReizigerId);
            return adres;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        try {
            String query =
                    "SELECT * FROM adres WHERE reiziger_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, reiziger.getReiziger_id());
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            int adresId = resultSet.getInt("adres_id");
            String adresPostcode = resultSet.getString("postcode");
            String adresHuisnummer = resultSet.getString("huisnummer");
            String adresStraat = resultSet.getString("straat");
            String adresWoonplaats = resultSet.getString("woonplaats");
            int adresReizigerId =  resultSet.getInt("reiziger_id");
            Adres adres = new Adres(adresId, adresPostcode, adresHuisnummer, adresStraat, adresWoonplaats, adresReizigerId);
            return adres;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Adres> findAll() {
        try {
            String query =
                    "SELECT * FROM adres";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Adres> adresList = new ArrayList<>();
            while (resultSet.next()) {
                int adresId = resultSet.getInt("adres_id");
                String adresPostcode = resultSet.getString("postcode");
                String adresHuisnummer = resultSet.getString("huisnummer");
                String adresStraat = resultSet.getString("straat");
                String adresWoonplaats = resultSet.getString("woonplaats");
                int adresReizigerId =  resultSet.getInt("reiziger_id");
                Adres adres = new Adres(adresId, adresPostcode, adresHuisnummer, adresStraat, adresWoonplaats, adresReizigerId);
                adresList.add(adres);
            }
            return adresList;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
