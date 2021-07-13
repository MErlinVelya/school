package com.veresklia.entity.provider;

import com.veresklia.entity.Group;
import com.veresklia.entity.Student;

import java.util.List;

public interface ContentProvider {
    List<Group> generateGroups(int groupsNumder, int minStudents, int maxStudents);

    List<Student> generateStudentsAllIn(List<Group> groups, int minCourses, int maxCourses, int numberOfStudents);
}
