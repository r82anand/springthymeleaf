Spring5MVCThymeleafEmail
========================

What is it?
-----------

This is your project! It is a sample, deployable Maven 3 project to help you get your foot in the door developing with Spring on Tomcat 7. 

This project is setup to allow you to create a compliant Spring 5 application using Spring MVC. 

System requirements
-------------------

All you need to build this project is Java 8.0 (Java SDK 1.8) or better, Maven 3.0 or better.

The application this project produces is designed to be run on Tomcat 7. 

Build and Deploy the Application
-------------------------

1. Open a command line and navigate to the root directory of this quickstart.
2. Type this command to build and deploy the archive:

        mvn clean package tomcat7:run

3. This will deploy `target/Spring5MVCThymeleafEmail.war` to the instance of the server.


Access the application 
---------------------
 
The application will be running at the following URL: <http://localhost:8080/hello/>.


Undeploy the Archive
--------------------

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. When you are finished testing, type this command to undeploy the archive:

        mvn jboss-as:undeploy


Debug the Application
------------------------------------

If you want to debug the source code or look at the Javadocs of any library in the project, run either of the following commands to pull them into your local repository. The IDE should then detect them.

        mvn dependency:sources
        mvn dependency:resolve -Dclassifier=javadoc

I am using a  Gmail account to send a message because  Gmail is a common  Mail-Server . However, for your  Gmail account to be able to send an email via a  Java application, you have to tell the  Google to allow it. OK, first of all, on the browser, you log in your Gmail account and then visit the following address:
https://www.google.com/settings/security/lesssecureapps
Activate to allow your Gmail account to be used by  Less Secure Apps.
