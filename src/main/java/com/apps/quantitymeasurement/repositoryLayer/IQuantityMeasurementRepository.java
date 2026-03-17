package com.apps.quantitymeasurement.repositoryLayer;

import java.util.List;
import com.apps.quantitymeasurement.entityLayer.QuantityMeasurementEntity;

public interface IQuantityMeasurementRepository {

    void save(QuantityMeasurementEntity entity);

    List<QuantityMeasurementEntity> findAll();
}