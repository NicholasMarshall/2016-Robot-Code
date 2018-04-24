package org.usfirst.frc.team5817.commands.intake;

import org.usfirst.frc.team5817.commands.shooter.ConveyorOut;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OuttakeBoth extends CommandGroup {

    public OuttakeBoth() {
    	addParallel(new Outtake());
    	addParallel(new ConveyorOut());
    	setInterruptible(true);
    	setRunWhenDisabled(false);
    }
    
}