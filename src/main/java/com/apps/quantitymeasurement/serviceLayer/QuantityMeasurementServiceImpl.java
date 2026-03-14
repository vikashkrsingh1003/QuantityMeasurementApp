package com.apps.quantitymeasurement.serviceLayer;

import com.apps.quantitymeasurement.UnitLayer.IMeasurable;
import com.apps.quantitymeasurement.UnitLayer.LengthUnit;
import com.apps.quantitymeasurement.UnitLayer.Quantity;
import com.apps.quantitymeasurement.UnitLayer.TemperatureUnit;
import com.apps.quantitymeasurement.UnitLayer.VolumeUnit;
import com.apps.quantitymeasurement.UnitLayer.WeightUnit;
import com.apps.quantitymeasurement.entityLayer.QuantityDTO;

import com.apps.quantitymeasurement.entityLayer.QuantityModel;
import com.apps.quantitymeasurement.exceptionLayer.QuantityMeasurementException;
import com.apps.quantitymeasurement.repositoryLayer.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.repositoryLayer.QuantityMeasurementCacheRepository;

 public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    // Convert DTO → Core Quantity
    private Quantity<IMeasurable> toQuantity(QuantityDTO dto) {

        IMeasurable unit = getCoreUnit(dto.unit);

        return new Quantity<>(dto.value, unit);
    }

    // Convert DTO unit → Core unit
    private IMeasurable getCoreUnit(QuantityDTO.IMeasurableUnit dtoUnit) {

        String name = dtoUnit.getUnitName();

        for (LengthUnit u : LengthUnit.values())
            if (u.getUnitName().equalsIgnoreCase(name))
                return u;

        for (WeightUnit u : WeightUnit.values())
            if (u.getUnitName().equalsIgnoreCase(name))
                return u;

        for (VolumeUnit u : VolumeUnit.values())
            if (u.getUnitName().equalsIgnoreCase(name))
                return u;

        for (TemperatureUnit u : TemperatureUnit.values())
            if (u.getUnitName().equalsIgnoreCase(name))
                return u;

        throw new QuantityMeasurementException("Invalid unit: " + name);
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        Quantity<IMeasurable> quantity1 = toQuantity(q1);
        Quantity<IMeasurable> quantity2 = toQuantity(q2);

        return quantity1.equals(quantity2);
    }

    @Override
    public QuantityDTO convert(QuantityDTO quantityDTO, QuantityDTO.IMeasurableUnit targetUnit) {

        Quantity<IMeasurable> quantity = toQuantity(quantityDTO);
        IMeasurable coreTargetUnit = getCoreUnit(targetUnit);

        Quantity<IMeasurable> result = quantity.convertTo(coreTargetUnit);

        return new QuantityDTO(result.getValue(), targetUnit);
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

        Quantity<IMeasurable> quantity1 = toQuantity(q1);
        Quantity<IMeasurable> quantity2 = toQuantity(q2);

        Quantity<IMeasurable> result = quantity1.add(quantity2);

        return new QuantityDTO(result.getValue(), q1.unit);
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {

        Quantity<IMeasurable> quantity1 = toQuantity(q1);
        Quantity<IMeasurable> quantity2 = toQuantity(q2);

        Quantity<IMeasurable> result = quantity1.subtract(quantity2);

        return new QuantityDTO(result.getValue(), q1.unit);
    }

    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {

        Quantity<IMeasurable> quantity1 = toQuantity(q1);
        Quantity<IMeasurable> quantity2 = toQuantity(q2);

        return quantity1.divide(quantity2);
    }
}