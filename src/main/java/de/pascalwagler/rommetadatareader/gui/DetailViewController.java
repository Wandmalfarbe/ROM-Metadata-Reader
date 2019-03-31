package de.pascalwagler.rommetadatareader.gui;

import de.pascalwagler.rommetadatareader.GameBoyUtil;
import de.pascalwagler.rommetadatareader.roms.MetadataReader;
import de.pascalwagler.rommetadatareader.roms.RomFileTypeDetector;
import de.pascalwagler.rommetadatareader.roms.RomMetadata;
import de.pascalwagler.rommetadatareader.roms.gb.GbMetadata;
import de.pascalwagler.rommetadatareader.roms.gba.GbaMetadata;
import de.pascalwagler.rommetadatareader.roms.nds.NdsMetadata;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DetailViewController implements Initializable {

    private ResourceBundle resources;

    @FXML
    private ImageView fileIcon;

    @FXML
    private Text filename;

    @FXML
    private ToggleButton showRawValuesToggle;

    @FXML
    private VBox metadataArea;

    @FXML
    private VBox fileInformationArea;

    public void initialize(URL location, ResourceBundle resources) {

        this.resources = resources;
        showRawValuesToggle.setSelected(false);
    }

    public void fillDetailPanel(File file, RomFileTypeDetector fDetector, MetadataReader reader) {

        try {
            RomMetadata metadata = reader.getMetadata(file.toPath());

            String shortType = fDetector.getShortType();
            fileIcon.setImage(new Image("img/" + shortType + "-large.png"));
            filename.setText(file.getName());

            fillFileInformation(file);

            metadataArea.getChildren().clear();

            switch(fDetector.getShortType()) {
                case "gb":
                case "gbc":
                case "sgb":
                    fillGbMetadata((GbMetadata) metadata);
                    break;
                case "gba":
                    fillGbaMetadata((GbaMetadata) metadata);
                    break;
                case "nds":
                    fillNdsMetadata((NdsMetadata) metadata);
                    break;
            }

            rawValuesToggled();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillFileInformation(File file) throws IOException {

        BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
        fileInformationArea.getChildren().clear();

        GridPane section1 = addNewSectionAndReturnPane(fileInformationArea, 2, 4);

        addLabel(section1, "label.file_info.creation_time", 0);
        addValue(section1, attr.creationTime().toString(), 0);

        addLabel(section1, "label.file_info.last_access_time", 1);
        addValue(section1, attr.lastAccessTime().toString(), 1);

        addLabel(section1, "label.file_info.last_modified_time", 2);
        addValue(section1, attr.lastModifiedTime().toString(), 2);

        addLabel(section1, "label.file_info.size", 3);
        addValue(section1, attr.size() + " " + resources.getString("unit.byte"), 3);
    }

    private void fillNdsMetadata(NdsMetadata m) {

        GridPane section1 = addNewSectionAndReturnPane(metadataArea, 2, 4);

        addLabel(section1, "label.title", 0);
        addValue(section1, m.getTitle(), 0);

        addLabel(section1, "label.game_code", 1);
        addValue(section1, m.getGameCode(), 1);

        String makerString = GameBoyUtil.manufacturers.getOrDefault(m.getMakerCode(), resources.getString("value.unknown"));
        addLabel(section1, "label.maker", 2);
        addValue(section1, makerString, 2, false);
        addValue(section1, m.getMakerCode(), 2, true);

        addLabel(section1, "label.unit_code", 3);
        addValue(section1, m.getUnitCode(), 3);
    }

    private void fillGbaMetadata(GbaMetadata m) {

        GridPane section1 = addNewSectionAndReturnPane(metadataArea, 2, 6);

        addLabel(section1, "label.title", 0);
        addValue(section1, m.getTitle(), 0);

        String makerString = GameBoyUtil.manufacturers.getOrDefault(m.getMakerCode(), resources.getString("value.unknown"));
        addLabel(section1, "label.maker", 1);
        addValue(section1, makerString, 1, false);
        addValue(section1, m.getMakerCode(), 1, true);

        addLabel(section1, "label.game_code", 2);
        addValue(section1, m.getGameCode(), 2);

        String gameTypeString = GameBoyUtil.gameTypesDescription.getOrDefault(m.getGameTypeChar(), "value.unknown");
        addLabel(section1, "label.game_type", 3);
        addValue(section1, resources.getString(gameTypeString), 3, false);
        addValue(section1, m.getGameTypeChar(), 3, true);

        String countryString = GameBoyUtil.countriesDescription.getOrDefault(m.getCountryChar(), "value.unknown");
        String countryImage = GameBoyUtil.countriesImage.getOrDefault(m.getCountryChar(), "unknown");
        addLabel(section1, "label.country", 4);
        addValueTextWithImage(section1, resources.getString(countryString), "img/flags/" + countryImage + ".png", 4);
        addValue(section1, m.getCountryChar(), 4, true);

        addLabel(section1, "label.header_checksum", 5);
        addValue(section1, m.getHeaderChecksum(), 5);

        GridPane section2 = addNewSectionAndReturnPane(metadataArea, 2, 2);

        addLabel(section2, "label.unit_code", 0);
        addValue(section2, m.getUnitCode(), 0);

        addLabel(section2, "label.version_code", 1);
        addValue(section2, m.getVersionCode(), 1);
    }

    private void fillGbMetadata(GbMetadata m) {

        GridPane section1 = addNewSectionAndReturnPane(metadataArea, 2, 7);

        addLabel(section1, "label.title", 0);
        addValue(section1, m.getTitle(), 0);

        String makerString = GameBoyUtil.manufacturers.getOrDefault(m.getNewLicenseeCode(), resources.getString("value.unknown"));
        addLabel(section1, "label.maker", 1);
        addValue(section1, makerString, 1, false);
        addValue(section1, m.getNewLicenseeCode(), 1, true);

        addLabel(section1, "label.game_code", 2);
        addValue(section1, m.getGameCode(), 2);

        String gameTypeString = GameBoyUtil.gameTypesDescription.getOrDefault(m.getGameTypeChar(), "value.unknown");
        addLabel(section1, "label.game_type", 3);
        addValue(section1, resources.getString(gameTypeString), 3, false);
        addValue(section1, m.getGameTypeChar(), 3, true);

        String countryString = GameBoyUtil.countriesDescription.getOrDefault(m.getCountryChar(), "value.unknown");
        String countryImage = GameBoyUtil.countriesImage.getOrDefault(m.getCountryChar(), "unknown");
        addLabel(section1, "label.country", 4);
        addValueTextWithImage(section1, resources.getString(countryString), "img/flags/" + countryImage + ".png", 4);
        addValue(section1, m.getCountryChar(), 4, true);

        addLabel(section1, "label.old_licensee_code", 5);
        addValue(section1, m.getOldLicenseeCode(), 5);

        addLabel(section1, "label.header_checksum", 6);
        addValue(section1, m.getHeaderChecksum(), 6);

        GridPane section2 = addNewSectionAndReturnPane(metadataArea, 2, 7);

        String cgbFlagTag = GameBoyUtil.cgbFlagsDescription.getOrDefault(m.getCgbFlag(), "value.unknown");
        addLabel(section2, "label.cgb_flag", 0);
        addValue(section2, resources.getString(cgbFlagTag), 0, false);
        addValue(section2, m.getCgbFlag(), 0, true);

        String sgbFlagTag = GameBoyUtil.sgbFlagsDescription.getOrDefault(m.getSgbFlag(), "value.unknown");
        addLabel(section2, "label.sgb_flag", 1);
        addValue(section2, resources.getString(sgbFlagTag), 1, false);
        addValue(section2, m.getSgbFlag(), 1, true);

        String cTypes = GameBoyUtil.cardridgeTypes.get(m.getCartridgeType());
        String[] cTypesTags;
        if (cTypes != null) {
            cTypesTags = cTypes.split("\\+");
        } else {
            cTypesTags = new String[1];
            cTypesTags[0] = resources.getString("value.unknown");
        }
        addLabel(section2, "label.cartridge_type", 2);
        section2.add(getTagComponent(cTypesTags), 1, 2);

        String romSizeString = GameBoyUtil.romSizesDescription.getOrDefault(m.getRomSize(), "value.unknown");
        addLabel(section2, "label.rom_size", 3);
        addValue(section2, resources.getString(romSizeString), 3, false);
        addValue(section2, m.getRomSize(), 3, true);

        String ramSizeString = GameBoyUtil.ramSizesDescription.getOrDefault(m.getRamSize(), "value.unknown");
        addLabel(section2, "label.ram_size", 4);
        addValue(section2, resources.getString(ramSizeString), 4, false);
        addValue(section2, m.getRamSize(), 4, true);

        String dCodeString = GameBoyUtil.destinationCodesDescription.getOrDefault(m.getDestinationCode(), "value.unknown");
        String dCodeImage = GameBoyUtil.destinationCodesImage.getOrDefault(m.getDestinationCode(), "unknown");
        addLabel(section2, "label.destination_code", 5);
        addValueTextWithImage(section2, resources.getString(dCodeString), "img/flags/" + dCodeImage + ".png", 5);
        addValue(section2, m.getDestinationCode(), 5, true);

        addLabel(section2, "label.mask_rom_version", 6);
        addValue(section2, m.getMaskRomVersionNumber(), 6);
    }

    @FXML
    private void rawValuesToggled() {

        ToggleButton button = showRawValuesToggle;
        if (button.isSelected()) {
            showRawValues();
        } else {
            hideRawValues();
        }
    }

    private void hideRawValues() {
        for (Node node : nodesWithRawValue) {
            node.setVisible(false);
        }
        for (Node node : nodesWithComputedValue) {
            node.setVisible(true);
        }
    }

    private void showRawValues() {
        for (Node node : nodesWithRawValue) {
            node.setVisible(true);
        }
        for (Node node : nodesWithComputedValue) {
            node.setVisible(false);
        }
    }

    private List<Node> nodesWithRawValue = new ArrayList<>();
    private List<Node> nodesWithComputedValue = new ArrayList<>();

    private void addLabel(GridPane section, String lString, int row) {
        section.add(new Text(resources.getString(lString)), 0, row);
    }

    private void addValue(GridPane section, String vString, int row) {
        section.add(new Text(vString), 1, row);
    }

    private void addValueTextWithImage(GridPane section, String vString, String imageName, int row) {

        HBox hBox = new HBox(5);
        hBox.setMaxHeight(20.0);

        ImageView imageView = new ImageView(new Image(imageName));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(16);
        imageView.setSmooth(true);
        hBox.getChildren().add(imageView);

        Text t = new Text(vString);
        hBox.getChildren().add(t);

        nodesWithComputedValue.add(hBox);
        section.add(hBox, 1, row);
    }

    private void addValue(GridPane section, String lString, int row, boolean isRawValue) {
        if (isRawValue) {
            Text t = new Text(lString);
            nodesWithRawValue.add(t);
            section.add(t, 1, row);
        } else {
            Text t = new Text(lString);
            nodesWithComputedValue.add(t);
            section.add(t, 1, row);
        }
    }

    private Node getTagComponent(String... tags) {
        HBox hBox = new HBox(5);
        hBox.setMaxHeight(20.0);

        for (String tag : tags) {
            HBox tagBox = new HBox();
            tagBox.getStyleClass().add("tag");

            Text text = new Text(tag);
            tagBox.getChildren().add(text);
            hBox.getChildren().add(tagBox);
        }
        return hBox;
    }

    private GridPane addNewSectionAndReturnPane(VBox metadataArea, int columns, int rows) {

        GridPane gridPaneSection = new GridPane();

        for (int x = 0; x < columns; x++) {
            ColumnConstraints cConstr = new ColumnConstraints();
            cConstr.setHgrow(Priority.SOMETIMES);
            cConstr.setMinWidth(10.0);
            cConstr.setPrefWidth(60.0);
            gridPaneSection.getColumnConstraints().add(cConstr);
        }

        for (int x = 0; x < rows; x++) {
            RowConstraints rConstr = new RowConstraints();
            rConstr.setVgrow(Priority.SOMETIMES);
            rConstr.setMinHeight(30.0);
            rConstr.setMaxHeight(30.0);
            rConstr.setPrefHeight(30.0);
            gridPaneSection.getRowConstraints().add(rConstr);
        }

        AnchorPane anchorPaneSection = new AnchorPane(gridPaneSection);

        anchorPaneSection.getStyleClass().add("group-box");

        AnchorPane.setTopAnchor(gridPaneSection, 0.0);
        AnchorPane.setRightAnchor(gridPaneSection, 0.0);
        AnchorPane.setBottomAnchor(gridPaneSection, 0.0);
        AnchorPane.setLeftAnchor(gridPaneSection, 0.0);

        metadataArea.getChildren().add(anchorPaneSection);
        return gridPaneSection;
    }
}