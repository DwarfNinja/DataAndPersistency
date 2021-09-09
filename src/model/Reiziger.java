package model;

import java.time.LocalDate;

public class Reiziger {
    int reiziger_id;
    String voorletter;
    String tussenvoegsel;
    String achternaam;
    LocalDate geboortedatum;
    Adres adres;

    public Reiziger(int reiziger_id, String voorletter, String tussenvoegsel, String achternaam, LocalDate geboortedatum) {
        this.reiziger_id = reiziger_id;
        this.voorletter = voorletter;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public int getReiziger_id() {
        return reiziger_id;
    }

    public void setReiziger_id(int reiziger_id) {
        this.reiziger_id = reiziger_id;
    }

    public String getVoorletter() {
        return voorletter;
    }

    public void setVoorletter(String voorletter) {
        this.voorletter = voorletter;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(LocalDate geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    @Override
    public String toString() {
        return "Reiziger{" +
                "reiziger_id=" + reiziger_id +
                ", voornaam='" + voorletter + '\'' +
                ", tussenvoegsel='" + tussenvoegsel + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", geboortedatum=" + geboortedatum +
                ", adres=" + adres +
                '}';
    }
}
