/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotakwira.gui;

import cotakwira.animation.Animations;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import cotakwira.resources.Constants;

/**
 *
 * @author yassin
 */
public class FirstWindow extends Application {

    @FXML
    private Pane sidebar;
    @FXML
    private Pane Navicons;
    double xOffset, yOffset;

    public static Stage stage = null;

    @Override
    public void start(Stage primaryStage) throws IOException {
      // Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
       Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
       //Parent root = FXMLLoader.load(getClass().getResource("AdminReclamation.fxml"));
        Scene scene = new Scene(root, 1050, 750);

        // primaryStage.setTitle("Hello World!");
        primaryStage.getIcons().add(new Image(Constants.STAGE_ICON));
        primaryStage.setScene(scene);

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        Animations.shake(root);
        this.stage = primaryStage;
        primaryStage.show();

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
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
