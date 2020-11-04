package domein;

import enums.Bezorgwijze;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name = "Advertentie.findAll", query = "SELECT a FROM Advertentie a")
@Inheritance(strategy = InheritanceType.JOINED)
public class Advertentie extends GeneriekObject {

    private String titel;
    private String soort;
    private double prijs;

    private String omschrijving;

    @Lob @Basic(fetch = FetchType.LAZY)
    private String bijlage;

    @ElementCollection
    private List<Bezorgwijze> bezorgwijzen;

    @ManyToOne
    private Gebruiker eigenaarAdvertentie;

    public Advertentie() {
    }

    public Advertentie(String titel, String soort, double prijs, Gebruiker g) {
        this.titel = titel;
        this.soort = soort;
        this.prijs = prijs;
        this.eigenaarAdvertentie = g;
        g.addAdvertentie(this);
    }

    public void setGebruiker(Gebruiker g){
        this.eigenaarAdvertentie = g;
    }

    public String getBijlage() {
        return bijlage;
    }

    public void setBijlage(String bijlage) {
        this.bijlage = bijlage;
    }

    public List<Bezorgwijze> getBezorgwijzen() {
        return bezorgwijzen;
    }

    public void addBezorgwijze(Bezorgwijze b){
        bezorgwijzen.add(b);
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    @Override
    public String toString() {
        return "Advertentie{" +
                "titel='" + titel + '\'' +
                ", soort='" + soort + '\'' +
                ", prijs=" + prijs +
                ", omschrijving='" + omschrijving + '\'' +
                '}';
    }
}
