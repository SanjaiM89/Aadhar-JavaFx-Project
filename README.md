
---

# 🆔 AadharClone

**AadharClone** is a JavaFX application that simulates the registration and generation of an Aadhar card. It features a form to input user details, a card preview, and an option to export the card as a PDF. The project uses **Maven** for dependency management and **MongoDB** for data persistence.

---

## 📦 Prerequisites

Before running the project, ensure the following are installed:

* **Java 17** or later
* **Maven** – for building the project
* **MongoDB** – for storing user data
* **Git** – for cloning the repository

---

## 🛠️ Project Setup

### 1. Clone the Repository

```bash
git clone https://github.com/<your-username>/AadharClone.git
cd AadharClone
```

### 2. Install Dependencies

Run the following command to install Maven dependencies:

```bash
mvn install
```

This downloads all dependencies listed in `pom.xml` (JavaFX, MongoDB, iText 7, etc.).

### 3. Set Up MongoDB

#### On Linux

```bash
sudo apt update
sudo apt install -y mongodb
sudo systemctl start mongodb
sudo systemctl status mongodb
```

#### On Windows

1. Download MongoDB Community Server from the [official website](https://www.mongodb.com/try/download/community).
2. Install MongoDB as a service (recommended).
3. Start MongoDB:

   * If installed as a service, it starts automatically.
   * Or run `mongod` from the MongoDB bin directory.

To verify MongoDB is running:

```bash
mongo
```

---

## 🚀 Running the Project

### On Linux

```bash
# Build the project
mvn clean install

# Run the application
mvn javafx:run
```

### On Windows

```bash
mvn clean install
mvn javafx:run
```

---

## 🧾 How It Works

1. The application opens a form (`form.fxml`) to enter user details.
2. Fill all required fields, optionally upload a photo, then click **"Generate Aadhar"**.
3. A preview of the card (`card.fxml`) will appear.
4. Click **"Export as PDF"** to save the Aadhar card.

---

## 🧯 Troubleshooting

### Linux

* **Locale Warning**:

  ```bash
  sudo locale-gen en_US.UTF-8
  export LC_ALL=en_US.UTF-8
  export LANG=en_US.UTF-8
  ```

* **File Permission Errors**:

  ```bash
  chmod -R u+rw .
  ```

* **MongoDB Issues**:
  Ensure MongoDB is running on `localhost:27017`.

### Windows

* **Permission Issues**:

  * Run terminal as Administrator if necessary.
  * Check write permissions for output folders.

* **Path Issues**:

  * Set `JAVA_HOME` and add to `Path`:

    ```
    JAVA_HOME=C:\Program Files\Java\jdk-17
    PATH=%JAVA_HOME%\bin;C:\path\to\maven\bin
    ```

---

## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   ├── app/controller/     # FormController.java, CardController.java
│   │   ├── app/model/          # User.java
│   │   ├── app/db/             # UserDAO.java
│   │   └── app/util/           # IDGenerator.java, ExportUtil.java
│   └── resources/
│       └── view/               # form.fxml, card.fxml, styles.css
pom.xml                         # Maven config file
```

---

## 📚 Dependencies

* **JavaFX 17.0.2** – UI
* **MongoDB Driver 4.11.1** – DB access
* **iText 7.2.5** – PDF generation
* **SLF4J 2.0.9** – Logging

Check `pom.xml` for the full list.

---

## ✨ Features

* Fill out detailed form to register an Aadhar card.
* Preview Aadhar card with mock QR and photo.
* Export Aadhar card as PDF.
* Persist user data using MongoDB.

---

## ⚠️ Known Issues

* **Photo Loading**: Ensure the photo path is valid and accessible.
* **PDF Export**: Requires write permission in the export directory.

---

## 🤝 Contributing

Contributions are welcome!

* Fork the repo
* Make improvements
* Submit a pull request

> For major changes, open an issue to discuss your ideas.

---

## 📄 License

This project is licensed under the **MIT License**.
See the [LICENSE](./LICENSE) file for details.

---

Would you like me to help generate a logo or banner for this GitHub project?
