package org.usfirst.frc.team5817.commands.auto;

import org.usfirst.frc.team5817.commands.drive.AutoAim;
import org.usfirst.frc.team5817.commands.drive.AutoOrientWheels;
import org.usfirst.frc.team5817.commands.drive.AutonomousBackPivotAutoAim;
import org.usfirst.frc.team5817.commands.drive.BackPivotAutoAim;
import org.usfirst.frc.team5817.commands.drive.BackPivotGyroTurn;
import org.usfirst.frc.team5817.commands.drive.FastGyroTurn;
import org.usfirst.frc.team5817.commands.drive.FollowSpeedProfile;
import org.usfirst.frc.team5817.commands.drive.GyroTurn;
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

public class LowBarOneBallAuto extends CommandGroup {
    
    public LowBarOneBallAuto() {
    	addParallel(new IntakeGoToPosition(RobotMap.INTAKE_INTAKE_POSITION));
    	addSequential(new AutoOrientWheels(0.0));
    	addSequential(new ZeroGyro());
    	addParallel(new BeginSpinningShooter());
    	addSequential(new FollowSpeedProfile(4.6, 250, 1.0, 250, 3.6, true));
    	addParallel(new IntakeGoToPosition(RobotMap.INTAKE_STORAGE_POSITION));
    	addParallel(new SpinShooter());
    	addSequential(new BackPivotGyroTurn(30.0));
    	addSequential(new AutoDelay(0.25));
    	addSequential(new AutonomousBackPivotAutoAim());
    	addParallel(new FireShooter());
    	addSequential(new AutoDelay(1.5));
    	addParallel(new StopConveyor());
    	addSequential(new StopShooter());
    	addParallel(new IntakeGoToPosition(RobotMap.INTAKE_INTAKE_POSITION));
    	addParallel(new SetShooterAngleUnderVertical(183));
    	addSequential(new BackPivotGyroTurn(0.0));
    	addSequential(new AutoOrientWheels(0.0));
    	addSequential(new FollowSpeedProfile(3.2, 300, 1.0, 300, 2.2, true, true));
    }
    
}