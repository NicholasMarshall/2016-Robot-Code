package org.usfirst.frc.team5817.subsystems;

import org.usfirst.frc.team5817.commands.drive.TeleopDrive;
import org.usfirst.frc.team5817.main.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SwerveWheel extends Subsystem  {
	
	/**
	 * The TalonSRX that controls the drive motor for this wheel.
	 */
	public CANTalon driveTalon;
	
	/**
	 * The TalonSRX that controls the steer motor for this wheel.
	 */
	private CANTalon steerTalon;
	
	/**
	 * The current rotation setpoint of the wheel in compass degrees.
	 */
	public double curSetpoint = 0.0;
	
	/**
	 * Creates a new instance of SwerveWheel. Takes port numbers of drive and steer talons, as well as whether 
	 * or not the drive or steer talons are inverted, and whether or not the drive or steer encoders are inverted.
	 * 
	 * @param drivePort Port of drive talon.
	 * @param steerPort Port of steer talon.
	 * @param isDriveInverted Whether or not to invert the drive output.
	 * @param isSteerInverted Whether or not to invert the steer output.
	 * @param isDriveEncInverted Whether or not to invert the drive encoder.
	 * @param isSteerEncInverted Whether or not to invert the steer encoder.
	 */
	public SwerveWheel(int drivePort, int steerPort, boolean isDriveInverted, boolean isSteerInverted, boolean isDriveEncInverted, boolean isSteerEncInverted) {
		driveTalon = new CANTalon(drivePort);
		steerTalon = new CANTalon(steerPort);
		
		driveTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		steerTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		
		driveTalon.reverseOutput(isDriveInverted);
		steerTalon.reverseOutput(isSteerInverted);
		
		driveTalon.reverseSensor(isDriveEncInverted);
		steerTalon.reverseSensor(isSteerEncInverted);
		
		driveTalon.changeControlMode(TalonControlMode.Speed);
		steerTalon.changeControlMode(TalonControlMode.Position);
		
		driveTalon.configEncoderCodesPerRev(RobotMap.DRIVE_ENCODER_COUNTS_PER_REV);
		steerTalon.configEncoderCodesPerRev(RobotMap.STEER_ENCODER_COUNTS_PER_REV);
		
		driveTalon.enableControl();
		steerTalon.enableControl();
	}

	/**
	 * Initializes the default command for this wheel.
	 */
	protected void initDefaultCommand() {
		setDefaultCommand(new TeleopDrive());
	}
	
	/**
	 * Sets the steer setpoint to be at a given encoder position.
	 * 
	 * @param pNewCounts The new setpoint in revolutions.
	 */
    public void setSteerCounts(double pNewCounts) {
    	steerTalon.set(pNewCounts);
    }
    
    /** Returns the current steer position in revolutions.
     * 
     * @return The current steer position in revolutions.
     */
    public double getSteerCounts() {
    	return steerTalon.getPosition();
    }
    
    /** Returns the current steer position in compass degrees.
     * 
     * @return The current steer position in compass degrees.
     */
    public double getSteerDegrees() {
    	double curCounts = getSteerCounts();
    	while(curCounts >= 1.0) {
    		curCounts -= 1.0;
    	}
    	while(curCounts < 0.0) {
    		curCounts += 1.0;
    	}
    	return (curCounts) * 360.0;
    }
    
    /**
	 * Sets the steer setpoint to be at a given degree position.
	 * 
	 * @param pNewDegrees The new setpoint in degrees.
	 */
    public void setSteerDegrees(double pNewDegrees) {
    	while(pNewDegrees > 360.0) {
    		pNewDegrees -= 360.0;
    	}
    	while(pNewDegrees < 0.0) {
    		pNewDegrees += 360.0;
    	}
    	double clockDelta = pNewDegrees - getSteerDegrees();
    	while(clockDelta < 0.0) {
    		clockDelta += 360.0;
    	}
    	double counterClockDelta = getSteerDegrees() - pNewDegrees;
    	while(counterClockDelta < 0.0) {
    		counterClockDelta += 360.0;
    	}
    	counterClockDelta *= -1.0;

    	double smallestDelta = (Math.abs(clockDelta) < Math.abs(counterClockDelta)) ? clockDelta : counterClockDelta;
    	double countsDelta = (smallestDelta / 360.0);
    	setSteerCounts(getSteerCounts() + countsDelta);
    	curSetpoint = ((getSteerCounts() + countsDelta)) * 360.0;
    }
    
    /** Returns whether or not the drive rotation is within 5 degrees of the current setpoint. Useful for keeping the robot from spasming while stopping and changing direction.
     * 
     * @return Whether or not the drive rotation is within 5 degrees of the current setpoint.
     */
    public boolean isDriveInRange() {
    	double localSetpoint = curSetpoint;
    	while(localSetpoint < 0.0) {
    		localSetpoint += 360.0;
    	}
    	while(localSetpoint >= 360.0) {
    		localSetpoint -= 360.0;
    	}
    	return Math.abs(localSetpoint - getSteerDegrees()) < 5.0 || Math.abs(localSetpoint - getSteerDegrees()) > 5.0;
    }
	
    /**
	 * Zeroes the steer motor.
	 */
	public void zeroSteer() {
		steerTalon.setPosition(0.0);
		steerTalon.set(0.0);
	}
	
	/**
	 * Zeroes the drive motor.
	 */
	public void zeroDrive() {
		driveTalon.setPosition(0.0);
	}

	/**
	 * Sets the drive motor to drive at a given speed in feet per second.
	 * 
	 * @param fps The speed of the drive in feet per second.
	 */
	public void setDrive(double fps) {
		if(isDriveInRange()) {
			driveTalon.set(fps * 1.0);
		} else {
			driveTalon.set(0.0);
		}
	}

	/** Returns the unsigned drive speed of the drive motor in feet per second.
     * 
     * @return The unsigned drive speed of the drive motor in feet per second.
     */
	public double getUnsignedDriveSpeed() {
		return Math.abs(driveTalon.getSpeed() / RobotMap.DRIVE_WHEEL_FPS_TO_RPM_CONVERSION_FACTOR);
	}

	/** Returns the signed drive speed of the drive motor in feet per second.
     * 
     * @return The signed drive speed of the drive motor in feet per second.
     */
	public double getSignedDriveSpeed() {
		return driveTalon.getSpeed(); //RobotMap.DRIVE_WHEEL_FPS_TO_RPM_CONVERSION_FACTOR;
	}
	
	/**
	 * Changes the steer motor to PercentVbus mode.
	 */
	public void setPercentSteerMode() {
		steerTalon.changeControlMode(TalonControlMode.PercentVbus);
		steerTalon.enableControl();
	}
	
	/**
	 * Changes the steer motor to position mode.
	 */
	public void setPositionSteerMode() {
		steerTalon.changeControlMode(TalonControlMode.Position);
		steerTalon.enableControl();
	}
	
	/**
	 * Changes the drive motor to percent mode.
	 */
	public void setPercentDriveMode() {
		driveTalon.changeControlMode(TalonControlMode.PercentVbus);
		driveTalon.enableControl();
	}
	
	/**
	 * Changes the drive motor to speed mode.
	 */
	public void setSpeedDriveMode() {
		driveTalon.changeControlMode(TalonControlMode.Speed);
		driveTalon.enableControl();
	}
	
	public void setPID(double p, double i, double d, double p2, double i2, double d2) {
		driveTalon.setPID(p, i, d);
		steerTalon.setPID(p2, i2, d2);
		
		driveTalon.enableControl();
		steerTalon.enableControl();
	}
	
	public void stop() {
		driveTalon.set(0.0);
		driveTalon.clearIAccum();
	}
	
}