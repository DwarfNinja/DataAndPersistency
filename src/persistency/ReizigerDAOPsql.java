package persistency;

import model.Reiziger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {
    Connection connection;
    AdresDAO adresDAO;

    public ReizigerDAOPsql(Connection connection) {
        this.connection = connection;
    }

    public void setAdresDAO(AdresDAO adresDAO) {
        this.adresDAO = adresDAO;
    }

    @Override
    public boolean save(Reiziger reiziger) {
        try {
            String query =
                    "INSERT INTO reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) " +
                    "VALUES(?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, reiziger.getReiziger_id());
            preparedStatement.setString(2, reiziger.getVoorletter());
            preparedStatement.setString(3, reiziger.getTussenvoegsel());
            preparedStatement.setString(4, reiziger.getAchternaam());
            preparedStatement.setDate(5, Date.valueOf(reiziger.getGeboortedatum()));
            preparedStatement.executeUpdate();
            if (reiziger.getAdres() != null) {
                adresDAO.save(reiziger.getAdres());
            }
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try {
            String query =
                    "UPDATE reiziger SET voorletters = ?, tussenvoegsel = ?, achternaam = ?, geboortedatum = ? " +
                            "WHERE reiziger_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, reiziger.getVoorletter());
            preparedStatement.setString(2, reiziger.getTussenvoegsel());
            preparedStatement.setString(3, reiziger.getAchternaam());
            preparedStatement.setDate(4, Date.valueOf(reiziger.getGeboortedatum()));
            preparedStatement.setInt(5, reiziger.getReiziger_id());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try {
            String query =
                    "DELETE FROM reiziger WHERE reiziger_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, reiziger.getReiziger_id());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public Reiziger findById(int id) {
        try {
            String query =
                    "SELECT * FROM reiziger WHERE reiziger_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            int reizigerId = resultSet.getInt("reiziger_id");
            String reizigerVoorletter = resultSet.getString("voorletters");
            String reizigerTussenvoegsel = resultSet.getString("tussenvoegsel") == null ? "" : String.format(" %s", resultSet.getString("tussenvoegsel"));
            String reizigerAchternaam = resultSet.getString("achternaam");
            String reizigerGeboorteDatum = resultSet.getString("geboortedatum");
            Reiziger reiziger = new Reiziger(reizigerId, reizigerVoorletter, reizigerTussenvoegsel, reizigerAchternaam, LocalDate.parse(reizigerGeboorteDatum));
            return reiziger;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        try {
            String query =
                    "SELECT * FROM reiziger WHERE geboortedatum = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf(datum));
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Reiziger> reizigerList = new ArrayList<>();
            while (resultSet.next()) {
                int reizigerId = resultSet.getInt("reiziger_id");
                String reizigerVoorletter = resultSet.getString("voorletters");
                String reizigerTussenvoegsel = resultSet.getString("tussenvoegsel") == null ? "" : String.format(" %s", resultSet.getString("tussenvoegsel"));
                String reizigerAchternaam = resultSet.getString("achternaam");
                String reizigerGeboorteDatum = resultSet.getString("geboortedatum");
                Reiziger reiziger = new Reiziger(reizigerId, reizigerVoorletter, reizigerTussenvoegsel, reizigerAchternaam, LocalDate.parse(reizigerGeboorteDatum));
                reizigerList.add(reiziger);
            }
            return reizigerList;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reiziger> findAll() {
        try {
            String query =
                    "SELECT * FROM reiziger";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Reiziger> reizigerList = new ArrayList<>();
            while (resultSet.next()) {
                int reizigerId = resultSet.getInt("reiziger_id");
                String reizigerVoorletter = resultSet.getString("voorletters");
                String reizigerTussenvoegsel = resultSet.getString("tussenvoegsel") == null ? "" : String.format(" %s", resultSet.getString("tussenvoegsel"));
                String reizigerAchternaam = resultSet.getString("achternaam");
                String reizigerGeboorteDatum = resultSet.getString("geboortedatum");
                Reiziger reiziger = new Reiziger(reizigerId, reizigerVoorletter, reizigerTussenvoegsel, reizigerAchternaam, LocalDate.parse(reizigerGeboorteDatum));
                reiziger.setAdres(adresDAO.findByReiziger(reiziger));
                reizigerList.add(reiziger);
            }
            return reizigerList;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
