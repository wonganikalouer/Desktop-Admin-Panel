/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainactiviry;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author acer
 */
public class MainActivity extends Application {
    WebView webview;
    DataStructure ds=new DataStructure();
    @Override
    public void start(Stage primaryStage) {
        webview=new WebView();
        primaryStage.getIcons().add(new Image(MainActivity.class.getResource("icon.png").toExternalForm()));
        StackPane root = new StackPane();
        ImageView img=new ImageView();
        img.setFitWidth(300);
        img.setFitHeight(300);
        img.setImage(new Image(MainActivity.class.getResource("logo.png").toExternalForm()));
        ProgressBar pbar=new ProgressBar();
        pbar.setMaxWidth(300);
        webview.setContextMenuEnabled(false);
        
        VBox hb=new VBox();
        root.getChildren().addAll(webview,pbar);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.setMaximized(true);
        webview.getEngine().load(ds.URL);
        primaryStage.setTitle("ET Consultants ADMIN");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        webview.getEngine().getLoadWorker().progressProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                pbar.setProgress(newValue.floatValue());
                if(newValue.floatValue()>=1){
                pbar.setVisible(false);
                }
                else{
                pbar.setVisible(true);}
            }
        });
        
        webview.getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>(){
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                if(newValue.equals(Worker.State.FAILED)){
                    webview.getEngine().load(ds.FAILURL);
                }
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
