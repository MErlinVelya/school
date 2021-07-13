package com.veresklia.school.dao;

import com.veresklia.entity.exception.DaoLevelException;
import com.veresklia.school.dao.connector.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class CrudDaoImpl<E, id> implements CrudDao<E, Integer> {

    private final String saveQuery;
    private final String findByIdQuery;
    private final String findAllQuery;
    private final String updateQuery;
    private final String deleteByIdQuery;
    protected final DatabaseConnector databaseConnector;

    protected CrudDaoImpl(String saveQuery, String findByIdQuery,
                       String findAllQuery, String updateQuery, String deleteByIdQuery, DatabaseConnector databaseConnector) {
        this.saveQuery = saveQuery;
        this.findByIdQuery = findByIdQuery;
        this.findAllQuery = findAllQuery;
        this.updateQuery = updateQuery;
        this.deleteByIdQuery = deleteByIdQuery;
        this.databaseConnector = databaseConnector;
    }

    @Override
    public Integer save(E entity) {
        Integer entityId = -1;
        try (Connection connection = databaseConnector.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(saveQuery, Statement.RETURN_GENERATED_KEYS))
        {
            insert(preparedStatement, entity);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                entityId = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Insertion is failed", e);
        }
        return entityId;
    }

    @Override
    public void deleteById(Integer id) {
        try (Connection connection = databaseConnector.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteByIdQuery)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Insertion is failed", e);
        }
    }

    @Override
    public Optional<E> findById(Integer id) {
            try (Connection connection = databaseConnector.connect();
                 PreparedStatement preparedStatement = connection.prepareStatement(findByIdQuery)){
                 preparedStatement.setInt(1, id);
                {
                    try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                        return resultSet.next() ? Optional.ofNullable(mapResultSetToEntity(resultSet)) : Optional.empty();
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

        @Override
        public List<E> findAll () {
            try (Connection connection = databaseConnector.connect();
                 PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery)) {
                try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                    List<E> entities = new ArrayList<>();
                    while (resultSet.next()) {
                        entities.add(mapResultSetToEntity(resultSet));
                    }
                    return entities;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

@Override
public List<E> findAll (int page, int pageLimit) {
    int limitOnPage = pageLimit;
    String findAllQueryWithPagenation = findAllQuery + " LIMIT " +
        limitOnPage + " OFFSET " + (limitOnPage * page - limitOnPage);
    try (Connection connection = databaseConnector.connect();
         PreparedStatement preparedStatement = connection.prepareStatement(findAllQueryWithPagenation)) {
        try (final ResultSet resultSet = preparedStatement.executeQuery()) {
            List<E> entities = new ArrayList<>();
            while (resultSet.next()) {
                entities.add(mapResultSetToEntity(resultSet));
            }
            return entities;
        }
    } catch (SQLException e) {
        throw new DaoLevelException("findAll", e);
    }
}

        @Override
        public void update (E entity){
            try (Connection connection = databaseConnector.connect();
                 PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

                updateValues(preparedStatement, entity);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new DaoLevelException("update", e);
            }
        }

        protected abstract E mapResultSetToEntity (ResultSet resultSet) throws SQLException;

        protected abstract void insert (PreparedStatement preparedStatement, E entity) throws SQLException;

        protected abstract void updateValues (PreparedStatement preparedStatement, E entity) throws SQLException;

}
