package work;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.Integer.parseInt;
import static work.Choose.*;

public class Setting {
    @FXML
    private Button yes;
    private CheckBox[] checkBoxes;
    private TextField[] textFields;
    public static Label showLabel;
    boolean nextPage = true;
    public void setScene(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SettingScene.fxml"));
        Scene settingScene = new Scene(loader.load());
        stage.setScene(settingScene);
        stage.show();

        AnchorPane root = loader.getRoot();
        BorderPane borderPane= (BorderPane) root.getChildren().get(0);
        checkBoxes= new CheckBox[4];
        GridPane gridPane= (GridPane)borderPane.getCenter();
        Setting.showLabel=(Label)root.getChildren().get(1);
        checkBoxes[0]= (CheckBox) gridPane.getChildren().get(7);
        checkBoxes[1]= (CheckBox) gridPane.getChildren().get(4);
        checkBoxes[2]= (CheckBox) gridPane.getChildren().get(5);
        checkBoxes[3]= (CheckBox) gridPane.getChildren().get(6);
        HBox theButton=(HBox)root.getChildren().get(2);
        yes= (Button) theButton.getChildren().get(1);
        Button back=(Button) theButton.getChildren().get(0);
        int index = 0;
        textFields= new TextField[16];
        for (Node node : gridPane.getChildren()) {
            if (node instanceof TextField) {
                textFields[index++] = (TextField) node;
            }
        }
        back.setOnAction(event -> {
            stage.setScene(chooseScene);
            stage.show();
        });
        yes.setOnAction(event -> {
            nextPage = true;
            ops=new boolean[]{false,false,false,false};
            difficulty=new Param[4];
            for(int i=0; i<4; i++){
                ops[i]=checkBoxes[i].isSelected();
                difficulty[i]=new Param();
            }
            for(int i=0; i<4; i++){
                if(ops[i]) {
                    try {
                        difficulty[i].setMinX(getNum(textFields[i]));
                        difficulty[i].setMinY(getNum(textFields[i]));
                        difficulty[i].setMaxX(getNum(textFields[i+4]));
                        difficulty[i].setMaxY(getNum(textFields[i+4]));
                        difficulty[i].setMinZ(getNum(textFields[i+8]));
                        difficulty[i].setMaxZ(getNum(textFields[i+12]));
                        if(difficulty[i].isInvalid()){
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        showLabel.setText("参数不可用 请重新输入");
                        nextPage=false;
                    }
                }
            }
            try {
                checkException();
            } catch (Exception e) {
                showLabel.setText("参数不可用 请重新输入");
                nextPage=false;
            }
            if(nextPage)Choose.goLoader(stage);
        });
    }
    public int getNum(TextField textField)throws RuntimeException{
        return new Integer(parseInt(textField.getText()));
    }
    public static void checkException ()throws Exception{
        if(ops[0]==false&&ops[1]==false&&ops[2]==false&&ops[3]==false){
            throw new Exception();
        }
    }
}
