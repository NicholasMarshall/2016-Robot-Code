package org.usfirst.frc.team5817.commands.auto;

import org.usfirst.frc.team5817.commands.drive.OrientWheels;
import org.usfirst.frc.team5817.commands.drive.StopDrive;
import org.usfirst.frc.team5817.commands.drive.ZeroGyro;
import org.usfirst.frc.team5817.commands.shooter.FireShooter;
import org.usfirst.frc.team5817.commands.shooter.SetShooterAngleUnderVertical;
import org.usfirst.frc.team5817.commands.shooter.SpinShooter;
import org.usfirst.frc.team5817.commands.shooter.StopConveyor;
import org.usfirst.frc.team5817.commands.shooter.StopShooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SpyBoxShoot extends CommandGroup {
    
    public SpyBoxShoot() {
    	addParallel(new OrientWheels(0.0));
    	addSequential(new ZeroGyro());
    	addParallel(new SetShooterAngleUnderVertical(299));
    	addParallel(new SpinShooter());
    	addSequential(new AutoDelay(3.0));
    	addParallel(new FireShooter());
    	addSequential(new AutoDelay(2.0));
    	addParallel(new StopConveyor());
    	addParallel(new StopShooter());
        addSequential(new StopDrive());
    }
    
}