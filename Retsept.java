import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
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


    public void toiduaine(String koostis){   //kui kasutaja sisestatud koostisosa sisaldub retseptis, avatakse retsepti veebileht
        for (int i = 0; i < koostisosad.size(); i++) {
            if (koostisosad.get(i).contains(koostis)) {
                võta_netist();
                //kui ühes retseptis on mitu sama nimega koostisosa?
            }
        }
    }

    public void nimi(String retseptinimi){  //kui kasutaja sisestatud otsingusõna sisaldub retsepti nimes, avatakse retsepti veebileht
        if (nimi.contains(retseptinimi)) {
            võta_netist();
        }
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
}
