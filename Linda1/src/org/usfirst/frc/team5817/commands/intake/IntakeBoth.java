package org.usfirst.frc.team5817.commands.intake;

import org.usfirst.frc.team5817.commands.shooter.ConveyorIn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeBoth extends CommandGroup {

    public IntakeBoth() {
    	addParallel(new Intake());
    	addParallel(new ConveyorIn());
    	setInterruptible(true);
    	setRunWhenDisabled(false);
    }
    
}