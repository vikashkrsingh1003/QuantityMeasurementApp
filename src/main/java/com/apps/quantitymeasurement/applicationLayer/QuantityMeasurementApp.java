package com.apps.quantitymeasurement.applicationLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.apps.quantitymeasurement.controllerLayer.QuantityMeasurementController;
import com.apps.quantitymeasurement.entityLayer.QuantityDTO;
import com.apps.quantitymeasurement.exceptionLayer.QuantityMeasurementException;
import com.apps.quantitymeasurement.repositoryLayer.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.repositoryLayer.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurement.repositoryLayer.QuantityMeasurementDatabaseRepository;
import com.apps.quantitymeasurement.serviceLayer.QuantityMeasurementServiceImpl;
import com.apps.quantitymeasurement.util.ApplicationConfig;
import com.apps.quantitymeasurement.util.ConnectionPool;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        try {

            // Select repository type
            IQuantityMeasurementRepository repository;

            String repositoryType =
                    ApplicationConfig.getProperty("repository.type");

            if ("database".equals(repositoryType)) {
                repository =
                        new QuantityMeasurementDatabaseRepository();
            } else {
                repository =
                        QuantityMeasurementCacheRepository.getInstance();
            }

            // Service
            QuantityMeasurementServiceImpl service =
                    new QuantityMeasurementServiceImpl(repository);

            // Controller
            QuantityMeasurementController controller =
                    new QuantityMeasurementController(service);

            // Example 1: Comparison
            QuantityDTO q1 =
                    new QuantityDTO(1, QuantityDTO.LengthUnit.FEET);

            QuantityDTO q2 =
                    new QuantityDTO(12, QuantityDTO.LengthUnit.INCHES);

            System.out.println("1 FEET equals 12 INCHES: "
                    + controller.compare(q1, q2));

            // Example 2: Conversion
            QuantityDTO converted =
                    controller.convert(q1,
                            QuantityDTO.LengthUnit.INCHES);

            System.out.println("1 FEET in INCHES: "
                    + converted.value);

            // Example 3: Addition
            QuantityDTO added =
                    controller.add(q1, q2);

            System.out.println("Addition result: "
                    + added.value + " "
                    + added.unit.getUnitName());

            // Example 4: Subtraction
            QuantityDTO subtracted =
                    controller.subtract(q1, q2);

            System.out.println("Subtraction result: "
                    + subtracted.value + " "
                    + subtracted.unit.getUnitName());

            // Example 5: Division
            double result =
                    controller.divide(q1,
                            new QuantityDTO(2,
                                    QuantityDTO.LengthUnit.FEET));

            System.out.println("Division result: "
                    + result);

            // Example 6: Exception Case
            System.out.println("\nTrying invalid operation: Temperature Addition");

            QuantityDTO t1 =
                    new QuantityDTO(10,
                            QuantityDTO.TemperatureUnit.CELSIUS);

            QuantityDTO t2 =
                    new QuantityDTO(20,
                            QuantityDTO.TemperatureUnit.CELSIUS);

            controller.add(t1, t2);

            // -------- DATABASE PART FROM FIRST MAIN --------

            Connection conn = ConnectionPool.getConnection();
            Statement stmt = conn.createStatement();

            System.out.println("\nTables in Database:");

            ResultSet tables =
                    stmt.executeQuery("SHOW TABLES");

            while (tables.next()) {
                System.out.println(tables.getString(1));
            }

            System.out.println("\nStored Measurements:");

            ResultSet rs =
                    stmt.executeQuery(
                            "SELECT * FROM QUANTITY_MEASUREMENT_ENTITY");

            while (rs.next()) {

                System.out.println(
                        rs.getLong("id") + " | " +
                        rs.getString("operation") + " | " +
                        rs.getString("operand1") + " | " +
                        rs.getString("operand2") + " | " +
                        rs.getString("result")
                );
            }

        } catch (QuantityMeasurementException e) {

            System.out.println("Quantity Measurement Error: "
                    + e.getMessage());

        } catch (Exception e) {

            System.out.println("Unexpected Error: "
                    + e.getMessage());
        }
    }
}