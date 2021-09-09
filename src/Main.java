import model.Adres;
import model.Reiziger;
import persistency.AdresDAO;
import persistency.AdresDAOPsql;
import persistency.ReizigerDAO;
import persistency.ReizigerDAOPsql;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class Main {
    static Connection connection;

    public static void main(String[] args) {
        getConnection();
        try {
            ReizigerDAOPsql reizigerDAOPsql = new ReizigerDAOPsql(connection);
            AdresDAOPsql adresDAOPsql = new AdresDAOPsql(connection);
            reizigerDAOPsql.setAdresDAO(adresDAOPsql);
            adresDAOPsql.setReizigerDAO(reizigerDAOPsql);
            testReizigerDAO(reizigerDAOPsql);
            testAdresDAO(adresDAOPsql, reizigerDAOPsql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql:ovchip", "postgres", "admin");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", LocalDate.parse(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.
        // Update aangemaakte reiziger
        System.out.println("[Test] ReizigerDAO.update() geeft de volgende resultaten na het updaten van de achternaam:\nVoor de update: " + rdao.findById(77));
        Reiziger sietskeUpdate = new Reiziger(77, "S", "", "Jansen", LocalDate.parse(gbdatum));
        rdao.update(sietskeUpdate);
        System.out.println("Na de update: " + rdao.findById(77) + "\n");

        // Vindt reiziger met gegeven ID
        System.out.println("[Test] ReizigerDAO.findbyId() met ID 77 geeft de volgende reiziger: \n" + rdao.findById(77) + "\n");

        // Vindt reizigers met gegeven geboortedatum
        System.out.println("[Test] ReizigerDAO.findByGbdatum() met geboortedatum 1981-03-14 geeft de volgende reiziger: \n" + rdao.findByGbdatum(gbdatum) + "\n");

        // Vindt delete reiziger
        System.out.println("[Test] ReizigerDAO.delete() geeft de volgende resultaten na het deleten van reiziger met ID 77");
        rdao.delete(sietskeUpdate);
        for (Reiziger reiziger : rdao.findAll()) {
            System.out.println(reiziger);
        }
        System.out.println();
    }

    private static void testAdresDAO(AdresDAO adao, ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test AdresDAO -------------");

        // Haal alle adressen op uit de database
        List<Adres> adressen = adao.findAll();
        System.out.println("[Test] AdresDAO.findAll() geeft de volgende reizigers:");
        for (Adres a : adressen) {
            System.out.println(a);
        }
        System.out.println();

        // Maak een nieuw adres aan en persisteer deze in de database
        Reiziger johndoe = new Reiziger(25, "J", "", "Doe", LocalDate.parse("1995-05-25"));
        Adres heidelberglaan = new Adres(77, "3584 CS", "15", "Heidelberglaan", "Utrecht", 25);
        johndoe.setAdres(heidelberglaan);
        rdao.save(johndoe);
        System.out.print("[Test] Eerst " + adressen.size() + " adressen, na AdresDAO.save() ");
        adressen = adao.findAll();
        System.out.println(adressen.size() + " adressen\n");

        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.
        // Update aangemaakte adres
        System.out.println("[Test] AdresDAO.update() geeft de volgende resultaten na het updaten van de achternaam:\nVoor de update: " + adao.findById(77));
        Adres heidelberglaanUpdate = new Adres(77, "3584 CS", "15", "Padualaan", "Utrecht", 25);
        adao.update(heidelberglaanUpdate);
        System.out.println("Na de update: " + adao.findById(77) + "\n");

        // Vindt reiziger met gegeven ID
        System.out.println("[Test] AdresDAO.findbyId() met ID 77 geeft de volgende reiziger: \n" + adao.findById(77) + "\n");

        // Vindt adres met gegeven reiziger
        System.out.println("[Test] AdresDAO.findByReiziger() met geboortedatum 1995-05-25 geeft de volgende reiziger: \n" + adao.findByReiziger(johndoe) + "\n");

        // Vindt delete adres
        System.out.println("[Test] AdresDAO.delete() geeft de volgende resultaten na het deleten van reiziger met ID 77");
        adao.delete(heidelberglaanUpdate);
        rdao.delete(johndoe);
        for (Adres adres : adao.findAll()) {
            System.out.println(adres);
        }
        System.out.println();
    }

    private static void AssignmentP1() {
        try {
            Connection myCon = DriverManager.getConnection("jdbc:postgresql:ovchip", "postgres", "admin");
            String query = "SELECT * FROM reiziger";
            PreparedStatement pst= myCon.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            System.out.println("Alle reizigers:");
            while (rs.next())
            {
                int reizigerIndex = rs.getInt("reiziger_id");
                String reizigerVoorletter = rs.getString("voorletters");
                String reizigerTussenvoegsel = rs.getString("tussenvoegsel") == null ? "" : String.format(" %s", rs.getString("tussenvoegsel"));
                String reizigerAchternaam = rs.getString("achternaam");
                String reizigerGeboorteDatum = rs.getString("geboortedatum");
                System.out.printf("\t #%d. %s.%s %s (%s) \n", reizigerIndex, reizigerVoorletter, reizigerTussenvoegsel, reizigerAchternaam, reizigerGeboorteDatum);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
