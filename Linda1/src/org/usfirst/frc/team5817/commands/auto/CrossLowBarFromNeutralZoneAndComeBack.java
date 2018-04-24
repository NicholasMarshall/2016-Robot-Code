package org.usfirst.frc.team5817.commands.auto;

import org.usfirst.frc.team5817.commands.drive.FollowSpeedProfile;
import org.usfirst.frc.team5817.commands.drive.OrientWheels;
import org.usfirst.frc.team5817.commands.drive.ZeroGyro;
import org.usfirst.frc.team5817.commands.intake.IntakeGoToPosition;
import org.usfirst.frc.team5817.main.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossLowBarFromNeutralZoneAndComeBack extends CommandGroup {
    
    public CrossLowBarFromNeutralZoneAndComeBack() {
    	addParallel(new OrientWheels(0.0));
    	addSequential(new ZeroGyro());
    	addSequential(new AutoDelay(2.0));
    	addParallel(new IntakeGoToPosition(RobotMap.INTAKE_LOW_POSITION));
        addSequential(new FollowSpeedProfile(5.0, 400, 1.0, 400, 4.0, true));
        addSequential(new AutoDelay(1.0));
        addSequential(new FollowSpeedProfile(4.5, 400, 1.0, 400, 3.5, true));
    }
    
}