package com.apps.quantitymeasurement;

import java.util.Scanner;

public class QuantityMeasurementApp {

    //--------------FEET CLASS--------------
    public static class Feet {

        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;

            Feet other = (Feet) obj;

            return Double.compare(this.value, other.value) == 0;
        }
    }

    //--------------- INCHES CLASS -------------------
    public static class Inches {

        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;

            Inches other = (Inches) obj;

            return Double.compare(this.value, other.value) == 0;
        }
    }

    // -------------------- DEMO METHODS----------------------
    public static void demonstrateFeetEquality(Scanner sc) {

        System.out.print("Enter first value in feet: ");
        double v1 = sc.nextDouble();

        System.out.print("Enter second value in feet: ");
        double v2 = sc.nextDouble();

        Feet f1 = new Feet(v1);
        Feet f2 = new Feet(v2);

        System.out.println("Feet Equal: " + f1.equals(f2));
    }

    public static void demonstrateInchesEquality(Scanner sc) {

        System.out.print("Enter first value in inches: ");
        double v1 = sc.nextDouble();

        System.out.print("Enter second value in inches: ");
        double v2 = sc.nextDouble();

        Inches i1 = new Inches(v1);
        Inches i2 = new Inches(v2);

        System.out.println("Inches Equal: " + i1.equals(i2));
    }

    // ---------------- MAIN METHOD ------------------
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            demonstrateFeetEquality(sc);
            demonstrateInchesEquality(sc);
        } catch (Exception e) {
            System.out.println("Invalid input! Please enter numeric values.");
        }

        sc.close();
    }
}