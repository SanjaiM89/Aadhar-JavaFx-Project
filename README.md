AadharClone
AadharClone is a JavaFX application that simulates the registration and generation of an Aadhar card, including a form to input user details, a card preview, and an option to export the card as a PDF. This project uses Maven for dependency management and includes MongoDB for data persistence.
Prerequisites
Before running the project, ensure you have the following installed on your system:

Java 17 (or later): The project is built using Java 17.
Maven: For dependency management and building the project.
MongoDB: A local MongoDB instance for storing user data.
Git: To clone the repository.

Project Setup
1. Clone the Repository
Clone the project from GitHub to your local machine:
git clone https://github.com/<your-username>/AadharClone.git
cd AadharClone

2. Install Dependencies
The project uses Maven to manage dependencies. Ensure all dependencies are downloaded by running:
mvn install

This will download all required dependencies listed in pom.xml, including JavaFX, MongoDB, and iText 7 for PDF generation.
3. Set Up MongoDB
The application uses MongoDB to store user data. Ensure MongoDB is installed and running on your system.
On Linux:

Install MongoDB:sudo apt update
sudo apt install -y mongodb


Start MongoDB:sudo systemctl start mongodb


Verify MongoDB is running:sudo systemctl status mongodb

Ensure MongoDB is running on the default port (27017).

On Windows:

Download and install MongoDB from the official website: MongoDB Community Server.
During installation, choose to install MongoDB as a service (recommended) or start it manually.
Start MongoDB:
If installed as a service, it starts automatically.
To start manually, navigate to the MongoDB bin directory (e.g., C:\Program Files\MongoDB\Server\<version>\bin) and run:mongod




Verify MongoDB is running by opening another command prompt and running:mongo

If the MongoDB shell opens, the server is running on localhost:27017.

Running the Project
On Linux

Build the Project:Ensure you're in the project root directory (AadharClone) and build the project:
mvn clean install


Run the Application:Start the JavaFX application:
mvn javafx:run


Interact with the Application:

The application will open a form (form.fxml) where you can enter user details.
Fill in all required fields, upload a photo (optional), and click "Generate Aadhar".
The card preview (card.fxml) will display the Aadhar card details.
Click "Export as PDF" to save the card as a PDF file.


Troubleshooting:

Locale Warning: If you see Gtk-WARNING: Locale not supported by C library, set a supported locale:sudo locale-gen en_US.UTF-8
export LC_ALL=en_US.UTF-8
export LANG=en_US.UTF-8


File Permission Issues: If you encounter errors like error while writing app.util.ExportUtil, ensure you have write permissions:chmod -R u+rw .


MongoDB Connection Issues: Ensure MongoDB is running on localhost:27017. Check logs for connection errors.



On Windows

Build the Project:Open a command prompt or PowerShell, navigate to the project root directory (AadharClone), and build the project:
mvn clean install


Run the Application:Start the JavaFX application:
mvn javafx:run


Interact with the Application:

The application will open a form (form.fxml) where you can enter user details.
Fill in all required fields, upload a photo (optional), and click "Generate Aadhar".
The card preview (card.fxml) will display the Aadhar card details.
Click "Export as PDF" to save the card as a PDF file.


Troubleshooting:

File Permission Issues: If you encounter errors like error while writing app.util.ExportUtil, ensure you have write permissions. Run the command prompt as Administrator:cd path\to\AadharClone
mvn clean install


MongoDB Connection Issues: Ensure MongoDB is running on localhost:27017. If MongoDB is not running, start it manually (see the MongoDB setup section above).
Path Issues: Ensure your Java and Maven paths are correctly set in the system environment variables:
Right-click on "This PC" > Properties > Advanced system settings > Environment Variables.
Add JAVA_HOME (e.g., C:\Program Files\Java\jdk-17) and ensure the Path includes %JAVA_HOME%\bin and the Maven bin directory.





Project Structure

src/main/java/app/controller/: Contains controllers (FormController.java, CardController.java).
src/main/java/app/model/: Contains the User.java model class.
src/main/java/app/db/: Contains UserDAO.java for MongoDB interaction.
src/main/java/app/util/: Contains utility classes (IDGenerator.java, ExportUtil.java).
src/main/resources/view/: Contains FXML files (form.fxml, card.fxml) and CSS (styles.css).
pom.xml: Maven configuration file with dependencies and plugins.

Dependencies

JavaFX 17.0.2: For the GUI.
MongoDB Driver 4.11.1: For database operations.
iText 7.2.5: For PDF generation.
SLF4J 2.0.9: For logging with MongoDB.

See pom.xml for the full list of dependencies.
Features

Fill out a detailed form to register an Aadhar card.
Preview the Aadhar card with a mock QR code and photo (if uploaded).
Export the Aadhar card as a PDF file.
Save user data to a MongoDB database.

Known Issues

Photo Loading: Ensure the photo file path is accessible. If the photo fails to load, check the file path and permissions.
PDF Export: Requires write permissions to the chosen directory. Run the application as Administrator if necessary.

Contributing
Feel free to fork this repository, make improvements, and submit a pull request. For major changes, please open an issue to discuss your ideas.
License
This project is licensed under the MIT License - see the LICENSE file for details.
