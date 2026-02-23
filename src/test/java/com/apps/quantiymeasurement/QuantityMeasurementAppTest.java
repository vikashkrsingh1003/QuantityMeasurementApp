package com.apps.quantiymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.Length;
import com.apps.quantitymeasurement.Length.LengthUnit;
import com.apps.quantitymeasurement.QuantityMeasurementApp.Feet;
import com.apps.quantitymeasurement.QuantityMeasurementApp.Inches;

public class QuantityMeasurementAppTest {

	//------------------Test Methods for Feet Class-----------------
    public void testFeetEquality_SameValue() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        assertTrue(f1.equals(f2));
    }

    @Test
    public void testFeetEquality_DifferentValue() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(2.0);

        assertFalse(f1.equals(f2));
    }

    @Test
    public void testFeetEquality_NullComparison() {
        Feet f1 = new Feet(1.0);

        assertFalse(f1.equals(null));
    }

    @Test
    public void testFeetEquality_DifferentClass() {
        Feet f1 = new Feet(1.0);
        Object obj = "Not Feet";

        assertFalse(f1.equals(obj));
    }

    @Test
    public void testFeetEquality_SameReference() {
        Feet f1 = new Feet(1.0);

        assertTrue(f1.equals(f1));
    }
    
    //---------------Test Methods for Inches Class----------------------
    
    @Test 
    public void  testInchesEquality_SameValue() {
    	Inches i1 = new Inches(1.0);
    	Inches i2 = new Inches(1.0);
    	assertTrue(i1.equals(i2));
    }
    @Test
    public void testInchesEquality_DifferentValue() {
       Inches i1 = new Inches(1.0);
       Inches i2 = new Inches(2.0);

        assertFalse(i1.equals(i2));
    }

    @Test
    public void testInchesEquality_NullComparison() {
        Inches i1 = new Inches(1.0);

        assertFalse(i1.equals(null));
    }

    @Test
    public void testInchesEquality_DifferentClass() {
       Inches i1 = new Inches(1.0);
        Object obj = "Not inches";

        assertFalse(i1.equals(obj));
    }

    @Test
    public void testInchesEquality_SameReference() {
        Inches i1 = new Inches(1.0);

        assertTrue(i1.equals(i1));
    }
    
    
    //uc3 test
	
    @Test
	public void testQuantity_SameValueSameUnit() {
		Length q1 = new Length(1.0, LengthUnit.FEET);
		Length q2 = new Length(1.0, LengthUnit.FEET);
		assertTrue(q1.equals(q2));
	}

	@Test
	public void testQuantity_DifferentValue() {
		Length q1 = new Length(1.0, LengthUnit.FEET);
		Length q2 = new Length(2.0, LengthUnit.FEET);
		assertFalse(q1.equals(q2));
	}

	@Test
	public void testQuantity_NullComparison() {
		Length q1 = new Length(1.0, LengthUnit.FEET);
		assertFalse(q1.equals(null));
	}

	@Test
	public void testQuantity_DifferentClass() {
		Length q1 = new Length(1.0, LengthUnit.FEET);
		String other = "NotQuantity";
		assertFalse(q1.equals(other));
	}

	@Test
	public void testQuantity_SameReference() {
		Length q1 = new Length(1.0, LengthUnit.FEET);
		assertTrue(q1.equals(q1));
	}
    
	
	//uc4
	

    @Test
    void testEquality_YardToYard_SameValue() {
        Length y1 = new Length(1.0, LengthUnit.YARDS);
        Length y2 = new Length(1.0, LengthUnit.YARDS);
        assertTrue(y1.equals(y2));
    }

    @Test
    void testEquality_YardToYard_DifferentValue() {
        Length y1 = new Length(1.0, LengthUnit.YARDS);
        Length y2 = new Length(2.0, LengthUnit.YARDS);
        assertFalse(y1.equals(y2));
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        Length yards = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);
        assertTrue(yards.equals(feet));
    }

    @Test
    void testEquality_FeetToYard_EquivalentValue() {
        Length feet = new Length(3.0, LengthUnit.FEET);
        Length yards = new Length(1.0, LengthUnit.YARDS);
        assertTrue(feet.equals(yards)); // symmetry
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {
        Length yards = new Length(1.0, LengthUnit.YARDS);
        Length inches = new Length(36.0, LengthUnit.INCHES);
        assertTrue(yards.equals(inches));
    }

    @Test
    void testEquality_InchesToYard_EquivalentValue() {
        Length inches = new Length(36.0, LengthUnit.INCHES);
        Length yards = new Length(1.0, LengthUnit.YARDS);
        assertTrue(inches.equals(yards)); // symmetry
    }

    @Test
    void testEquality_YardToFeet_NonEquivalentValue() {
        Length yards = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(2.0, LengthUnit.FEET);
        assertFalse(yards.equals(feet));
    }

    @Test
    void testEquality_centimetersToInches_EquivalentValue() {
        Length cm = new Length(1.0, LengthUnit.CENTIMETERS);
        Length inches = new Length(0.393701, LengthUnit.INCHES);
        assertTrue(cm.equals(inches));
        assertTrue(inches.equals(cm)); // symmetry
    }

    @Test
    void testEquality_centimetersToFeet_NonEquivalentValue() {
        Length cm = new Length(1.0, LengthUnit.CENTIMETERS);
        Length feet = new Length(1.0, LengthUnit.FEET);
        assertFalse(cm.equals(feet));
    }

    @Test
    void testEquality_MultiUnit_TransitiveProperty() {
        Length yards = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);
        Length inches = new Length(36.0, LengthUnit.INCHES);

        assertTrue(yards.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yards.equals(inches)); // transitive
    }

    @Test
    void testEquality_YardWithNullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(1.0, null);
        });
    }

    @Test
    void testEquality_YardSameReference() {
        Length yards = new Length(2.0, LengthUnit.YARDS);
        assertTrue(yards.equals(yards)); // reflexive
    }

    @Test
    void testEquality_YardNullComparison() {
        Length yards = new Length(1.0, LengthUnit.YARDS);
        assertFalse(yards.equals(null));
    }

    @Test
    void testEquality_CentimetersWithNullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(1.0, null);
        });
    }

    @Test
    void testEquality_CentimetersSameReference() {
        Length cm = new Length(2.0, LengthUnit.CENTIMETERS);
        assertTrue(cm.equals(cm)); // reflexive
    }

    @Test
    void testEquality_CentimetersNullComparison() {
        Length cm = new Length(1.0, LengthUnit.CENTIMETERS);
        assertFalse(cm.equals(null));
    }

    @Test
    void testEquality_AllUnits_ComplexScenario() {
        Length yards = new Length(2.0, LengthUnit.YARDS);
        Length feet = new Length(6.0, LengthUnit.FEET);
        Length inches = new Length(72.0, LengthUnit.INCHES);

        assertTrue(yards.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yards.equals(inches)); // transitive across all units
    }

    
}