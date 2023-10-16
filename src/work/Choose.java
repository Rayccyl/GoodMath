package work;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

import static work.Loader.scene;

public class Choose extends Application {

    public static Param difficulty[];
    public static boolean[] ops;
    public static Scene chooseScene;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button level1=new Button("初级");
        Button level2=new Button("中级");
        Button level3=new Button("高级");
        Button custom=new Button("自定义");
        VBox chooseVBox=new VBox(20);
        chooseVBox.getChildren().addAll(level1,level2,level3,custom);
        chooseVBox.setAlignment(Pos.CENTER);
        chooseScene=new Scene(chooseVBox,280,320);

        primaryStage.setScene(chooseScene);
        primaryStage.setTitle("四则运算");
        primaryStage.show();

        ops=new boolean[]{true,true,true,true};
        level1.setOnAction(event ->{
            difficulty=new Param[2];
            difficulty[0]=new Param(1,10,1,10,1,10);
            difficulty[1]=new Param(1,10,1,10,1,10);
            ops[2]=false;
            ops[3]=false;
            goLoader(primaryStage);

        });
        level2.setOnAction(event ->{
            difficulty=new Param[4];
            difficulty[0]=new Param(1,200,1,200,1,200);
            difficulty[1]=new Param(1,200,1,200,1,200);
            difficulty[2]=new Param(2,12,2,12,4,144);
            difficulty[3]=new Param(2,12,2,12,4,144);
            goLoader(primaryStage);
        });
        level3.setOnAction(event ->{
            difficulty=new Param[4];
            difficulty[0]=new Param(100,10000,100,10000,200,10000);
            difficulty[1]=new Param(100,10000,100,10000,200,10000);
            difficulty[2]=new Param(10,100,10,100,100,10000);
            difficulty[3]=new Param(10,100,10,100,100,10000);
            goLoader(primaryStage);
        });
        custom.setOnAction(event ->{
            Setting setter = new Setting();
            try {
                setter.setScene(primaryStage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    protected static void goLoader(Stage primaryStage){
        Loader loader=new Loader();
        try {
            loader.start(primaryStage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
