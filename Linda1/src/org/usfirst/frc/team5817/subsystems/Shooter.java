package org.usfirst.frc.team5817.subsystems;

import org.usfirst.frc.team5817.commands.shooter.TeleopShooter;
import org.usfirst.frc.team5817.main.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
    
    private CANTalon leftShooter;
    private CANTalon rightShooter;

    public Shooter() {
    	leftShooter = new CANTalon(RobotMap.SHOOTER_LEFT);
    	rightShooter = new CANTalon(RobotMap.SHOOTER_RIGHT);
    	
    	leftShooter.reverseOutput(false);
    	rightShooter.reverseOutput(false);
    	
    	leftShooter.changeControlMode(TalonControlMode.PercentVbus);
    	rightShooter.changeControlMode(TalonControlMode.PercentVbus);
    	
    	leftShooter.configEncoderCodesPerRev(RobotMap.SHOOTER_ENCODER_COUNTS_PER_REV);
    	rightShooter.configEncoderCodesPerRev(RobotMap.SHOOTER_ENCODER_COUNTS_PER_REV);
    	
    	leftShooter.enableControl();
    	rightShooter.enableControl();
    }
    
    public void setLeft(double output) {
    	leftShooter.set(output);
    }
    
    public void setRight(double output) {
    	rightShooter.set(output);
    }
    
    public void stopLeft() {
    	setLeft(0.0);
    }
    
    public void stopRight() {
    	setRight(0.0);
    }
    
    public double getLeft() {
    	return leftShooter.get();
    }
    
    public double getRight() {
    	return rightShooter.get();
    }
    
    public void stop() {
    	stopLeft();
    	stopRight();
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new TeleopShooter());
    }
	
}