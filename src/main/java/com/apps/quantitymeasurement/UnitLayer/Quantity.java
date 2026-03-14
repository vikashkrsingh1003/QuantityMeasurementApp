package com.apps.quantitymeasurement.UnitLayer;

import com.apps.quantitymeasurement.exceptionLayer.QuantityMeasurementException;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (unit == null) {
            throw new QuantityMeasurementException("Unit cannot be null");
        }

        if (Double.isNaN(value)) {
            throw new QuantityMeasurementException("Value cannot be NaN");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // Convert quantity to another unit
    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null) {
            throw new QuantityMeasurementException("Target unit cannot be null");
        }

        double baseValue = unit.convertToBaseUnit(value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(convertedValue, targetUnit);
    }

    // Addition
    public Quantity<U> add(Quantity<U> other) {

        if (other == null) {
            throw new QuantityMeasurementException("Quantity to add cannot be null");
        }

        unit.validateOperationSupport("ADD");

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double resultBase = base1 + base2;

        double result = unit.convertFromBaseUnit(resultBase);

        return new Quantity<>(result, unit);
    }

    // Addition with explicit target unit
    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (other == null) {
            throw new QuantityMeasurementException("Quantity to add cannot be null");
        }

        if (targetUnit == null) {
            throw new QuantityMeasurementException("Target unit cannot be null");
        }

        unit.validateOperationSupport("ADD");

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double resultBase = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(result, targetUnit);
    }

    // Subtraction
    public Quantity<U> subtract(Quantity<U> other) {

        if (other == null) {
            throw new QuantityMeasurementException("Quantity to subtract cannot be null");
        }

        unit.validateOperationSupport("SUBTRACT");

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double resultBase = base1 - base2;

        double result = unit.convertFromBaseUnit(resultBase);

        return new Quantity<>(result, unit);
    }

    // Subtraction with explicit target unit
    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        if (other == null) {
            throw new QuantityMeasurementException("Quantity to subtract cannot be null");
        }

        if (targetUnit == null) {
            throw new QuantityMeasurementException("Target unit cannot be null");
        }

        unit.validateOperationSupport("SUBTRACT");

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double resultBase = base1 - base2;

        double result = targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(result, targetUnit);
    }

    // Division
    public double divide(Quantity<U> other) {

        if (other == null) {
            throw new QuantityMeasurementException("Quantity to divide cannot be null");
        }

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        if (base2 == 0) {
            throw new QuantityMeasurementException("Division by zero is not allowed");
        }

        return base1 / base2;
    }

    // Equality comparison
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Quantity<?> other = (Quantity<?>) obj;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < 0.0001;
    }

    @Override
    public int hashCode() {
        double baseValue = unit.convertToBaseUnit(value);
        return Double.valueOf(baseValue).hashCode();
    }
}