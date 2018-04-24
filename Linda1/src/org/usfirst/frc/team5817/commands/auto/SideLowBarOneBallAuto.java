package org.usfirst.frc.team5817.commands.auto;

import org.usfirst.frc.team5817.commands.drive.AutoOrientWheels;
import org.usfirst.frc.team5817.commands.drive.AutonomousBackPivotAutoAim;
import org.usfirst.frc.team5817.commands.drive.BackPivotGyroTurn;
import org.usfirst.frc.team5817.commands.drive.FollowSidewaysSpeedProfile;
import org.usfirst.frc.team5817.commands.drive.FollowSpeedProfile;
import org.usfirst.frc.team5817.commands.drive.ZeroGyro;
import org.usfirst.frc.team5817.commands.intake.IntakeGoToPosition;
import org.usfirst.frc.team5817.commands.shooter.BeginSpinningShooter;
import org.usfirst.frc.team5817.commands.shooter.FireShooter;
import org.usfirst.frc.team5817.commands.shooter.SetShooterAngleUnderVertical;
import org.usfirst.frc.team5817.commands.shooter.SpinShooter;
import org.usfirst.frc.team5817.commands.shooter.StopConveyor;
import org.usfirst.frc.team5817.commands.shooter.StopShooter;
import org.usfirst.frc.team5817.main.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SideLowBarOneBallAuto extends CommandGroup {
    
    public SideLowBarOneBallAuto() {
    	addSequential(new ZeroGyro());
    	addSequential(new AutoOrientWheels(270.0));
    	addSequential(new AutoDelay(2.0));
    	addParallel(new IntakeGoToPosition(RobotMap.INTAKE_INTAKE_POSITION));
    	addSequential(new FollowSidewaysSpeedProfile(2.0, 350, 1.0, 350, 1.0, true, false));
    	addSequential(new AutoOrientWheels(0.0));
    	addParallel(new SpinShooter());
    	addSequential(new FollowSpeedProfile(4.5, 350, 1.0, 350, 3.5, true));
    	addParallel(new IntakeGoToPosition(RobotMap.INTAKE_STORAGE_POSITION));
    	addSequential(new BackPivotGyroTurn(55.0));
    	addSequential(new AutoDelay(0.25));
    	addSequential(new AutonomousBackPivotAutoAim());
    	addParallel(new FireShooter());
    	addSequential(new AutoDelay(1.5));
    	addParallel(new StopConveyor());
    	addSequential(new StopShooter());
    	addParallel(new SetShooterAngleUnderVertical(RobotMap.SHOOTER_STORAGE_POSITION));
    	addSequential(new BackPivotGyroTurn(0.0));
    }
    
}