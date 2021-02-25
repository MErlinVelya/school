

import com.veresklia.controller.InterfaceProvider;
import com.veresklia.dao.connector.DatabaseConnector;
import com.veresklia.domain.Group;
import com.veresklia.domain.provider.ContentProvider;
import com.veresklia.dao.DatabiseFiller;

import java.sql.Connection;
import java.sql.SQLException;

public class SchoolConsoleApplication {

    public static void main(String[] args) throws SQLException {
        ContentProvider contentProvider = new ContentProvider();
        DatabaseConnector databaseConnector = new DatabaseConnector();
        DatabiseFiller databiseFiller = new DatabiseFiller();
    Connection con = databaseConnector.connect("jdbc:postgresql://localhost:5433/school", "postgres", "admin");
  //      Connection con = databaseConnector.connect("jdbc:postgresql://localhost:5432/school", "merlin", "");

        databiseFiller.createTables(con);
        Group[] groups = contentProvider.generateGroups(10, 10, 30);

        databiseFiller.fillTables(con, groups, contentProvider.getCourses(), contentProvider.generateStudentsAllIn(groups, 1, 3, 200));
        InterfaceProvider interfaceProvider = new InterfaceProvider();
        interfaceProvider.provideInterface();
    }
}
