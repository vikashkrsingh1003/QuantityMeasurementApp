package com.apps.quantiymeasurement;


import com.apps.quantitymeasurement.UnitLayer.LengthUnit;
import com.apps.quantitymeasurement.UnitLayer.Quantity;
import com.apps.quantitymeasurement.UnitLayer.TemperatureUnit;
import com.apps.quantitymeasurement.UnitLayer.VolumeUnit;
import com.apps.quantitymeasurement.UnitLayer.WeightUnit;


import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.0001;
    
    
    
    
    // ==========================
    // LENGTH TESTS
    // ==========================

    @Test
    public void testLengthEquality_FeetToInches() {
        assertTrue(new Quantity<>(1.0, LengthUnit.FEET)
                .equals(new Quantity<>(12.0, LengthUnit.INCHES)));
    }

    @Test
    public void testLengthEquality_FeetToFeet() {
        assertTrue(new Quantity<>(5.0, LengthUnit.FEET)
                .equals(new Quantity<>(5.0, LengthUnit.FEET)));
    }

    @Test
    public void testLengthConversion_FeetToInch() {
        Quantity<LengthUnit> result =
                new Quantity<>(1.0, LengthUnit.FEET)
                        .convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), EPSILON);
    }

    @Test
    public void testLengthAddition() {
        Quantity<LengthUnit> result =
                new Quantity<>(1.0, LengthUnit.FEET)
                        .add(new Quantity<>(12.0, LengthUnit.INCHES));

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    // ==========================
    // WEIGHT TESTS
    // ==========================

    @Test
    public void testWeightEquality_KgToGram() {
        assertTrue(new Quantity<>(1.0, WeightUnit.KILOGRAM)
                .equals(new Quantity<>(1000.0, WeightUnit.GRAM)));
    }

    @Test
    public void testWeightConversion_KgToGram() {
        Quantity<WeightUnit> result =
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    public void testWeightAddition() {
        Quantity<WeightUnit> result =
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
                        .add(new Quantity<>(1000.0, WeightUnit.GRAM));

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    // ==========================
    // VOLUME — Equality
    // ==========================

    @Test
    public void testEquality_LitreToLitre_SameValue() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(1.0, VolumeUnit.LITRE)));
    }

    @Test
    public void testEquality_LitreToMillilitre_EquivalentValue() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    public void testEquality_MillilitreToLitre_EquivalentValue() {
        assertTrue(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                .equals(new Quantity<>(1.0, VolumeUnit.LITRE)));
    }

    @Test
    public void testEquality_VolumeVsLength_Incompatible() {
        assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(1.0, LengthUnit.FEET)));
    }

    @Test
    public void testEquality_NullComparison() {
        assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE).equals(null));
    }

    // ==========================
    // ADDITION
    // ==========================

    @Test
    public void testAddition_CrossUnit_LitrePlusMillilitre() {
        assertEquals(2.0,
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)).getValue(),
                EPSILON);
    }

    @Test
    public void testAddition_Commutativity() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE)
                .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE));

        Quantity<VolumeUnit> b = new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                .add(new Quantity<>(1.0, VolumeUnit.LITRE));

        assertTrue(a.equals(b));
    }

    // ==========================
    // SUBTRACTION
    // ==========================

    @Test
    public void testSubtraction_SameUnit_FeetMinusFeet() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(5.0, LengthUnit.FEET));

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_CrossUnit_FeetMinusInches() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(6.0, LengthUnit.INCHES));

        assertEquals(9.5, result.getValue(), EPSILON);
    }

    // ==========================
    // DIVISION
    // ==========================

    @Test
    public void testDivision_SameUnit_FeetDividedByFeet() {
        double result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(2.0, LengthUnit.FEET));

        assertEquals(5.0, result, EPSILON);
    }

    @Test
    public void testDivision_CrossUnit_KilogramDividedByGram() {
        double result =
                new Quantity<>(2.0, WeightUnit.KILOGRAM)
                        .divide(new Quantity<>(2000.0, WeightUnit.GRAM));

        assertEquals(1.0, result, EPSILON);
    }

    // ==========================
    // TEMPERATURE TESTS
    // ==========================

    @Test
    public void testTemperatureEquality() {
        Quantity<TemperatureUnit> c =
                new Quantity<>(0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> f =
                new Quantity<>(32, TemperatureUnit.FAHRENHEIT);

        assertEquals(c, f);
    }

    @Test
    public void testTemperatureConversion() {
        Quantity<TemperatureUnit> result =
                new Quantity<>(100, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(212.0, result.getValue(), EPSILON);
    }

    // ==========================
    // EDGE CASES
    // ==========================

    @Test
    public void testZeroValuesAcrossUnits() {
        Quantity<VolumeUnit> v1 = new Quantity<>(0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(0, VolumeUnit.MILLILITRE);

        assertTrue(v1.equals(v2));
    }

    @Test
    public void testNegativeValues() {
        Quantity<LengthUnit> a = new Quantity<>(-1, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(-12, LengthUnit.INCHES);

        assertTrue(a.equals(b));
    } 

}
