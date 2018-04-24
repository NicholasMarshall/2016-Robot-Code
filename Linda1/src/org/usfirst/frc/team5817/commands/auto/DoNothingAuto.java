package org.usfirst.frc.team5817.commands.auto;

import org.usfirst.frc.team5817.commands.drive.StopDrive;
import org.usfirst.frc.team5817.commands.drive.ZeroGyro;
import org.usfirst.frc.team5817.commands.shooter.StopShooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DoNothingAuto extends CommandGroup {
    
    public DoNothingAuto() {
    	addParallel(new StopDrive());
    	addParallel(new StopShooter());
    	addSequential(new ZeroGyro());
    }

}