# APP URI: https://rick-and-morty-7mcas7jnoq-uc.a.run.app 

# Rick and Morty Character Search Project Overview

This project enables users to search for characters from the "Rick and Morty" series using various search criteria like name, status, species, gender, and type. The application fetches character data from a database using Hibernate ORM and provides search results in JSON format through a Servlet-based backend. The frontend is built using HTML, Bootstrap for styling, and JavaScript for dynamic interaction.

## Features
- **Search by Name**: Enter a character's name to find matching results.
- **Filter by Status, Species, Gender, and Type**: Refine searches using these parameters.
- **Responsive UI**: The interface adjusts to different screen sizes for optimal user experience.

## Technologies Used
- **Java Servlets**: Handle HTTP requests and responses for character search functionality.
- **Hibernate**: Object-relational mapping framework for database interaction.
- **Gson**: Convert Java objects to JSON format for API responses.
- **HTML, Bootstrap, JavaScript**: Frontend technologies for user interface and dynamic content loading.

## How to Run the Project

### Prerequisites
- **Java Development Kit (JDK)** installed on your system.
- **Apache Maven** for managing dependencies (optional, if not already included in the project).

### Steps to Run
1. **Clone the Repository**:
   ```bash
   git clone <repository_url>
   cd RickAndMorty
   ```
   
2. **Set Up Database Configuration**:
- Ensure your Hibernate configuration (hibernate.cfg.xml) points to the correct database URL, username, and password.

3. **Compile the Project**:
- If using Maven:
```bash
mvn clean install
```

4. **Deploy to Servlet Container**:
Deploy the generated WAR file (RickAndMorty.war) to a servlet container like Apache Tomcat or Jetty.
Access the Application:

5. **Deploy to Servlet Container**:
- Deploy the generated WAR file (RickAndMorty.war) to a servlet container like Apache Tomcat or Jetty.

6. **Access the Application**:
- Open a web browser and navigate to http://localhost:8080/RickAndMorty (adjust port number if necessary).

7. **Use the Search Interface**:
- Enter search criteria in the form fields (Name, Status, Species, Gender, Type).
Click the "Search" button to fetch characters matching the specified criteria.

8. **View Search Results**:
- Matching characters will be displayed with their name, status, species, gender, type, and location.
