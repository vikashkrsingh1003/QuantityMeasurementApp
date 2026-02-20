package com.apps.quantitymeasurement;

import java.util.Scanner;

public class QuantityMeasurementApp {

    // Inner class to represent Feet measurement
    public static class Feet {

        private final double value;

        // Constructor
        public Feet(double value) {
            this.value = value;
        }

        // Override equals() to compare Feet objects
        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            Feet other = (Feet) obj;

            return Double.compare(this.value, other.value) == 0;
        }
    }

    // Main method with user input
    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {

            System.out.print("Enter first value in feet: ");
            double inputOne = sc.nextDouble();

            System.out.print("Enter second value in feet: ");
            double inputTwo = sc.nextDouble();

            Feet f1 = new Feet(inputOne);
            Feet f2 = new Feet(inputTwo);

            System.out.println("Equal (" + f1.equals(f2) + ")");
        }
    }
}