package org.usfirst.frc.team5817.subsystems;

import org.usfirst.frc.team5817.main.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeRotation extends Subsystem {
    
    private CANTalon intakeRotation;

    public IntakeRotation() {
    	intakeRotation = new CANTalon(RobotMap.INTAKE_ROTATION);
    	intakeRotation.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	intakeRotation.reverseOutput(true);
    	intakeRotation.reverseSensor(true);
    	intakeRotation.changeControlMode(TalonControlMode.PercentVbus);
    	intakeRotation.configEncoderCodesPerRev(RobotMap.INTAKE_ENCODER_COUNTS_PER_REV);
    	intakeRotation.setPID(0.1, 0.000002, 0.75);
    	intakeRotation.enableControl();
    }
    
    public void set(double output) {
    	intakeRotation.set(output);
    }
    
    public double get()	{
    	return intakeRotation.getPosition();
    }
    
    public void zeroIntake() {
    	intakeRotation.setPosition(RobotMap.INTAKE_INTAKE_POSITION);
    }
    
    public void setPercentMode() {
    	intakeRotation.changeControlMode(TalonControlMode.PercentVbus);
    	intakeRotation.enableControl();
    }
    
    public void setPositionMode() {
    	intakeRotation.changeControlMode(TalonControlMode.Position);
    	intakeRotation.enableControl();
    }
    
    public void initDefaultCommand() {
    	
    }

}