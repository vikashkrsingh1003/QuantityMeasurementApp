package com.apps.quantitymeasurement.UnitLayer;

import com.apps.quantitymeasurement.exceptionLayer.QuantityMeasurementException;

// Common contract for all measurable units
public interface IMeasurable {

    // Conversion factor relative to base unit
    double getConversionFactor();

    // Convert value to base unit
    double convertToBaseUnit(double value);

    // Convert from base unit to this unit
    double convertFromBaseUnit(double baseValue);

    // Name of the unit
    String getUnitName();

    // Measurement category (Length, Weight, Volume, Temperature)
    default String getMeasurementType() {
        return this.getClass().getSimpleName();
    }

    // By default arithmetic is supported
    default SupportsArithmetic supportsArithmetic() {
        return () -> true;
    }

    // Validate if arithmetic operation is allowed
    default void validateOperationSupport(String operation) {
        if (!supportsArithmetic().isSupported()) {
            throw new QuantityMeasurementException(
                    operation + " not supported for unit: " + getUnitName());
        }
    }
}