package de.pascalwagler.rommetadatareader.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class RomMetadataReader extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");

        System.out.println("Java Version: " + javaVersion);
        System.out.println("JavaFX Version: " + javafxVersion);

        ResourceBundle bundle = ResourceBundle.getBundle("i18n.LangBundle", Locale.getDefault());
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/MainWindow.fxml"), bundle);
        Parent root = loader.load();

        Scene scene = new Scene(root);

        stage.getIcons().add(new Image("img/app-icons/icon_16x16.png"));
        stage.getIcons().add(new Image("img/app-icons/icon_20x20.png"));
        stage.getIcons().add(new Image("img/app-icons/icon_32x32.png"));
        stage.getIcons().add(new Image("img/app-icons/icon_40x40.png"));
        stage.getIcons().add(new Image("img/app-icons/icon_44x44.png"));
        stage.getIcons().add(new Image("img/app-icons/icon_64x64.png"));
        stage.getIcons().add(new Image("img/app-icons/icon_128x128.png"));
        stage.getIcons().add(new Image("img/app-icons/icon_256x256.png"));
        stage.getIcons().add(new Image("img/app-icons/icon_512x512.png"));

        stage.setTitle(bundle.getString("app.name"));
        stage.setScene(scene);
        stage.show();
    }
}
