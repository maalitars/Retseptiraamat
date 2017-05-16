import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class Tekstivastus {
    private String pealkiri;
    private Button nupp = new Button ("Edasi");
    private Button nupp2 = new Button("Sulge");
    private TextField tekst;

    public Scene tegemine(){
        BorderPane piir = new BorderPane();
        Label label = new Label(pealkiri);
        tekst = new TextField();
        HBox hBox = new HBox();
        HBox hBox1 = new HBox();

        hBox.setAlignment(Pos.CENTER);
        hBox1.setAlignment(Pos.BASELINE_CENTER);
        hBox1.setSpacing(15);
        hBox.getChildren().add(tekst);
        hBox1.getChildren().addAll(nupp,nupp2);
        piir.setCenter(hBox);
        piir.setBottom(hBox1);
        piir.setTop(label);
        Scene stseen = new Scene(piir, 300,170);
        return stseen;
    }

    public Tekstivastus(String pealkiri) {
        this.pealkiri = pealkiri;
    }

    public Button getNupp() {
        return nupp;
    }

    public Button getNupp2() {
        return nupp2;
    }

    public TextField getTekst() {
        return tekst;
    }

}
