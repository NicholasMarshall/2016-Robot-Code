package org.usfirst.frc.team5817.main;

public class RobotMap {

	public final static int FRONT_LEFT_STEER = 10;
	public final static int FRONT_RIGHT_STEER = 2;
	public final static int REAR_LEFT_STEER = 3;
	public static final int REAR_RIGHT_STEER = 4;
	
	public final static int FRONT_LEFT_DRIVE = 5;
	public final static int FRONT_RIGHT_DRIVE = 6;
	public final static int REAR_LEFT_DRIVE = 7;
	public static final int REAR_RIGHT_DRIVE = 8;
	
	public static final int INTAKE_ROTATION = 9;
	public static final int INTAKE_HORIZONTAL = 1;
	public static final int INTAKE_VERTICAL = 11;
	
	public static final int SHOOTER_ROTATION = 12;
	public static final int SHOOTER_LEFT = 13;
	public static final int SHOOTER_RIGHT = 14;
	
	public static final int CONVEYOR_UPPER = 15;
	public static final int CONVEYOR_LOWER = 16;
	
	public static final int DRIVE_ENCODER_COUNTS_PER_REV = 2513;
	public static final int STEER_ENCODER_COUNTS_PER_REV = 1638;
	public static final int INTAKE_ENCODER_COUNTS_PER_REV = 22118;
	public static final int SHOOTER_ENCODER_COUNTS_PER_REV = 360;
	public static final int SHOOTER_ROTATION_ENCODER_COUNTS_PER_REV = 4320;
	
	public static final double DRIVE_WHEEL_CIRCUMFERENCE = 10.2101761125;
	public static final double DRIVE_WHEEL_FPS_TO_RPM_CONVERSION_FACTOR = 70.517882558218214083719116652015;
	public static final double DRIVE_BASE_RADIUS_FEET = 1.190429383333333333333333;
	
	public static final double CAMERA_PIXEL_WIDTH = 320.0;
	public static final double CAMERA_FIELD_OF_VIEW_DEGREES = 67.0;
	
	public static final double SHOOTER_FLICK_POSITION = 250.0;
	public static final double SHOOTER_STORAGE_POSITION = 172.0;
	
	public static final double INTAKE_INTAKE_POSITION = 0.064;
	public static final double INTAKE_STORAGE_POSITION = 0.314;
	public static final double INTAKE_LOW_POSITION = 0.0;
}