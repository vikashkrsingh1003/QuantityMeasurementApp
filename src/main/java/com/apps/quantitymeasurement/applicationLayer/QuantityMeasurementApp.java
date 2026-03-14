package com.apps.quantitymeasurement.applicationLayer;

import com.apps.quantitymeasurement.controllerLayer.QuantityMeasurementController;
import com.apps.quantitymeasurement.entityLayer.QuantityDTO;
import com.apps.quantitymeasurement.exceptionLayer.QuantityMeasurementException;
import com.apps.quantitymeasurement.repositoryLayer.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurement.serviceLayer.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        try {

            // Repository
            QuantityMeasurementCacheRepository repository =
                    new QuantityMeasurementCacheRepository();

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
                    controller.convert(q1, QuantityDTO.LengthUnit.INCHES);

            System.out.println("1 FEET in INCHES: "
                    + converted.value);

            // Example 3: Addition
            QuantityDTO added =
                    controller.add(q1, q2);

            System.out.println("Addition result: "
                    + added.value + " " + added.unit.getUnitName());

            // Example 4: Subtraction
            QuantityDTO subtracted =
                    controller.subtract(q1, q2);

            System.out.println("Subtraction result: "
                    + subtracted.value + " " + subtracted.unit.getUnitName());

            // Example 5: Division
            double result =
                    controller.divide(q1,
                            new QuantityDTO(2, QuantityDTO.LengthUnit.FEET));

            System.out.println("Division result: " + result);

            // Example 6: Exception Case (Temperature Arithmetic)
            System.out.println("\nTrying invalid operation: Temperature Addition");

            QuantityDTO t1 =
                    new QuantityDTO(10, QuantityDTO.TemperatureUnit.CELSIUS);

            QuantityDTO t2 =
                    new QuantityDTO(20, QuantityDTO.TemperatureUnit.CELSIUS);

            controller.add(t1, t2);

        } catch (QuantityMeasurementException e) {

            System.out.println("Quantity Measurement Error: "
                    + e.getMessage());

        } catch (Exception e) {

            System.out.println("Unexpected Error: "
                    + e.getMessage());
        }
    }
}