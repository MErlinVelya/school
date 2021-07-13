package com.veresklia.school.dao;

import com.ibatis.common.jdbc.ScriptRunner;
import com.veresklia.entity.Group;
import com.veresklia.school.dao.connector.DatabaseConnector;
import com.veresklia.school.dao.connector.DatabaseConnectorImpl;
import org.apache.ibatis.io.Resources;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class GroupDaoImplTest {
    private final DatabaseConnector databaseConnector =
        new DatabaseConnectorImpl("src/test/resources/connection_mac_test.properties");

    @BeforeEach
        private void init(){
            UtilityBeforeEach.beforeEach(databaseConnector);
        }

    @Test
    void findGroupsByStudentsNumberReturnWithListWhenProvidedInt () throws SQLException {
        List<Group> expected = new ArrayList<>();
        Group group = new Group("one-11", 1);
        Group group2 = new Group("two-22", 1);
        expected.add(group);
        expected.add(group2);
        GroupDao groupDao = new GroupDaoImpl<Group, Integer>(databaseConnector);
        List<Group> actual = groupDao.findGroupsByStudentsNumber(0);
        assertThat(actual, is(expected));
    }

    @Test
    void updateUpdatingDatabaseWhenProvidedEntity(){
        final GroupDaoImpl groupDao = new GroupDaoImpl(databaseConnector);
        Group expected = new Group("aa",0);

        groupDao.update(expected);

        List<Group> search = groupDao.findAll();
        Group actual = search.get(2);

        assertThat(actual, is(expected));
    }

    @Test
    void insertincludingDataToPreparedSatatementWhenProvidedEntity(){
        final GroupDaoImpl groupDao = new GroupDaoImpl(databaseConnector);
        Group expected = new Group("aa",0);

        groupDao.save(expected);

        List<Group> groups = groupDao.findAll();
        Group actual = groups.get(2);

        assertThat(actual, is(expected));
    }
}
