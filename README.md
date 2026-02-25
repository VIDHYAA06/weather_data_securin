# weather_data_securin

This is a Spring Boot backend application that processes nearly 20 years of Delhi weather forecast data from a CSV file and exposes REST APIs to retrieve filtered weather details and monthly temperature statistics.

The application:

Accepts large CSV file uploads
Cleans and stores data in MongoDB
Provides month-wise weather retrieval
Calculates high, minimum, and median temperature
Follows layered architecture (Controller → Service → Repository)

Technologies Used:

Java 17
Spring Boot
Spring Data MongoDB
MongoDB
OpenCSV
Maven
Lombok

Project Structure:

<img width="404" height="739" alt="image" src="https://github.com/user-attachments/assets/04642c4f-6c78-4967-aaf9-fbf6c6da5064" />



 END POINTS:
 Base url : http://localhost:8081/api/weather
 
http://localhost:8081/api/weather/upload  (POST)
http://localhost:8081/api/weather/month?year=1996&month=11 (GET)
http://localhost:8081/api/weather/stats/1996 (GET)

UPLOAD SUCCESS:
<img width="1562" height="639" alt="upload_success" src="https://github.com/user-attachments/assets/bd2d95dc-8178-480e-adbc-a522b289fe37" />
MONTH OUTPUT:
<img width="1707" height="812" alt="month_output" src="https://github.com/user-attachments/assets/3f4bc745-a47b-4808-813a-f1d2af339f8f" />
STATS_OUTPUT:
<img width="1730" height="830" alt="Screenshot 2026-02-25 124218" src="https://github.com/user-attachments/assets/5c12687a-4571-4414-91b0-f42111ea6ead" />

/>





 
