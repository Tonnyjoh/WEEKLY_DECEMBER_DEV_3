# Week 3 Project Setup

## Prerequisites
- **Apache Tomcat**: Ensure you have `apache-tomcat-10.x.x` installed.
- **MySQL**: Make sure MySQL is set up on your machine.

## Steps to Setup the Project

### 1. Unzip the Project
- Unzip the `Week-3-main` folder into the `webapps` directory of your Tomcat installation.
    - **Example Path**: `C:\Program Files\apache-tomcat-10.1.15\webapps\Week-3-main`

### 2. Project Structure
- Inside the `Week-3-main` directory, ensure the following structure:
    ```
    Week-3-main/
    ├── WEB-INF/
    └── other directories...
    ```

### 3. Start Apache Tomcat
- Navigate to the `bin` folder of your Tomcat installation:
    ```
    C:\Program Files\apache-tomcat-10.1.15\bin
    ```
- Launch the server by running:
    ```
    startup.bat
    ```

### 4. Access the Application
- Open your browser and go to:
    ```
    http://localhost:8080/Week-3-main/home
    ```

### 5. Configure `web.xml`
- Create a directory for your project:
    ```
    C:/Users/@youpseudo/Documents/tp
    ```
- Inside this directory, create two folders: `public` and `private`.
  
- Update the `web.xml` file to reflect the new directories:
    ```xml
    <param-value>C:/Users/@youpseudo/Documents/tp/</param-value>
    <location>C:/Users/@youpseudo/Documents/tp</location>
    ```

### 6. Setup MySQL Database
- Open your MySQL client and execute the following commands:
    ```sql
    CREATE DATABASE week3;
    USE week3;

    CREATE TABLE Customer (
      id INT NOT NULL AUTO_INCREMENT,
      email VARCHAR(60) NOT NULL,
      mdpass VARCHAR(32) NOT NULL,
      name VARCHAR(20) NOT NULL,
      PRIMARY KEY (id),
      UNIQUE (email)
    ) ENGINE = INNODB;
    ```

### 7. Configure Database Connection
- Navigate to the `dao.properties` file:
    ```
    C:\Program Files\apache-tomcat-10.1.15\webapps\Week-3-main\WEB-INF\classes\com\ti\dao
    ```
- Modify the following properties:
    ```
    url = jdbc:mysql://localhost:3306/week3
    driver = com.mysql.jdbc.Driver
    username = @yourname
    password = @yourpassword
    ```

### 8. Restart the Server
- Restart your Tomcat server to apply the changes.

### 9. Test File Download
- You can test file downloads by visiting:
    ```
    http://localhost:8080/Week-3-main/fichiers/public/test.txt
    ```

## Conclusion
Follow the above steps to set up your Week 3 project successfully. If you encounter any issues, double-check the paths and configurations.
