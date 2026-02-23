package com.apps.quantitymeasurement;

import java.util.Objects;

public class Length {

    private final double value;
    private final LengthUnit unit;

    // Enum for supported length units (base unit = inches)
    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
    	YARDS(36.0),         // 1 yard = 36 inches
        CENTIMETERS(0.393701); // 1 cm = 0.393701 inches


        private final double conversionFactorToInches;

        LengthUnit(double conversionFactorToInches) {
            this.conversionFactorToInches = conversionFactorToInches;
        }

        public double toInches(double value) {
            return value * conversionFactorToInches;
        }
    }

    // Constructor
    public Length(double value, LengthUnit unit) {

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        if (value < 0) {
            throw new IllegalArgumentException("Length cannot be negative");
        }

        this.value = value;
        this.unit = unit;
    }

    // Convert any length to base unit (inches)
    private double toInches() {
        return unit.toInches(value);
    }

    // Compare two Length objects
    public boolean compare(Length that) {

        if (that == null) {
            return false;
        }

        return Double.compare(this.toInches(), that.toInches()) == 0;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Length)) return false;

        Length that = (Length) o;

        return Double.compare(this.toInches(), that.toInches()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toInches());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", \"" + unit.name().toLowerCase() + "\")";
    }
}