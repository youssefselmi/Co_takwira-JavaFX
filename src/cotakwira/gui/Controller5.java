package cotakwira.gui;

import javafx.event.ActionEvent;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Controller5 {

    public ImageView torr;


    public void payit(ActionEvent actionEvent) throws InterruptedException {
        torr.setY(-150);


    }

    public void blui(MouseEvent mouseEvent) {
        Lighting lighting = new Lighting();
        torr.setEffect(lighting);
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File("D:\\3éme\\2émesemestre\\Pi\\Desktop\\CoTakwira\\pdf_facture.pdf");
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                // no application registered for PDFs
            }
        }
        System.out.println("done");
    }
}
