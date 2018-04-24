package org.usfirst.frc.team5817.util;

public final class RexMath {

	/**
	 * Creates a new instance of RexMath. Private so instances cannot be made.
	 */
	private RexMath() {}
	
	/**
	 * The double value closest to the value of e.
	 */
	public static final double E = 2.7182818284590452354;
	
	/**
	 * The double value closest to the value of pi.
	 */
	public static final double PI = 3.14159265358979323846;
	
	/**
	 * Computes the sine of an angle, measured in degrees.
	 *
	 * @param  a The angle (in degrees) to compute the sine of.
	 * @return The sine of the angle.
	 */
	public static double sin(double a) {
        return StrictMath.sin(toRadians(a));
    }
	
	/**
	 * Computes the cosine of an angle, measured in degrees.
	 *
	 * @param  a The angle (in degrees) to compute the cosine of.
	 * @return The cosine of the angle.
	 */
	public static double cos(double a) {
        return StrictMath.cos(toRadians(a));
    }
	
	/**
	 * Computes the tangent of an angle, measured in degrees.
	 *
	 * @param  a The angle (in degrees) to compute the tangent of.
	 * @return The tangent of the angle.
	 */
	public static double tan(double a) {
        return StrictMath.tan(toRadians(a));
    }
	
	/**
	 * Computes the arcsine of a decimal value, measured in degrees.
	 *
	 * @param  a The decimal value to compute the arcsine of.
	 * @return The arcsine of the decimal value in degrees.
	 */
	public static double asin(double a) {
        return RexMath.toDegrees(StrictMath.asin(a));
    }
	
	/**
	 * Computes the arccosine of a decimal value, measured in degrees.
	 *
	 * @param  a The decimal value to compute the arccosine of.
	 * @return The arccosine of the decimal value in degrees.
	 */
	public static double acos(double a) {
        return RexMath.toDegrees(StrictMath.acos(a));
    }
	
	/**
	 * Computes the arctangent of a value, measured in degrees.
	 *
	 * @param  a The value to compute the arctangent of.
	 * @return The arctangent of the value in degrees.
	 */
	public static double atan(double a) {
        return RexMath.toDegrees(StrictMath.atan(a));
    }
	
	/**
	 * Computes the arctangent of a vector while accommodating for the full unit circle
	 *
	 * @param  y The y value of the input vector
	 * @param  x The x value of the input vector
	 * @return The arctangent of the value in degrees.
	 */
	public static double atan2(double y, double x) {
        return RexMath.toDegrees(StrictMath.atan2(y, x));
    }
	
	/**
	 * Rounds the specified value up to the nearest whole number.
	 *
	 * @param  a The value to round up.
	 * @return The nearest greater whole number.
	 */
	public static double ceil(double a) {
        return StrictMath.ceil(a);
    }

	/**
	 * Rounds the specified value down to the nearest whole number.
	 *
	 * @param  a The value to round down.
	 * @return The nearest lesser whole number.
	 */
    public static double floor(double a) {
        return StrictMath.floor(a);
    }
    
    /**
	 * Converts a degree value to a radian value.
	 *
	 * @param  angdeg The degree value to convert to a radian value.
	 * @return The degree value in radians.
	 */
    public static double toRadians(double angdeg) {
        return (angdeg / 180.0) * PI;
    }

    /**
	 * Converts a radian value to a degree value.
	 *
	 * @param  angrad The radian value to convert to a degree value.
	 * @return The radian value in degrees.
	 */
    public static double toDegrees(double angrad) {
        return (angrad * 180.0) / PI;
    }

    /**
	 * Raises e to the power of a value.
	 *
	 * @param  a The value to raise e to the power of.
	 * @return e to the power of the parameter.
	 */
    public static double exp(double a) {
        return StrictMath.exp(a);
    }
    
    /**
	 * Computes the natural log of a value.
	 *
	 * @param  a The value to compute the natural log of.
	 * @return The natural log of the parameter.
	 */
    public static double ln(double a) {
        return StrictMath.log(a);
    }

    /**
	 * Computes the log base 10 of a value.
	 *
	 * @param  a The value to compute the log base 10 of.
	 * @return The log base 10 of the parameter.
	 */
    public static double log10(double a) {
        return StrictMath.log10(a);
    }

    /**
	 * Computes the square root of a value.
	 *
	 * @param  a The value to compute the square root of.
	 * @return The square root of the parameter.
	 */
    public static double sqrt(double a) {
        return StrictMath.sqrt(a);
    }

    /**
	 * Computes the cube root of a value.
	 *
	 * @param  a The value to compute the cube root of.
	 * @return The cube root of the parameter.
	 */
    public static double cbrt(double a) {
        return StrictMath.cbrt(a);
    }
    
    /**
	 * Generates a random decimal value between 0 and 1.
	 *
	 * @return A random decimal value between 0 and 1.
	 */
    public static double random() {
    	return Math.random();
    }
    /**
     * 
     * Adds two vectors given their magnitudes and directions. Note that angles should be input and output in compass degrees, 
     * not unit circle degrees.
     * 
     * @param magnitude1 The magnitude of the first vector.
     * @param angle1 The angle of the first vector
     * @param magnitude2 The magnitude of the second vector.
     * @param angle2 The angle of the second vector.
     * 
     * @return A double array containing the magnitude and angle of the sum of the two provided vectors.
     */
    public static double[] addPolarVectors(double magnitude1, double angle1, double magnitude2, double angle2) {
    	double unitDegrees1 = 450 - angle1;
    	double unitDegrees2 = 450 - angle2;
    	double x1 = magnitude1 * cos(unitDegrees1);
    	double x2 = magnitude2 * cos(unitDegrees2);
    	double y1 = magnitude1 * sin(unitDegrees1);
    	double y2 = magnitude2 * sin(unitDegrees2);
    	double totalX = x1 + x2;
    	double totalY = y1 + y2;
    	double[] output = new double[2];
    	output[0] = sqrt((totalX * totalX) + (totalY * totalY));
    	output[1] = 450 - atan2(totalY, totalX);
    	return output;
    }
    
}
