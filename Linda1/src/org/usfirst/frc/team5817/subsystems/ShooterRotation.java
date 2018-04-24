package org.usfirst.frc.team5817.subsystems;

import org.usfirst.frc.team5817.commands.shooter.TeleopShooterRotation;
import org.usfirst.frc.team5817.main.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterRotation extends Subsystem {
    
	private CANTalon shooterRotation;

    public ShooterRotation() {
    	shooterRotation = new CANTalon(RobotMap.SHOOTER_ROTATION);
    	shooterRotation.setFeedbackDevice(FeedbackDevice.AnalogPot);
    	shooterRotation.reverseOutput(true);
    	shooterRotation.changeControlMode(TalonControlMode.Position);
    	shooterRotation.configMaxOutputVoltage(4.0);
    	shooterRotation.setPID(40, 0.0, 1000);
    	shooterRotation.enableControl();
    }
    
    public void setRaw(double output) {
    	shooterRotation.set(output);
    }
    
    public void setDegrees(double degrees) {
    	while(degrees > 360.0) {
    		degrees -= 360.0;
    	}
    	while(degrees < 0.0) {
    		degrees += 360.0;
    	}
    	setRaw(degrees / 360.0);
    }
    
    public double getRaw() {
    	return shooterRotation.getPosition();
    }
    
    public double getDegrees() {
    	double pos = shooterRotation.getPosition();
    	while(pos > 1.0) {
    		pos -= 1.0;
    	}
    	while(pos < 0.0) {
    		pos += 1.0;
    	}
    	return pos * 360.0;
    }

    public void initDefaultCommand() {
        setDefaultCommand(new TeleopShooterRotation());
    }
    
    public void zeroShooter() {
    	shooterRotation.setPosition(0.0);
    }
    
    public void setPercentMode() {
		shooterRotation.changeControlMode(TalonControlMode.PercentVbus);
		shooterRotation.enableControl();
	}
	
	public void setUnderVerticalPositionMode() {
		shooterRotation.changeControlMode(TalonControlMode.Position);
		shooterRotation.setPID(40, 0.0, 1000);
		shooterRotation.enableControl();
	}
	
	public void setAboveVerticalPositionMode() {
		shooterRotation.changeControlMode(TalonControlMode.Position);
		shooterRotation.setPID(40, 0.0, 1000);
		shooterRotation.enableControl();
	}
	
}