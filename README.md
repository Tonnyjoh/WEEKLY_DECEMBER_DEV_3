 If you use apache-tomcat-10.x.x
 Unzip the folder into the “webapps” directory
  ex: C:\Program Files\apache-tomcat-10.1.15\webapps\Week-3-main
 Under Week-3-main, we must have the WEB-INF directory and others.
 From Windows, go to the /bin folder of your Tomcat installation, and launch the file called startup.bat
# Entry link
  ex: http://localhost:8080/Week-3-main/home
# In the web.xml file, make changes:
create a directory C:/Users/@youpseudo/Documents/tp in which you will create a "public" folder and a "private" folder
Now change directories in web.xml
  ex:  "<param-value>C:/Users/ynnot/Documents/tp/</param-value>" and
      "<location>C:/Users/ynnot/Documents/tp</location>"
# Pay attention to the "/".
 IUse MySQL,
        CREATE DATABASE week3;
        USEweek3;
        CREATE TABLE Customer (
          id INT NOT NULL AUTO_INCREMENT ,
          email VARCHAR(60) NOT NULL,
          mdpass VARCHAR(32) NOT NULL,
          name VARCHAR( 20 ) NOT NULL ,
          PRIMARY KEY (id),
          UNIQUE (email)
        ) ENGINE = INNODB;
# Navigate to "dao.properties" in C:\Program Files\apache-tomcat-10.1.15\webapps\week3\WEB-INF\classes\com\ti\dao and change
  url = jdbc:mysql://localhost:3306/week3
  driver = com.mysql.jdbc.Driver
  username = @yourname
  password = @yourpassword
# Restart your server.  
