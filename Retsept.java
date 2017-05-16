import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class Retsept implements Comparable<Retsept> {
    private String nimi;
    private String liik;
    private double aeg;
    private List<String> koostisosad;
    private String aadress;

    public Retsept(String nimi, String liik, double aeg, List<String> koostisosad, String aadress) {
        this.nimi = nimi;
        this.liik = liik;
        this.aeg = aeg;
        this.koostisosad = koostisosad;
        this.aadress = aadress;
    }

    public double ajakulu() {
        return aeg;
    }   //ühe retsepti ajakulu

    public int compareTo(Retsept võrreldav) {   //meetod sorteerib retseptid ajakulu järgi väiksemast suuremani
        if (ajakulu() < võrreldav.ajakulu()) {
            return -1;
        }
        if (ajakulu() > võrreldav.ajakulu()) {
            return 1;
        }
        return 0;
    }

    public String getLiik() {
        return liik;
    }


    public void võta_netist() {
       try {
           URL url = new URL(aadress);
           URI uri = url.toURI();
           Desktop.getDesktop().browse(uri);
       }

       catch (URISyntaxException | IOException e){
           throw new RuntimeException();
       }
    }
    public String getAadress() {
        return aadress;
    }

    public List<String> getKoostisosad() {
        return koostisosad;
    }

    public String getNimi() {
        return nimi;
    }
}
