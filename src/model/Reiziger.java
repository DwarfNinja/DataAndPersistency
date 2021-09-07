package model;

import java.time.LocalDate;

public class Reiziger {
    int reiziger_id;
    String postcode;
    String tussenvoegsel;
    String achternaam;
    LocalDate geboortedatum;

    public Reiziger(int reiziger_id, String postcode, String tussenvoegsel, String achternaam, LocalDate geboortedatum) {
        this.reiziger_id = reiziger_id;
        this.postcode = postcode;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public int getReiziger_id() {
        return reiziger_id;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }
}
