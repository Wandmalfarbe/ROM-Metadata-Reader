package de.pascalwagler.rommetadatareader.gui;

import de.pascalwagler.rommetadatareader.GameBoyUtil;
import de.pascalwagler.rommetadatareader.roms.MetadataReader;
import de.pascalwagler.rommetadatareader.roms.RomFileTypeDetector;
import de.pascalwagler.rommetadatareader.roms.RomMetadata;
import de.pascalwagler.rommetadatareader.roms.gb.GbFileTypeDetector;
import de.pascalwagler.rommetadatareader.roms.gb.GbMetadata;
import de.pascalwagler.rommetadatareader.roms.gb.GbMetadataReader;
import de.pascalwagler.rommetadatareader.roms.gba.GbaFileTypeDetector;
import de.pascalwagler.rommetadatareader.roms.gba.GbaMetadataReader;
import de.pascalwagler.rommetadatareader.roms.gbc.GbcFileTypeDetector;
import de.pascalwagler.rommetadatareader.roms.nds.NdsFileTypeDetector;
import de.pascalwagler.rommetadatareader.roms.nds.NdsMetadataReader;
import de.pascalwagler.rommetadatareader.roms.sgb.SgbFileTypeDetector;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.spi.FileTypeDetector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainWindowController implements Initializable {

    private ResourceBundle resources;

    @FXML
    private StackPane mainStackPane;

    @FXML
    private ListView<File> fileList;

    @FXML
    private DetailViewController detailViewController;

    private static final HashMap<String, MetadataReader> readers = new HashMap<>();

    private static final List<RomFileTypeDetector> fileTypeDetectors = new ArrayList<>();

    static {
        readers.put("gb", new GbMetadataReader());
        readers.put("gbc", new GbMetadataReader());
        readers.put("sgb", new GbMetadataReader());
        readers.put("gba", new GbaMetadataReader());
        readers.put("nds", new NdsMetadataReader());

        fileTypeDetectors.add(new GbFileTypeDetector());
        fileTypeDetectors.add(new GbcFileTypeDetector());
        fileTypeDetectors.add(new SgbFileTypeDetector());
        fileTypeDetectors.add(new GbaFileTypeDetector());
        fileTypeDetectors.add(new NdsFileTypeDetector());
    }

    public void initialize(URL location, ResourceBundle resources) {

        this.resources = resources;

        showDropInstructions();

        fileList.setCellFactory(param -> new ListCell<File>() {

            private final ImageView imageView = new ImageView();

            @Override
            public void updateItem(File file, boolean empty) {
                super.updateItem(file, empty);

                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    RomFileTypeDetector detector;
                    try {
                        detector = chooseFileTypeDetector(file.toPath());
                        if (detector == null) {
                            return;
                        }
                        String shortType = detector.getShortType();

                        imageView.setImage(new Image("img/" + shortType + "-filetype.png"));
                        imageView.setPreserveRatio(true);
                        imageView.setFitWidth(16);
                        imageView.setSmooth(true);
                        setText(file.getName());
                        setGraphic(imageView);
                    } catch (IOException e) {
                        setText(null);
                        setGraphic(null);
                        e.printStackTrace();
                    }
                }
            }
        });

        fileList.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {


                RomFileTypeDetector detector;
                try {
                    detector = chooseFileTypeDetector(newValue.toPath());
                    if (detector == null) {
                        return;
                    }
                    MetadataReader reader = readers.get(detector.getShortType());
                    detailViewController.fillDetailPanel(newValue, detector, reader);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void showDropInstructions() {

        Node dropFilePanel;
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/DropFilePanel.fxml"), resources);
        try {
            dropFilePanel = loader.load();
            mainStackPane.getChildren().add(dropFilePanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void hideDropInstructions() {
        if (mainStackPane.getChildren().size() > 1) {
            mainStackPane.getChildren().remove(1);
        }
    }

    @FXML
    private void onDragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.ANY);
        event.consume();
    }

    @FXML
    private void onDragEntered(DragEvent event) {
        mainStackPane.getStyleClass().add("drag-over");
        event.consume();
    }

    @FXML
    private void onDragExited(DragEvent event) {
        mainStackPane.getStyleClass().remove("drag-over");
        event.consume();
    }

    @FXML
    private void onDragDropped(DragEvent event) {

        Platform.runLater(() -> {
            try {
                handleDrop(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void handleDrop(DragEvent event) throws IOException {
        hideDropInstructions();

        mainStackPane.getStyleClass().remove("drag-over");
        Dragboard db = event.getDragboard();

        // Clear the previous list
        fileList.getItems().clear();

        if (db.hasFiles()) {

            List<File> draggedFiles = db.getFiles();
            List<File> relevantFiles = draggedFiles.stream()
                    .filter(file -> !file.isDirectory()) // don't allow directories
                    .filter(file -> getContentType(file) != null)
                    .collect(Collectors.toList());

            // Return early if there have been no relevant files dropped.
            if (relevantFiles.size() == 0) {
                System.out.println("No relevant files dropped.");
                event.setDropCompleted(false);
                event.consume();
                return;
            }

            for (File relevantFile : relevantFiles) {
                RomFileTypeDetector detector = chooseFileTypeDetector(relevantFile.toPath());

                if (detector == null) {
                    continue;
                }

                MetadataReader reader = readers.get(detector.getShortType());
                RomMetadata metadata = reader.getMetadata(relevantFile.toPath());

                if (metadata instanceof GbMetadata) {
                    GbMetadata m = (GbMetadata) metadata;
                    if (GameBoyUtil.countriesImage.get(m.getCountryChar()) == null) {
                        System.out.println(relevantFile);
                        System.out.println(m);
                    }
                }
                fileList.getItems().add(relevantFile);
            }
            fileList.getSelectionModel().select(0);
        }
        event.setDropCompleted(true);
        event.consume();
    }

    private static String getContentType(File file) {
        for (FileTypeDetector detector : fileTypeDetectors) {
            String contentType = null;
            try {
                contentType = detector.probeContentType(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (contentType != null) {
                return contentType;
            }
        }
        return null;
    }

    private static RomFileTypeDetector chooseFileTypeDetector(Path path) throws IOException {

        for (RomFileTypeDetector detector : fileTypeDetectors) {
            String contentType = detector.probeContentType(path);
            if (contentType != null) {
                return detector;
            }
        }
        return null;
    }
}