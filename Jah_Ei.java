import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Jah_Ei {
    private String pealkiri;
    private String valik1;
    private String valik2;
    private Button üks = new Button();
    private Button teine = new Button();

    public Scene tegemine(){
        BorderPane juur = new BorderPane();
        HBox hBox = new HBox();
        HBox hBox1 = new HBox();
        üks.setText(valik1);
        teine.setText(valik2);
        Label label = new Label(pealkiri);
        hBox.getChildren().add(label);
        hBox1.getChildren().addAll(üks,teine);
        hBox.setAlignment(Pos.CENTER);
        hBox1.setAlignment(Pos.BASELINE_CENTER);
        hBox1.setSpacing(15);
        juur.setCenter(hBox);
        juur.setBottom(hBox1);
        Scene stseen = new Scene(juur, 300,100);
        return stseen;
    }

    public Jah_Ei(String pealkiri, String valik1, String valik2) {
        this.pealkiri = pealkiri;
        this.valik1 = valik1;
        this.valik2 = valik2;
    }

    public Button getÜks() {
        return üks;
    }

    public Button getTeine() {
        return teine;
    }
}
