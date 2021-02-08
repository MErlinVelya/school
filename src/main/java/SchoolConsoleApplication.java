import com.veresklia.provider.ContentProvider;

import java.util.Map;

public class SchoolConsoleApplication {

    public static void main(String[] args) {
        ContentProvider contentProvider = new ContentProvider();
        Map<String, String> students = contentProvider.generateStudents(200);
        students.forEach((k, v) -> System.out.println(k + " " + v));
    }
}
