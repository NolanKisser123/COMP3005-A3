import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
            while(resultSet.next()){
                System.out.print(resultSet.getInt("student_id") + "\t");
                System.out.print(resultSet.getString("first_name") + "\t");
                System.out.print(resultSet.getString("last_name") + "\t");
                System.out.print(resultSet.getString("email") + "\t");
                System.out.println(resultSet.getDate("enrollment_date"));
            }
        }
        catch (Exception e){
            System.out.print(e);
        }
    }

    /**
     * Inserts a new student record into the students table
     * @param first_name
     * @param last_name
     * @param email
     * @param date
     */
    public void addStudents(String first_name, String last_name, String email, String date){

    }

    /**
     * Updates the email address for a student with a specified student_id
     * @param student_id
     * @param new_email
     */
    public void updateStudentEmail(int student_id, String new_email){

    }

    /**
     * Deletes the record of the student with the specified student_id
     * @param student_id
     */
    public void deleteStudent(int student_id){

    }
}
