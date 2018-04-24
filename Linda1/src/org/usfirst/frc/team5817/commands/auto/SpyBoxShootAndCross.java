package org.usfirst.frc.team5817.commands.auto;

import org.usfirst.frc.team5817.commands.drive.FollowSpeedProfile;
import org.usfirst.frc.team5817.commands.drive.OrientWheels;
import org.usfirst.frc.team5817.commands.drive.OrientWheelsForTurn;
import org.usfirst.frc.team5817.commands.drive.ZeroGyro;
import org.usfirst.frc.team5817.commands.intake.IntakeGoToPosition;
import org.usfirst.frc.team5817.commands.shooter.FireShooter;
import org.usfirst.frc.team5817.commands.shooter.SetShooterAngleUnderVertical;
import org.usfirst.frc.team5817.commands.shooter.SpinShooter;
import org.usfirst.frc.team5817.commands.shooter.StopConveyor;
import org.usfirst.frc.team5817.commands.shooter.StopShooter;
import org.usfirst.frc.team5817.main.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SpyBoxShootAndCross extends CommandGroup {
    
    public SpyBoxShootAndCross() {
    	addSequential(new ZeroGyro());
    	addParallel(new SetShooterAngleUnderVertical(319));
    	addParallel(new SpinShooter());
    	addSequential(new AutoDelay(3.0));
    	addParallel(new FireShooter());
    	addSequential(new AutoDelay(2.0));
    	addParallel(new StopConveyor());
    	addParallel(new StopShooter());
    	addParallel(new SetShooterAngleUnderVertical(RobotMap.SHOOTER_STORAGE_POSITION));
        addSequential(new FollowSpeedProfile(0.5, 400, 0.1, 400, 0.4, false));
        addParallel(new OrientWheelsForTurn());
        addSequential(new AutoDelay(0.4));
        addSequential(new FollowSpeedProfile(1.0, 400, 0.25, 400, 0.55, false));
        addParallel(new OrientWheels(270.0));
        addSequential(new AutoDelay(0.4));
        addSequential(new FollowSpeedProfile(1.0, 600, 0.1, 600, 0.9, false));
        addSequential(new ZeroGyro());
        addSequential(new FollowSpeedProfile(0.5, 200, 0.25, 200, 0.25, false, true));
        addParallel(new IntakeGoToPosition(RobotMap.INTAKE_INTAKE_POSITION));
        addParallel(new OrientWheels(0.0));
        addSequential(new AutoDelay(0.5));
        addSequential(new FollowSpeedProfile(5.0, 600, 0.5, 600, 4.5, true));
    }
    
}