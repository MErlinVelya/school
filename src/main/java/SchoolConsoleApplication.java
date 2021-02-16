

import com.veresklia.connector.DatabaseConnector;
import com.veresklia.provider.ContentProvider;
import com.veresklia.provider.DatabiseFiller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SchoolConsoleApplication {

    public static void main(String[] args) throws SQLException {
       ContentProvider contentProvider = new ContentProvider();
//        Map<String, String> students = contentProvider.generateStudents(200);
//        students.forEach((k, v) -> System.out.println(k + " " + v));
//        List<String[]> students = contentProvider.generateStudents(200);
//
////        students.forEach(a -> System.out.println(a[0] +" " + a[1]));
//     //   System.out.println((char)90);
//
//        String[] groups = contentProvider.generateGroups(10);
//        System.out.println(groups[9]);
        DatabaseConnector databaseConnector = new DatabaseConnector();

        DatabiseFiller databiseFiller = new DatabiseFiller();

           // Connection con = databaseConnector.connect("jdbc:postgresql://localhost:5433/school", "postgres", "admin");
        Connection con = databaseConnector.connect("jdbc:postgresql://localhost:5432/school", "merlin", "");

        //databiseFiller.createTables(con);
      databiseFiller.fillTables(con, contentProvider.generateGroups(10), contentProvider.getCourses(), contentProvider.generateStudents(200));



    }
}
