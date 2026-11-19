# Task 1 – Fee payment design

Compile `FeeService.java` and `FeeWindow.java` with a JDBC driver and a configured database connection supplied by the calling application. The GUI delegates validation and persistence to `FeeService`, uses a parameterized SQL statement, and handles window closing. The receipt action displays a basic receipt dialog; extend it to retrieve a persisted payment record for a production receipt.

The original design defects include string comparison with `==`, no input validation, SQL concatenation, database work and sleep on the event thread, no useful feedback, no real receipt implementation, unclosed resources, no close handling, and unhandled checked exceptions.
