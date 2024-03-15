import java.sql.*;

public class Database {
    private String url;
    private String user;
    private String password;

    public Database(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
    }

    /**
     * Retrieves and Displays all records from student table
     */
    public void getAllStudents(){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM students");
            ResultSet resultSet = statement.getResultSet();
            System.out.println("Printing out Student Table:");
            while(resultSet.next()){
                System.out.print(resultSet.getInt("student_id") + "\t");
                System.out.print(resultSet.getString("first_name") + "\t");
                System.out.print(resultSet.getString("last_name") + "\t");
                System.out.print(resultSet.getString("email") + "\t");
                System.out.println(resultSet.getDate("enrollment_date"));
            }
        }
        catch (Exception e){
            System.out.print("Error getting all students: " + e.getMessage());
        }
    }

    /**
     * Inserts a new student record into the students table
     * @param first_name
     * @param last_name
     * @param email
     * @param enrollment_date
     */
    public void addStudent(String first_name, String last_name, String email, Date enrollment_date){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, email);
            preparedStatement.setDate(4, enrollment_date);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Student added successfully.");
        }
        catch (Exception e){
            System.out.print("Error adding student: "+ e.getMessage());
        }
    }

    /**
     * Updates the email address for a student with a specified student_id
     * @param student_id
     * @param new_email
     */
    public void updateStudentEmail(int student_id, String new_email){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE students SET email = ? WHERE student_id = ?");
            preparedStatement.setString(1, new_email);
            preparedStatement.setInt(2, student_id);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            if (rowsAffected > 0){
                System.out.println("Email updated successfully.");
            } else {
                System.out.println("No student found with ID " + student_id);
            }
        }
        catch (Exception e){
            System.out.print("Error updating email: " + e.getMessage());
        }
    }

    /**
     * Deletes the record of the student with the specified student_id
     * @param student_id
     */
    public void deleteStudent(int student_id){
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM students WHERE student_id = ?");
            preparedStatement.setInt(1, student_id);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            if (rowsAffected > 0){
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("No student found with ID " + student_id);
            }
        }
        catch (Exception e){
            System.out.print("Error deleting student: " + e.getMessage());
        }
    }
}
