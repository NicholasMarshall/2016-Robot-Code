package org.usfirst.frc.team5817.commands.auto;

import org.usfirst.frc.team5817.commands.drive.FollowSpeedProfile;
import org.usfirst.frc.team5817.commands.drive.OrientWheels;
import org.usfirst.frc.team5817.commands.drive.OrientWheelsForTurn;
import org.usfirst.frc.team5817.commands.drive.ZeroGyro;
import org.usfirst.frc.team5817.commands.intake.IntakeGoToPosition;
import org.usfirst.frc.team5817.commands.shooter.FireShooter;
import org.usfirst.frc.team5817.commands.shooter.SetShooterAngleUnderVertical;
import org.usfirst.frc.team5817.commands.shooter.SpinShooter;
import org.usfirst.frc.team5817.commands.shooter.SpinShooter50;
import org.usfirst.frc.team5817.commands.shooter.StopConveyor;
import org.usfirst.frc.team5817.commands.shooter.StopShooter;
import org.usfirst.frc.team5817.main.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SpyBoxShootAndCrossBack extends CommandGroup {
    
    public SpyBoxShootAndCrossBack() {
    	addSequential(new ZeroGyro()); //Zero the gyro
    	addParallel(new SetShooterAngleUnderVertical(319 /* This is gonna have to increase because of the slower speed */));
    	addParallel(new SpinShooter50()); //Spin the shooter to half speed
    	addSequential(new AutoDelay(1.0)); //Let the shooter spin up
    	addParallel(new FireShooter()); //Run the conveyor to fire
    	addSequential(new AutoDelay(1.5)); //Wait a little to let it actually shoot
    	addParallel(new OrientWheelsForRearRightPivot()); //Orient the wheels to pivot
    	addParallel(new StopConveyor()); //At the same time stop the conveyor
    	addParallel(new StopShooter()); //At the same time stop the shooter
    	addParallel(new SetShooterAngleUnderVertical(RobotMap.SHOOTER_STORAGE_POSITION)); //At the same time lower the shooter
    	addSequential(new AutoDelay(0.2)); //Give all of this a sec to happen before turning
    	addSequential(new SpyBoxGyroTurn(95.0)); //Execute a gyro turn
    	addParallel(new IntakeGoToPosition(RobotMap.INTAKE_INTAKE_POSITION)); //Let the intake come down to intake position, you're gonna need to adjust the exact value in RobotMap
    	addSequential(new FollowSpeedProfile(5.0, 600, 0.50, 600, 4.5, true)); /* Drive forward */ //Format = Total profile time, max speed, ramp up time, max speed, begin ramp down time, and whether or not it should use the gyro
    	addSequential(new FollowSpeedProfile(3.0, 600, 0.50, 600, 2.5, true, true)); /* Drive backwards */ //Same as above, but with an added boolean. if you put a second boolean that is true it reverses the profile
    }
    
}