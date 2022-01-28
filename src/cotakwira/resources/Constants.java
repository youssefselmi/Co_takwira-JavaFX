package cotakwira.resources;

import javafx.scene.effect.BoxBlur;

public class Constants {

    public static final String TITLE = "CoTakwira";
    // public static final Double MIN_WIDTH = 1040.00;
    // public static final Double MIN_HEIGHT = 640.00;

    public static final String MEDIA_PACKAGE = "/resources/media/";

    public static final String STAGE_ICON = MEDIA_PACKAGE + "icon.png";
    public static final String NO_IMAGE_AVAILABLE = MEDIA_PACKAGE + "empty-image.jpg";
    public static final String INFORMATION_IMAGE = MEDIA_PACKAGE + "information.png";
    public static final String ERROR_IMAGE = MEDIA_PACKAGE + "error.png";
    public static final String SUCCESS_IMAGE = MEDIA_PACKAGE + "success.png";
    
    public static final String MEDIA_DragAndDrop = MEDIA_PACKAGE + "drag-drop.gif";  

    public static final String CSS_LIGHT_THEME = "../gui/style.css";

    public static final String MESSAGE_ERROR_CONNECTION_MYSQL = "An error occurred when connecting to MySQL.\nCheck your connection to MySQL";

    public static final String MESSAGE_ADDED = "Donnée ajoutées avec succès ";
    public static final String MESSAGE_UPDATED = "Donnée été Modifier avec succès ";
    public static final String MESSAGE_DELETED = "Donnée été supprimer avec succès ";
    public static final String MESSAGE_24h = "Il dure plus de 24H ou a été lu par l'admin";

    public static final BoxBlur BOX_BLUR_EFFECT = new BoxBlur(3, 3, 3);
}
