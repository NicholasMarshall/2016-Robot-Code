package org.usfirst.frc.team5817.commands.auto;

import org.usfirst.frc.team5817.commands.drive.AutoOrientWheels;
import org.usfirst.frc.team5817.commands.drive.BackPivotAutoAim;
import org.usfirst.frc.team5817.commands.drive.FollowSpeedProfile;
import org.usfirst.frc.team5817.commands.drive.OrientWheelsForTurn;
import org.usfirst.frc.team5817.commands.drive.ZeroGyro;
import org.usfirst.frc.team5817.commands.intake.IntakeBoth;
import org.usfirst.frc.team5817.commands.shooter.FireShooter;
import org.usfirst.frc.team5817.commands.shooter.SetShooterAngleUnderVertical;
import org.usfirst.frc.team5817.commands.shooter.SpinShooter;
import org.usfirst.frc.team5817.commands.shooter.StopShooter;
import org.usfirst.frc.team5817.main.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoBallAuto extends CommandGroup {
    
    public TwoBallAuto() {
    	addSequential(new ZeroGyro());
    	addParallel(new AutoOrientWheels(310.0));
    	addParallel(new IntakeBoth());
    	addParallel(new SetShooterAngleUnderVertical(RobotMap.SHOOTER_FLICK_POSITION));
    	addSequential(new AutoDelay(0.75));
    	addSequential(new FollowSpeedProfile(0.70, 570, 0.35, 570, 0.35, true));
    	addSequential(new AutoOrientWheels(0.0));
    	addParallel(new SpinShooter());
    	addSequential(new FollowSpeedProfile(1.8, 600, 0.5, 600, 1.0, true));
    	addParallel(new OrientWheelsForTurn());
    	addSequential(new AutoDelay(0.2));
    	addSequential(new FollowSpeedProfile(0.5, 600, 0.25, 600, 0.25, false));
    	addSequential(new AutoDelay(0.2));
    	addSequential(new BackPivotAutoAim());
    	addParallel(new FireShooter());
    	addSequential(new AutoDelay(1.0));
    	addParallel(new StopShooter());
    }
    
}