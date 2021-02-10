

import com.veresklia.provider.ContentProvider;

import java.util.List;
import java.util.Map;

public class SchoolConsoleApplication {

    public static void main(String[] args) {
        ContentProvider contentProvider = new ContentProvider();
//        Map<String, String> students = contentProvider.generateStudents(200);
//        students.forEach((k, v) -> System.out.println(k + " " + v));
        List<String[]> students = contentProvider.generateStudents(200);

//        students.forEach(a -> System.out.println(a[0] +" " + a[1]));
     //   System.out.println((char)90);

        String[] groups = contentProvider.generateGroups(10);
        System.out.println(groups[9]);
    }
}
