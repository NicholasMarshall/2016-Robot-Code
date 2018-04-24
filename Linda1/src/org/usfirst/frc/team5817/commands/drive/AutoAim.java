package org.usfirst.frc.team5817.commands.drive;

import org.usfirst.frc.team5817.commands.auto.AutoDelay;
import org.usfirst.frc.team5817.commands.shooter.AutoAimShooter;
import org.usfirst.frc.team5817.commands.shooter.FireShooter;
import org.usfirst.frc.team5817.commands.shooter.StopConveyor;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoAim extends CommandGroup {
    
    public AutoAim() {
    	addSequential(new ZeroGyro());
    	addParallel(new AutoAimShooter());
    	addSequential(new AutoAimBase());
    }
    
}