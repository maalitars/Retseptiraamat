import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.layout.BorderPane;

public class Valikvastused {
    private String pealkiri;
    private Button nupp = new Button ("Edasi");
    private Button nupp2 = new Button("Sulge");
    private int valikuid;
    private RadioButton valik1, valik2,valik3,valik4,valik5;


    public Scene tegemine(){
        BorderPane piir = new BorderPane();
        Label label = new Label(pealkiri);
        ListView<RadioButton> list = new ListView<>();
        ObservableList<RadioButton> items = FXCollections.observableArrayList();
        ToggleGroup vastused = new ToggleGroup();


        if (valikuid == 5){
            valik1.setToggleGroup(vastused);
            valik2.setToggleGroup(vastused);
            valik3.setToggleGroup(vastused);
            valik4.setToggleGroup(vastused);
            valik5.setToggleGroup(vastused);
            items.addAll(valik1,valik2,valik3,valik4,valik5);
        }
        else{

            valik1.setToggleGroup(vastused);
            valik2.setToggleGroup(vastused);
            items.addAll(valik1,valik2);
        }
        HBox hBox = new HBox();
        HBox hBox1 = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox1.setAlignment(Pos.BASELINE_CENTER);
        hBox1.setSpacing(15);
        hBox.getChildren().add(list);
        list.setItems(items);
        piir.setCenter(hBox);
        piir.setTop(label);
        hBox1.getChildren().addAll(nupp,nupp2);
        piir.setBottom(hBox1);
        Scene stseen = new Scene(piir, 300, 170);
        return stseen;
    }
    public Valikvastused(String pealkiri, RadioButton val1, RadioButton val2) {
        this.pealkiri = pealkiri;
        this.valik1 = val1;
        this.valik2 = val2;
        this.valikuid = 2;
    }
    public Valikvastused(String pealkiri,  RadioButton valik1, RadioButton valik2, RadioButton valik3, RadioButton valik4, RadioButton valik5) {
        this.pealkiri = pealkiri;
        this.valik1 = valik1;
        this.valik2 = valik2;
        this.valik3 = valik3;
        this.valik4 = valik4;
        this.valik5 = valik5;
        this.valikuid = 5;
    }

    public Button getNupp() {
        return nupp;
    }

    public Button getNupp2() {
        return nupp2;
    }
}
