import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Graafika extends Application {
    private String retsepti_nimi;
    private String retsepti_liik;
    private String retsepti_aeg;
    private String retsepti_koostisosad;
    private String retsepti_aadress;


    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        List<Retsept> söögid = retseptid("C:\\Users\\Maali\\IdeaProjects\\PROJEKT\\src\\retseptid.txt");

        Stage lava = new Stage();

        Jah_Ei esimene_aken = new Jah_Ei("Mida soovid teha?", "Süüa teha", "Sulge");
        lava.setScene(esimene_aken.tegemine());
        lava.show();
        esimene_aken.getTeine().setOnMouseClicked((MouseEvent) -> lava.close());

        esimene_aken.getÜks().setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                lava.close();
                java.util.Collections.sort(söögid);
                RadioButton valik1,valik2,valik3,valik4,valik5;
                valik1 = new RadioButton("Nimi");
                valik2 = new RadioButton("Toiduliik(soolane/magus)");
                valik3 = new RadioButton("Koostisosa");
                valik4 = new RadioButton("Valmistusaeg");
                valik5 = new RadioButton("Võta suvaline retsept");
                Valikvastused valikvastused = new Valikvastused("Sisesta, mille järgi soovid retsepti otsida!", valik1,valik2,valik3,valik4,valik5 );
                Button nupp = valikvastused.getNupp();
                Button nupp2 = valikvastused.getNupp2();
                Scene stseen = valikvastused.tegemine();
                primaryStage.setScene(stseen);
                primaryStage.show();
                nupp2.setOnMouseClicked((MouseEvent) -> primaryStage.close());
                nupp.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event)  {
                        if(valik1.isSelected()) {
                            Tekstivastus tekstivastus = new Tekstivastus("Sisesta retsepti nimi või selle osa");
                            Scene stseen1 = tekstivastus.tegemine();
                            primaryStage.setScene(stseen1);
                            primaryStage.show();
                            Button button = tekstivastus.getNupp();
                            tekstivastus.getNupp2().setOnMouseClicked((MouseEvent) -> primaryStage.close());
                            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    String nimi = tekstivastus.getTekst().getCharacters().toString();
                                    //retsepti avamine nime järgi
                                    if (!nimi.isEmpty()) {
                                        for (Retsept retsept : söögid) {
                                            retsept.nimi(nimi);
                                        }
                                        viimane_aken(primaryStage, stseen);
                                    }
                                }
                            });
                        }
                        else if (valik2.isSelected()) {
                            RadioButton val1,val2;
                            val1 = new RadioButton("Magustoit");
                            val2 = new RadioButton("Soolane");
                            Valikvastused valikvastused2 = new Valikvastused("Kas soovid teha magustoitu või soolast toitu?", val1,val2);
                            Scene stseen2 = valikvastused2.tegemine();
                            Button nupp1 = valikvastused2.getNupp();
                            primaryStage.setScene(stseen2);
                            primaryStage.show();
                            nupp1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    if (val1.isSelected()) {
                                        int arv = (int) Math.round(Math.random() * (söögid.size() - 1));
                                        while (!söögid.get(arv).getLiik().equals("magustoit")) {
                                            arv = (int) Math.round(Math.random() * (söögid.size() - 1));
                                        }
                                        söögid.get(arv).võta_netist();
                                        viimane_aken(primaryStage, stseen);
                                    }
                                    else {
                                        int arv = (int) Math.round(Math.random() * (söögid.size() - 1));
                                        while (!söögid.get(arv).getLiik().equals("soolane")) {
                                            arv = (int) Math.round(Math.random() * (söögid.size() - 1));
                                        }
                                        söögid.get(arv).võta_netist();
                                        viimane_aken(primaryStage, stseen);
                                    }
                                }
                            });
                            valikvastused2.getNupp2().setOnMouseClicked((MouseEvent) -> primaryStage.close());
                        }
                        else if(valik3.isSelected()) {
                            Tekstivastus tekstivastus = new Tekstivastus("Sisesta retsepti koostisosa");
                            Button button = tekstivastus.getNupp();
                            Scene stseen1 = tekstivastus.tegemine();
                            primaryStage.setScene(stseen1);
                            primaryStage.show();
                            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    String koostis = tekstivastus.getTekst().getCharacters().toString();
                                    if (!koostis.isEmpty()) {
                                        for (Retsept retsept : söögid) {
                                            retsept.toiduaine(koostis);
                                        }

                                        viimane_aken(primaryStage, stseen);
                                    }
                                }
                            });
                            tekstivastus.getNupp2().setOnMouseClicked((MouseEvent) -> primaryStage.close());
                        }
                        else if(valik4.isSelected()){
                            RadioButton val1, val2;
                            val1 = new RadioButton("Kiire");
                            val2 = new RadioButton("Aeglane");
                            Valikvastused valikvastused2 = new Valikvastused("Kas teha kiire või aeglase ajakuluga retsepti?",val1,val2);
                            Button button = valikvastused2.getNupp();
                            Scene stseen1 = valikvastused2.tegemine();
                            primaryStage.setScene(stseen1);
                            primaryStage.show();
                            valikvastused2.getNupp2().setOnMouseClicked((MouseEvent) -> primaryStage.close());
                            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    Stage lava = new Stage();
                                    Jah_Ei retsept_sobis = new Jah_Ei("Kas see retsept sobis?", "Jah", "Ei");
                                    lava.setScene(retsept_sobis.tegemine());
                                    lava.show();
                                    if (val1.isSelected() || val2.isSelected()) {
                                        if(val1.isSelected()) {
                                            int i = 0;
                                            söögid.get(i).võta_netist();
                                        }
                                        else {
                                            int i = 1;
                                            söögid.get(söögid.size()-i).võta_netist();
                                        }
                                        retsept_sobis.getÜks().setOnMouseClicked(new EventHandler<MouseEvent>() {
                                            @Override
                                            public void handle(MouseEvent event) {
                                                lava.close();
                                                viimane_aken(primaryStage, stseen);
                                            }
                                        });
                                        retsept_sobis.getTeine().setOnMouseClicked(new EventHandler<MouseEvent>() {
                                            int a = 1;
                                            @Override
                                            public void handle(MouseEvent event) {
                                                a++;
                                                try{
                                                    if(val1.isSelected()){
                                                        söögid.get(a).võta_netist();
                                                    }
                                                    else{
                                                        söögid.get(söögid.size()-a).võta_netist();
                                                    }
                                                }
                                                catch (IndexOutOfBoundsException e){
                                                    lava.close();
                                                    viimane_aken(primaryStage,stseen);
                                                }
                                            }
                                        });
                                    }
                                }
                            });
                        }
                        else if(valik5.isSelected()) {  // suvalise retsepti avamine
                            int arv = (int) Math.round(Math.random() * (söögid.size() - 1));  //kasutaja saab suvalise retsepti soovituse
                            söögid.get(arv).võta_netist();
                            viimane_aken(primaryStage, stseen);
                        }
                    }
                });
            }

        });
    }
    private static void viimane_aken(Stage primary, Scene stseen){
        Stage lava = new Stage();
        Jah_Ei viimane = new Jah_Ei("Kas soovid veel retsepte valida?", "Jah", "Ei");
        lava.setScene(viimane.tegemine());
        lava.show();
        viimane.getÜks().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                lava.close();
                primary.setScene(stseen);
            }
        });
        viimane.getTeine().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                lava.close();
                primary.close();
            }
        });
    }
    private static List<Retsept> retseptid (String failinimi) throws Exception { //loome failis olevatest retseptidest listi
        java.io.File fail = new java.io.File(failinimi);
        java.util.Scanner sc = new java.util.Scanner(fail, "UTF-8");
        List<Retsept> toidud = new ArrayList<>();
        while (sc.hasNextLine()){
            String rida = sc.nextLine();
            String[] tükid = rida.split("; ");
            String[] tegemisaeg = tükid[2].split(" ");
            double tunnid = Double.parseDouble(tegemisaeg[0]);
            if (tegemisaeg[1].equals("min")) {
                tunnid = tunnid / 60;
            }
            List<String> koostisosad = new ArrayList<>();
            for (String elem: tükid[3].split(", ")){
                koostisosad.add(elem);
            }
            Retsept retsept = new Retsept(tükid[0], tükid[1], tunnid, koostisosad, tükid[4]);
            toidud.add(retsept);
        }
        return toidud;
    }

    private static void failiKirjutaja(String veebiaadress) {
        try (BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("logi.txt")))){
                br.write(veebiaadress);
                br.write("\n");

        }
        catch (IOException e ){
            throw new RuntimeException();
        }

    }
}

