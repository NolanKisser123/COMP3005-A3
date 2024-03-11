import java.time.LocalDate;
import java.sql.Date;

public class main {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now(); // Create a LocalDate object
        Date current_date = java.sql.Date.valueOf(localDate); //Convert LocalDate to Date object
        Database studentDB = new Database("jdbc:postgresql://localhost:5432/Assignment3", "postgres", "admin");
        studentDB.addStudent("test", "test", "test@cmail.com", current_date);
        studentDB.getAllStudents();

    }
}
