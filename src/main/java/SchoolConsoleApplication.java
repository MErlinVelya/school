

import com.veresklia.dao.connector.DatabaseConnector;
import com.veresklia.domain.Student;
import com.veresklia.domain.provider.ContentProvider;
import com.veresklia.dao.DatabiseFiller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SchoolConsoleApplication {

    public static void main(String[] args) throws SQLException {
       ContentProvider contentProvider = new ContentProvider();
        DatabaseConnector databaseConnector = new DatabaseConnector();
//
        DatabiseFiller databiseFiller = new DatabiseFiller();
//
       Connection con = databaseConnector.connect("jdbc:postgresql://localhost:5433/school", "postgres", "admin");
 //       Connection con = databaseConnector.connect("jdbc:postgresql://localhost:5432/school", "merlin", "");

        databiseFiller.createTables(con);

   databiseFiller.fillTables(con, contentProvider.generateGroups(10, 10, 30), contentProvider.getCourses(), contentProvider.generateStudentsAllIn(10, 30, 1, 3, 200));


    //    List<Student> students = contentProvider.generateStudentsAllIn(10,30,1,3,200);
        //System.out.println(students.toString());


    }
}
