# Task 6 – Campus event registration

1. Run `schema.sql` in MySQL.
2. Add the MySQL Connector/J driver to the classpath.
3. Update DB_URL, DB_USER and DB_PASSWORD in EventRegistrationApp.java.
4. Compile and run EventRegistrationApp.

The application validates required fields, uses PreparedStatement, enforces a unique student/event pair in the database, and performs JDBC work in SwingWorker so the event-dispatch thread remains responsive. The table is limited to the latest 500 rows in this starter implementation. Do not commit real database credentials.
