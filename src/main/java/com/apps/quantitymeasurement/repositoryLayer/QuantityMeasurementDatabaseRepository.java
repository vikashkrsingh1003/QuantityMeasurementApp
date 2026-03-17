package com.apps.quantitymeasurement.repositoryLayer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.apps.quantitymeasurement.entityLayer.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.util.ConnectionPool;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

    public QuantityMeasurementDatabaseRepository() {
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {

        String sql = """
                CREATE TABLE IF NOT EXISTS QUANTITY_MEASUREMENT_ENTITY (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    operation VARCHAR(50),
                    operand1 VARCHAR(100),
                    operand2 VARCHAR(100),
                    result VARCHAR(100),
                    error_message VARCHAR(255),
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
                """;

        try (Connection connection = ConnectionPool.getConnection();
             Statement stmt = connection.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {

        String sql = """
                INSERT INTO QUANTITY_MEASUREMENT_ENTITY
                (operation, operand1, operand2, result, error_message)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

        	ps.setString(1, entity.getOperation());

        	ps.setString(2, entity.getThisValue() + " " + entity.getThisUnit());

        	ps.setString(3, entity.getThatValue() + " " + entity.getThatUnit());

        	ps.setString(4, entity.getResultValue() + " " + entity.getResultUnit());

        	ps.setString(5, entity.getErrorMessage());

        	ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save measurement", e);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> findAll() {

        // Your entity does not have a constructor for DB mapping yet,
        // so returning empty list for now to avoid errors.

        return new ArrayList<>();
    }
}