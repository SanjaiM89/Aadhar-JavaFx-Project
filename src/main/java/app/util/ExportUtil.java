package app.util;

import app.model.User;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Image;
import com.itextpdf.io.image.ImageDataFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ExportUtil {

    public static void exportToPDF(User user, Stage stage) {
        if (user == null) {
            showAlert("Error", "User data is not available for export.");
            return;
        }

        // Use FileChooser to let the user select the save location
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Aadhar Card as PDF");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        fileChooser.setInitialFileName("AadharCard_" + user.getAadharNumber() + ".pdf");
        File file = fileChooser.showSaveDialog(stage);

        if (file == null) {
            System.out.println("PDF export cancelled by user.");
            return;
        }

        try {
            // Initialize PDF document
            PdfWriter writer = new PdfWriter(file.getAbsolutePath());
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Header
            document.add(new Paragraph("AADHAAR")
                    .setFontSize(18)
                    .setBold()
                    .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER));
            document.add(new Paragraph("UNIQUE IDENTIFICATION AUTHORITY OF INDIA")
                    .setFontSize(10)
                    .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER));
            document.add(new Paragraph("GOVERNMENT OF INDIA")
                    .setFontSize(10)
                    .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER));
            document.add(new Paragraph(" ").setMarginBottom(20)); // Spacer

            // Main Content
            document.add(new Paragraph("Aadhar Number: " + (user.getAadharNumber() != null ? user.getAadharNumber() : "N/A"))
                    .setFontSize(14)
                    .setBold());
            document.add(new Paragraph("Name: " + (user.getFirstName() != null ? user.getFirstName() : "") + " " + (user.getLastName() != null ? user.getLastName() : ""))
                    .setFontSize(12));
            document.add(new Paragraph("Year of Birth: " + (user.getDob() != null ? String.valueOf(user.getDob().getYear()) : "N/A"))
                    .setFontSize(12));
            document.add(new Paragraph("Gender: " + (user.getGender() != null ? user.getGender() : "N/A"))
                    .setFontSize(12));
            document.add(new Paragraph("Address: " + (user.getResidentialAddress() != null ? user.getResidentialAddress() : "N/A"))
                    .setFontSize(12));

            // Add Photo if Available
            if (user.getPhotoPath() != null && !user.getPhotoPath().isEmpty()) {
                try {
                    Image photo = new Image(ImageDataFactory.create(user.getPhotoPath()));
                    photo.setWidth(80);
                    photo.setHeight(100);
                    document.add(photo);
                } catch (Exception e) {
                    System.err.println("Failed to add photo to PDF: " + e.getMessage());
                }
            }

            // Footer
            document.add(new Paragraph(" ").setMarginTop(20)); // Spacer
            document.add(new Paragraph("Aadhar - Aam Aadmi Ka Adhikaar")
                    .setFontSize(10)
                    .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER));

            // Close the document
            document.close();
            System.out.println("PDF exported successfully to: " + file.getAbsolutePath());
            showAlert("Success", "Aadhar Card exported successfully to " + file.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to export Aadhar Card to PDF: " + e.getMessage());
        }
    }

    private static void showAlert(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}