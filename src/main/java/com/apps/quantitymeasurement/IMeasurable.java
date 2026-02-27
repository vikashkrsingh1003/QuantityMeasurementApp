package com.apps.quantitymeasurement;

//feature 11

public interface IMeasurable {

	double getConversionFactor();

	double convertToBaseUnit(double value);

	double convertFromBaseUnit(double baseValue);

	String getUnitName();
}