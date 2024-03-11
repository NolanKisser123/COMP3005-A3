
public class main {

    public static void main(String[] args) {
        Database studentDB = new Database("jdbc:postgresql://localhost:5432/Assignment3", "postgres", "admin");
        studentDB.getAllStudents();

    }
}
