package com.veresklia.school.dao;

import com.veresklia.entity.Group;

import java.sql.SQLException;
import java.util.List;

public interface GroupDao extends CrudDao <Group, Integer> {
    List<Group> findGroupsByStudentsNumber(Integer studentsQuantity) throws SQLException;
}
