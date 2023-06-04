# Online-Course-Managment-System-Final-Project
## SQL Code Overview:
    The project is a database management system for online courses. It involves the management of students, courses, instructors, lessons, and their relationships.
The database includes the following entities:
1.	Student: Stores information about students, such as their ID, name, email, and password.
2.	Course: Contains information about the available courses, including the course ID, title, description, and duration.
3.	Instructor: Stores details about the instructors, including their ID, name, email, and specialization.
4.	Lesson: Stores information about the lessons associated with a course, including the lesson ID, course ID, lesson title, lesson description, and lesson date.

And their relationships:
5.	Enrollment: Represents the relationship between students and courses, including the enrollment ID, student ID, course ID, and enrollment date.
6.	Student_Lesson: Represents the many-to-many relationship between students and lessons, linking a student to the lessons they are enrolled in.
7.	Instructor_Course: Represents the relationship between instructors and the courses they teach, with the instructor ID and course ID as the primary key.

Also, it includes sample data insertion into the tables to populate the database with initial records.

Java Code Overview:
    In the other hand, the database has been connected with Java code which represents a simple CRUD (Create, Read, Update, Delete) system that represents (Insert, Select, Update, Delete). that interacts with a database. It allows users to perform basic database operations through a command-line interface.

The code uses JDBC (Java Database Connectivity) to connect to an Oracle database and execute SQL statements. It prompts the user with a menu, and based on the user's choice, it performs the corresponding operation.
Here's a breakdown of the code:
1. The code imports necessary packages, including the JDBC package, and declares the necessary constants for the database connection.
2. The main method establishes a connection to the database, displays a menu to the user, and prompts for their choice. Based on the choice, it calls the corresponding method to perform the selected operation.
3. The addRecord method allows the user to add a new record to a table. It prompts the user for the table name and retrieves the column information using ResultSetMetaData. It constructs an SQL INSERT statement dynamically based on the column names and prepares a PreparedStatement with placeholders. It prompts the user for values for each column and executes the INSERT statement.
4. The readRecord method allows the user to retrieve and display records from a table. It prompts the user for the table name and executes a simple SELECT statement. It retrieves the result set and displays the column names and corresponding values for each row.
5. The updateRecord method allows the user to update an existing record in a table. It prompts the user for the table name and retrieves the column information using ResultSetMetaData. It constructs an SQL UPDATE statement dynamically based on the column names and prepares a PreparedStatement with placeholders. It prompts the user for new values for each column and the ID of the record to update. It executes the UPDATE statement.
6. The deleteRecord method allows the user to delete a record from a table. It prompts the user for the table name and retrieves the column information using ResultSetMetaData. It prompts the user for the ID of the record to delete. It constructs and executes an SQL DELETE statement.
7. The code includes exception handling to catch any SQL-related exceptions that may occur during the execution of database operations.

Overall, the project aims to facilitate the management of students, courses, instructors, enrollments, and lessons for online course management system. and provides a basic java framework for interacting with a database using JDBC and performing CRUD operations.

Recourses:
-	 Full Project Explanation – 1 HOUR Video [YouTube].
