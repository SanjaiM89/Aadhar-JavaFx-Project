package app.db;

import app.model.User;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class UserDAO {
    private final MongoCollection<Document> collection;

    public UserDAO() {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("aadhar_db");
        this.collection = database.getCollection("users");
    }

    public void saveUser(User user) {
        Document doc = new Document("firstName", user.getFirstName())
                .append("lastName", user.getLastName())
                .append("dob", user.getDob() != null ? user.getDob().toString() : null)
                .append("gender", user.getGender())
                .append("maritalStatus", user.getMaritalStatus())
                .append("spouseName", user.getSpouseName())
                .append("fatherName", user.getFatherName())
                .append("motherName", user.getMotherName())
                .append("motherTongue", user.getMotherTongue())
                .append("bloodGroup", user.getBloodGroup())
                .append("educationalQualifications", user.getEducationalQualifications())
                .append("photoPath", user.getPhotoPath())
                .append("permanentAddress", user.getPermanentAddress())
                .append("permanentPostalCode", user.getPermanentPostalCode())
                .append("residentialAddress", user.getResidentialAddress())
                .append("residentialPostalCode", user.getResidentialPostalCode())
                .append("phone", user.getPhone())
                .append("email", user.getEmail())
                .append("nationality", user.getNationality())
                .append("biometric", user.isBiometric())
                .append("aadharNumber", user.getAadharNumber());
        collection.insertOne(doc);
        System.out.println("User saved to MongoDB: " + user.getAadharNumber());
    }
}