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
    
    
}