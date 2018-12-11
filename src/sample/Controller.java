package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class Controller {
    // VIEWS
    @FXML private TextField colorInput, startCurve;
    @FXML private Button drawButton;


    public Main main;


    public void setMain(Main main){
        this.main = main;
    }

    @FXML
    public void testMethod(){
        String color = colorInput.getText();
        int start_curve = Integer.parseInt(startCurve.getText());

        System.out.println("Color: " + color);
        System.out.println("Start Kurve: " + start_curve);
    }
}
