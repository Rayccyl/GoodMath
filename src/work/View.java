package work;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static work.Loader.controller;
import static work.Loader.scene;
import static work.Choose.chooseScene;

public class View extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {

        //Controller
        Label head = new Label("按回车也可以进到下一题");
        Label question = new Label();
        Label tail = new Label();
        TextField answer = new TextField();

        answer.setStyle("-fx-background-radius: 10px; -fx-border-radius: 10px;-fx-background-color: #99a5e8; -fx-text-fill: #691b1b;");

        Button exit = new Button("退出");
        Button next = new Button("下一题");

        //Container
        BorderPane top=new BorderPane();
        top.setLeft(exit);
        top.setCenter(head);
        top.setRight(next);
        HBox center=new HBox(5);
        center.getChildren().addAll(question,answer);
        center.setAlignment(Pos.CENTER);
        BorderPane cPane = new BorderPane();
        cPane.setTop(top);
        cPane.setCenter(center);
        HBox bottom=new HBox(tail);
        bottom.setAlignment(Pos.CENTER);
        cPane.setBottom(bottom);
        question.setText(controller.question);
        Font myfont = new Font("Cambria",80);
        question.setFont(myfont);
        answer.setFont(myfont);
        answer.setMaxWidth(500);

        Button restart=new Button("重新开始");
        VBox centerEnd=new VBox(10);
        Label endLabel=new Label();
        centerEnd.getChildren().add(endLabel);
        centerEnd.getChildren().add(restart);
        centerEnd.setAlignment(Pos.CENTER);
        Scene end=new Scene(centerEnd,300,200);

        //Show
        Scene sceneCore = new Scene(cPane,960,280);
        primaryStage.setTitle("四则运算");
        primaryStage.setScene(sceneCore);
        primaryStage.show();
        //Handle
        controller.firstQuestion();

        restart.setOnAction(
                event ->{
                    primaryStage.setScene(chooseScene);
                }
        );

        exit.setOnAction(
                event ->{
                    endLabel.setText("挑战失败");
                    primaryStage.setScene(end);
                }
        );
        next.setOnAction(
                event -> {
                    try{
                        controller.answer=Integer.parseInt(answer.getText());
                        controller.theCalc.ans=controller.answer;
                    }catch(NumberFormatException e) {
                        //handle error
                    }
                    if(controller.check(controller)){
                        controller.theNum++;
                    };
                    if(!controller.nextQuestion()){
                        endLabel.setText("共"+MyController.numOfQuestions+"题，其中对"+ controller.theNum+"题 费时间"+(controller.seconds /1000d)+"s");
                        primaryStage.setScene(end);
                    };
                    question.setText(controller.question);
                    answer.clear();
                    tail.setText("时间"+(controller.seconds /1000d)+"s，"+controller.toString());
                }
        );
        sceneCore.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                next.fire();
            }
        });
        tail.setText(controller.toString());

    }
}