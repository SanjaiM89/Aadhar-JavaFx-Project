package app.controller;

import app.model.User;
import app.util.ExportUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;

public class CardController {
    @FXML private Label aadharNumberLabel;
    @FXML private Label fullNameLabel;
    @FXML private Label yearOfBirthLabel;
    @FXML private Label genderLabel;
    @FXML private Label addressLabel;
    @FXML private ImageView photoView;
    @FXML private Button exportButton;

    private User user;

    @FXML
    public void initialize() {
        System.out.println("CardController: Initializing...");
        exportButton.setOnAction(event -> handleExport());
    }

    public void setUser(User user) {
        System.out.println("CardController: Setting user...");
        this.user = user;
        populateCardDetails();
    }

    private void populateCardDetails() {
        System.out.println("CardController: Populating card details...");
        if (user == null) {
            System.out.println("User is null!");
            return;
        }

        aadharNumberLabel.setText(user.getAadharNumber() != null ? user.getAadharNumber() : "N/A");
        fullNameLabel.setText((user.getFirstName() != null ? user.getFirstName() : "") + " " + (user.getLastName() != null ? user.getLastName() : ""));
        yearOfBirthLabel.setText("Year of Birth: " + (user.getDob() != null ? String.valueOf(user.getDob().getYear()) : "N/A"));
        genderLabel.setText("Gender: " + (user.getGender() != null ? user.getGender() : "N/A"));
        addressLabel.setText(user.getResidentialAddress() != null ? user.getResidentialAddress() : "N/A");

        // Load photo if available
        if (user.getPhotoPath() != null && !user.getPhotoPath().isEmpty()) {
            try {
                File file = new File(user.getPhotoPath());
                Image image = new Image(file.toURI().toString());
                photoView.setImage(image);
                System.out.println("Photo loaded successfully: " + user.getPhotoPath());
            } catch (Exception e) {
                photoView.setImage(null);
                System.err.println("Failed to load photo: " + e.getMessage());
            }
        } else {
            System.out.println("No photo path provided.");
        }
    }

    private void handleExport() {
        System.out.println("Exporting Aadhar Card to PDF...");
        Stage stage = (Stage) exportButton.getScene().getWindow();
        ExportUtil.exportToPDF(user, stage);
    }
}