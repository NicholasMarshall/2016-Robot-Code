package org.usfirst.frc.team5817.commands.auto;

import org.usfirst.frc.team5817.commands.drive.AutonomousBackPivotAutoAim;
import org.usfirst.frc.team5817.commands.drive.ZeroGyro;
import org.usfirst.frc.team5817.commands.shooter.BeginSpinningShooter;
import org.usfirst.frc.team5817.commands.shooter.FireShooter;
import org.usfirst.frc.team5817.commands.shooter.SetShooterAngleUnderVertical;
import org.usfirst.frc.team5817.commands.shooter.StopConveyor;
import org.usfirst.frc.team5817.commands.shooter.StopShooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestAuto extends CommandGroup {
    
    public  TestAuto() {
    	addParallel(new ZeroGyro());
        addParallel(new BeginSpinningShooter());
        addParallel(new SetShooterAngleUnderVertical(318.0));
        addSequential(new AutoDelay(2.0));
        addSequential(new AutonomousBackPivotAutoAim());
        addParallel(new FireShooter());
        addSequential(new AutoDelay(1.5));
        addParallel(new StopShooter());
        addSequential(new StopConveyor());
    }
    
}