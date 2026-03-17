package com.apps.quantitymeasurement.entityLayer;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityMeasurementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // First quantity
    private double thisValue;
    private String thisUnit;
    private String thisMeasurementType;

    // Second quantity
    private double thatValue;
    private String thatUnit;
    private String thatMeasurementType;

    // Operation (COMPARE, ADD, SUBTRACT, etc.)
    private String operation;

    // Result values
    private double resultValue;
    private String resultUnit;
    private String resultMeasurementType;

    // For comparison operations
    private String resultString;

    // Error handling
    private boolean isError;
    private String errorMessage;
}