import java.sql.*;

public class Main {

    public static void main(String[] args) {
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
