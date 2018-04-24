package org.usfirst.frc.team5817.subsystems;

import org.usfirst.frc.team5817.main.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Conveyor extends Subsystem {
    
    private CANTalon lowerConveyor;

    public Conveyor() {
    	lowerConveyor = new CANTalon(RobotMap.CONVEYOR_LOWER);
    	lowerConveyor.setInverted(false);
    	lowerConveyor.changeControlMode(TalonControlMode.PercentVbus);
    	lowerConveyor.enableControl();
    }
    
    public void set(double output) {
    	lowerConveyor.set(output);
    }
    
    public void stop() {
    	set(0.0);
    }
    
    public void initDefaultCommand() {
        
    }

}