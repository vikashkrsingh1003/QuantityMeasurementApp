package com.apps.quantitymeasurement.repositoryLayer;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.apps.quantitymeasurement.entityLayer.QuantityMeasurementEntity;


public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {

    private final List<QuantityMeasurementEntity> cache = new ArrayList<>();

    @Override
    public void save(QuantityMeasurementEntity entity) {
        cache.add(entity);
    }

    @Override
    public List<QuantityMeasurementEntity> findAll() {
        return cache;
    }
}