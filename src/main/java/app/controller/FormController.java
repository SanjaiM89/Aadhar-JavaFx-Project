package app.controller;

import app.db.UserDAO;
import app.model.User;
import app.util.IDGenerator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class FormController {
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private DatePicker dobPicker;
    @FXML private ComboBox<String> genderCombo;
    @FXML private ComboBox<String> maritalStatusCombo;
    @FXML private Label spouseNameLabel;
    @FXML private TextField spouseNameField;
    @FXML private TextField fatherNameField;
    @FXML private TextField motherNameField;
    @FXML private TextField motherTongueField;
    @FXML private TextField bloodGroupField;
    @FXML private TextField educationalQualificationsField;
    @FXML private Button photoButton;
    @FXML private Label photoLabel;
    @FXML private TextArea permanentAddressArea;
    @FXML private TextField permanentPostalCodeField;
    @FXML private CheckBox sameAsPermanentCheck;
    @FXML private TextArea residentialAddressArea;
    @FXML private TextField residentialPostalCodeField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private TextField nationalityField;
    @FXML private CheckBox biometricCheck;
    @FXML private Button submitButton;

    private File photoFile;
    private UserDAO userDAO = new UserDAO();

    @FXML
    public void initialize() {
        System.out.println("FormController: Initializing...");
        // Populate ComboBoxes
        genderCombo.getItems().addAll("Male", "Female", "Other");
        genderCombo.setPromptText("Select Gender");
        maritalStatusCombo.getItems().addAll("Single", "Married");
        maritalStatusCombo.setPromptText("Marital Status");

        // Event Listeners
        submitButton.setOnAction(event -> handleSubmit());
        photoButton.setOnAction(event -> choosePhoto());

        // Conditional Logic for Spouse Name
        maritalStatusCombo.valueProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("Marital Status changed to: " + newVal);
            if ("Married".equals(newVal)) {
                if ("Male".equals(genderCombo.getValue())) {
                    spouseNameLabel.setText("Wife's Name:");
                    spouseNameField.setPromptText("Wife's Name");
                    spouseNameLabel.setVisible(true);
                    spouseNameLabel.setManaged(true);
                    spouseNameField.setVisible(true);
                    spouseNameField.setManaged(true);
                } else if ("Female".equals(genderCombo.getValue())) {
                    spouseNameLabel.setText("Husband's Name:");
                    spouseNameField.setPromptText("Husband's Name");
                    spouseNameLabel.setVisible(true);
                    spouseNameLabel.setManaged(true);
                    spouseNameField.setVisible(true);
                    spouseNameField.setManaged(true);
                } else {
                    spouseNameLabel.setVisible(false);
                    spouseNameLabel.setManaged(false);
                    spouseNameField.setVisible(false);
                    spouseNameField.setManaged(false);
                }
            } else {
                spouseNameLabel.setVisible(false);
                spouseNameLabel.setManaged(false);
                spouseNameField.setVisible(false);
                spouseNameField.setManaged(false);
            }
        });

        genderCombo.valueProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("Gender changed to: " + newVal);
            if ("Married".equals(maritalStatusCombo.getValue())) {
                if ("Male".equals(newVal)) {
                    spouseNameLabel.setText("Wife's Name:");
                    spouseNameField.setPromptText("Wife's Name");
                    spouseNameLabel.setVisible(true);
                    spouseNameLabel.setManaged(true);
                    spouseNameField.setVisible(true);
                    spouseNameField.setManaged(true);
                } else if ("Female".equals(newVal)) {
                    spouseNameLabel.setText("Husband's Name:");
                    spouseNameField.setPromptText("Husband's Name");
                    spouseNameLabel.setVisible(true);
                    spouseNameLabel.setManaged(true);
                    spouseNameField.setVisible(true);
                    spouseNameField.setManaged(true);
                } else {
                    spouseNameLabel.setVisible(false);
                    spouseNameLabel.setManaged(false);
                    spouseNameField.setVisible(false);
                    spouseNameField.setManaged(false);
                }
            }
        });

        // Autofill Logic for Residential Address
        sameAsPermanentCheck.selectedProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("Same as Permanent Address checkbox: " + newVal);
            if (newVal) {
                residentialAddressArea.setText(permanentAddressArea.getText());
                residentialPostalCodeField.setText(permanentPostalCodeField.getText());
                residentialAddressArea.setDisable(true);
                residentialPostalCodeField.setDisable(true);
            } else {
                residentialAddressArea.setText("");
                residentialPostalCodeField.setText("");
                residentialAddressArea.setDisable(false);
                residentialPostalCodeField.setDisable(false);
            }
        });
    }

    private void choosePhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        photoFile = fileChooser.showOpenDialog(null);
        if (photoFile != null) {
            photoLabel.setText(photoFile.getName());
            System.out.println("Photo selected: " + photoFile.getAbsolutePath());
        }
    }

    private void handleSubmit() {
        System.out.println("FormController: Handling form submission...");
        if (!validateForm()) {
            System.out.println("Form validation failed.");
            return;
        }

        User user = new User();
        user.setFirstName(firstNameField.getText());
        user.setLastName(lastNameField.getText());
        user.setDob(dobPicker.getValue());
        user.setGender(genderCombo.getValue());
        user.setMaritalStatus(maritalStatusCombo.getValue());
        user.setSpouseName(spouseNameField.isVisible() ? spouseNameField.getText() : null);
        user.setFatherName(fatherNameField.getText());
        user.setMotherName(motherNameField.getText());
        user.setMotherTongue(motherTongueField.getText());
        user.setBloodGroup(bloodGroupField.getText());
        user.setEducationalQualifications(educationalQualificationsField.getText());
        user.setPhotoPath(photoFile != null ? photoFile.getAbsolutePath() : null);
        user.setPermanentAddress(permanentAddressArea.getText());
        user.setPermanentPostalCode(permanentPostalCodeField.getText());
        user.setResidentialAddress(residentialAddressArea.getText());
        user.setResidentialPostalCode(residentialPostalCodeField.getText());
        user.setPhone(phoneField.getText());
        user.setEmail(emailField.getText());
        user.setNationality(nationalityField.getText());
        user.setBiometric(biometricCheck.isSelected());
        user.setAadharNumber(IDGenerator.generateAadharNumber());

        System.out.println("User object created: " + user.getAadharNumber());

        userDAO.saveUser(user);
        System.out.println("User saved to database.");

        try {
            System.out.println("Loading card.fxml...");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/card.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            scene.getStylesheets().add(getClass().getResource("/view/styles.css").toExternalForm());
            CardController cardController = loader.getController();
            System.out.println("Setting user in CardController...");
            cardController.setUser(user);
            Stage stage = (Stage) submitButton.getScene().getWindow();
            stage.setScene(scene);
            System.out.println("Transition to card preview successful.");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load card preview: " + e.getMessage());
        }
    }

    private boolean validateForm() {
        System.out.println("Validating form...");
        if (firstNameField.getText().isEmpty()) {
            showAlert("Error", "First Name cannot be empty.");
            return false;
        }
        if (lastNameField.getText().isEmpty()) {
            showAlert("Error", "Last Name cannot be empty.");
            return false;
        }
        if (dobPicker.getValue() == null) {
            showAlert("Error", "Please select a valid Date of Birth.");
            return false;
        }
        if (genderCombo.getValue() == null) {
            showAlert("Error", "Please select a Gender.");
            return false;
        }
        if (maritalStatusCombo.getValue() == null) {
            showAlert("Error", "Please select Marital Status.");
            return false;
        }
        if (spouseNameField.isVisible() && spouseNameField.getText().isEmpty()) {
            showAlert("Error", spouseNameField.getPromptText() + " cannot be empty.");
            return false;
        }
        if (fatherNameField.getText().isEmpty()) {
            showAlert("Error", "Father's Name cannot be empty.");
            return false;
        }
        if (motherNameField.getText().isEmpty()) {
            showAlert("Error", "Mother's Name cannot be empty.");
            return false;
        }
        if (motherTongueField.getText().isEmpty()) {
            showAlert("Error", "Mother Tongue cannot be empty.");
            return false;
        }
        if (bloodGroupField.getText().isEmpty()) {
            showAlert("Error", "Blood Group cannot be empty.");
            return false;
        }
        if (educationalQualificationsField.getText().isEmpty()) {
            showAlert("Error", "Educational Qualifications cannot be empty.");
            return false;
        }
        if (permanentAddressArea.getText().isEmpty()) {
            showAlert("Error", "Permanent Address cannot be empty.");
            return false;
        }
        if (permanentPostalCodeField.getText().isEmpty()) {
            showAlert("Error", "Permanent Postal Code cannot be empty.");
            return false;
        }
        if (residentialAddressArea.getText().isEmpty()) {
            showAlert("Error", "Residential Address cannot be empty.");
            return false;
        }
        if (residentialPostalCodeField.getText().isEmpty()) {
            showAlert("Error", "Residential Postal Code cannot be empty.");
            return false;
        }
        if (phoneField.getText().isEmpty()) {
            showAlert("Error", "Phone number cannot be empty.");
            return false;
        }
        if (!phoneField.getText().matches("\\d{10}")) {
            showAlert("Error", "Phone number must be exactly 10 digits.");
            return false;
        }
        if (emailField.getText().isEmpty()) {
            showAlert("Error", "Email cannot be empty.");
            return false;
        }
        if (!emailField.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            showAlert("Error", "Please enter a valid email address (e.g., example@domain.com).");
            return false;
        }
        if (nationalityField.getText().isEmpty()) {
            showAlert("Error", "Nationality cannot be empty.");
            return false;
        }

        System.out.println("Form validation passed.");
        return true;
    }

    private void showAlert(String title, String message) {
        System.out.println("Showing alert: " + title + " - " + message);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}