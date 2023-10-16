package work;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import static work.Choose.ops;
import static work.Setting.showLabel;

public class Loader extends Application {
    static int numOQuestions = 0;
    static MyController controller;
    static Scene scene;
    private Button begin;
    public TextField receiver;

    public void start(Stage primaryStage) throws Exception{

        formScene();
        primaryStage.setScene(scene);
        primaryStage.setTitle("四则运算");
        primaryStage.show();
        begin.setOnAction(
                event -> {
                    try{
                        numOQuestions=Integer.parseInt(receiver.getText());
                        if(numOQuestions>100||numOQuestions<0) {
                            numOQuestions=10;
                        }
                    }catch (Exception e){
                        numOQuestions=10;
                    }
                    try {
                        controller = new MyController(numOQuestions, ops);
                    }catch (Exception e){
                        showLabel.setText("参数不可用 请重新输入");
                    };
                    try{
                        View view = new View();
                        view.start(primaryStage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
    public void formScene(){

        begin=new Button("挑战开始->");
        begin.setStyle("-fx-background-radius: 10px; -fx-border-radius: 10px;");
        Label remindLabel= new Label("出几题?(1-100) 默认10");
        receiver=new TextField();
        BorderPane core=new BorderPane();
        HBox top1=new HBox();
        top1.getChildren().add(remindLabel);
        top1.setAlignment(Pos.CENTER);
        HBox mid1=new HBox();
        mid1.getChildren().add(receiver);
        mid1.setAlignment(Pos.CENTER);
        HBox but1=new HBox();
        but1.getChildren().add(begin);
        but1.setAlignment(Pos.CENTER);
        core.setTop(top1);
        core.setCenter(mid1);
        core.setBottom(but1);
        scene=new Scene(core,340,300);
    }
}
