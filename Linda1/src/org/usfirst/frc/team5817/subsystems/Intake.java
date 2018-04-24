package org.usfirst.frc.team5817.subsystems;

import org.usfirst.frc.team5817.main.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
    
    private CANTalon horizontalIntake;
    private CANTalon verticalIntake;
    private DigitalInput bannerSensor;

    public Intake() {
    	horizontalIntake = new CANTalon(RobotMap.INTAKE_HORIZONTAL);
    	verticalIntake = new CANTalon(RobotMap.INTAKE_VERTICAL);
    	
    	horizontalIntake.setInverted(false);
    	verticalIntake.setInverted(false);
    	
    	horizontalIntake.changeControlMode(TalonControlMode.PercentVbus);
    	verticalIntake.changeControlMode(TalonControlMode.PercentVbus);
    	
    	horizontalIntake.enableControl();
    	verticalIntake.enableControl();
    	
    	bannerSensor = new DigitalInput(0);
    }
    
    public void setHorizontal(double output) {
    	horizontalIntake.set(output);
    }
    
    public void setVertical(double output) {
    	verticalIntake.set(output);
    }
    
    public void stopHorizontal() {
    	setHorizontal(0.0);
    }
    
    public void stopVertical() {
    	setVertical(0.0);
    }
    
    public void stop() {
    	stopHorizontal();
    	stopVertical();
    }
    
    public void initDefaultCommand() {
        
    }
    
    public boolean isBallLoaded() {
    	return bannerSensor.get();
    }

}