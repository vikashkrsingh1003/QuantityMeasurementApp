package com.apps.quantitymeasurement.controllerLayer;

import com.apps.quantitymeasurement.entityLayer.QuantityDTO;
import com.apps.quantitymeasurement.serviceLayer.IQuantityMeasurementService;

public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    public boolean compare(QuantityDTO q1, QuantityDTO q2) {
        return service.compare(q1, q2);
    }

    public QuantityDTO convert(QuantityDTO quantity,
                               QuantityDTO.IMeasurableUnit targetUnit) {

        return service.convert(quantity, targetUnit);
    }

    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
        return service.add(q1, q2);
    }

    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {
        return service.subtract(q1, q2);
    }

    public double divide(QuantityDTO q1, QuantityDTO q2) {
        return service.divide(q1, q2);
    }
}