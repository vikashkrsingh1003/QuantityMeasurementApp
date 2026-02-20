package com.apps.quantiymeasurement;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.apps.quantitymeasurement.QuantityMeasurementApp;

public class QuantityMeasurementAppTest {

	@Test
	void testEquality_SameValue() {
		QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
		QuantityMeasurementApp.Feet f2 = new QuantityMeasurementApp.Feet(1.0);
		assertTrue(f1.equals(f2), "1.0 ft should be equal to 1.0 ft");
	}

	@Test
	void testEquality_DifferentValue() {
		QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
		QuantityMeasurementApp.Feet f2 = new QuantityMeasurementApp.Feet(2.0);
		assertFalse(f1.equals(f2), "1.0 ft should not be equal to 2.0 ft");
	}

	@Test
	void testEquality_NullComparison() {
		QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
		assertFalse(f1.equals(null), "Value should not be equal to null");
	}

	@Test
	void testEquality_NonNumericInput() {
		QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
		String nonNumeric = "abc";
		assertFalse(f1.equals(nonNumeric), "Value should not be equal to non-numeric input");
	}

	@Test
	void testEquality_SameReference() {
		QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
		assertTrue(f1.equals(f1), "Object should be equal to itself");
	}
}
