package com.veresklia.school.dao;

import com.veresklia.entity.Group;
import com.veresklia.school.dao.connector.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GroupDaoImpl<E, ID> extends CrudDaoImpl<Group, Integer> implements GroupDao {
    private static final String SAVE_QUERY = "INSERT INTO groups (group_name) VALUES (?)";
    private static final String FIND_BY_ID_QUERY = "select group_id, group_name from groups where group_id = ?";
    private static final String FIND_ALL_QUERY = "select group_name from groups";
    private static final String UPDATE_QUERY = "INSERT INTO groups (group_name) VALUES (?)";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM groups WHERE group_id = ?";
    private static final String FIND_GROUPS_BY_STUDENTS_NUMBER = "SELECT groups.group_name, COUNT(groups.group_name) FROM groups INNER JOIN students ON " +
            "groups.group_id = students.group_id GROUP BY groups.group_name HAVING COUNT(groups.group_name) > ?";

    public GroupDaoImpl(DatabaseConnector databaseConnector) {
        super(SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY,databaseConnector);
    }

    @Override
    protected Group mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Group returningGroup = new Group(resultSet.getString(1));
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columns = rsmd.getColumnCount();
        if(columns > 1){
            returningGroup.setStudentsquantity(resultSet.getInt(2));

        }
        return returningGroup;
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, Group entity) throws SQLException {
        preparedStatement.setString(1, entity.getGroupName());
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, Group entity) throws SQLException {
        preparedStatement.setString(1, entity.getGroupName());
    }

    @Override
    public List<Group> findGroupsByStudentsNumber(Integer studentsQuantity) throws SQLException {
        List<Group> groups = new ArrayList<>();
        try (Connection connection = databaseConnector.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_GROUPS_BY_STUDENTS_NUMBER)) {
            preparedStatement.setInt(1, studentsQuantity);
            ResultSet groupsNames = preparedStatement.executeQuery();
            while (groupsNames.next()) {
                groups.add(mapResultSetToEntity(groupsNames));
            }
            return groups;
        }
    }
}
