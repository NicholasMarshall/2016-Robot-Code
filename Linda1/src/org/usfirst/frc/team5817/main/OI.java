package org.usfirst.frc.team5817.main;

import org.usfirst.frc.team5817.commands.drive.AutoAim;
import org.usfirst.frc.team5817.commands.drive.ZeroGyro;
import org.usfirst.frc.team5817.commands.drive.ZeroSteer;
import org.usfirst.frc.team5817.commands.intake.IntakeBoth;
import org.usfirst.frc.team5817.commands.intake.IntakeDown;
import org.usfirst.frc.team5817.commands.intake.IntakeGoToPosition;
import org.usfirst.frc.team5817.commands.intake.IntakeUp;
import org.usfirst.frc.team5817.commands.intake.OuttakeBoth;
import org.usfirst.frc.team5817.commands.intake.ZeroIntake;
import org.usfirst.frc.team5817.commands.shooter.BloopShot;
import org.usfirst.frc.team5817.commands.shooter.FireShooter;
import org.usfirst.frc.team5817.commands.shooter.IntakeShooter;
import org.usfirst.frc.team5817.commands.shooter.LowGoalShoot;
import org.usfirst.frc.team5817.commands.shooter.SetShooterAngleUnderVertical;
import org.usfirst.frc.team5817.util.LogitechExtreme3DPro;
import org.usfirst.frc.team5817.util.XboxController;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class OI {

	public XboxController driveController;
	public LogitechExtreme3DPro copilotController;
	
	public JoystickButton intake, outtake, intakeShooter;
	public JoystickButton zeroIntake, zeroGyro;
	public JoystickButton intakeGoToStorage, intakeGoToIntake, intakeGoLow;
	public JoystickButton shooterCornerShot, shooterWeak, shootOuterWorks, shootLowGoal;

	public Trigger zeroDrive;
	public Trigger intakeUp, intakeDown;
	public Trigger fireShooter;
	public Trigger autoAimLeft, autoAimRight;
	public Trigger slowTurnLeft, slowTurnRight;
	
	public OI() {
		driveController = new XboxController(0);
		copilotController = new LogitechExtreme3DPro(1);
		
		intake = new JoystickButton(copilotController, 1);
		outtake = new JoystickButton(copilotController, 2);
		
		intake.whileHeld(new IntakeBoth());
		outtake.whileHeld(new OuttakeBoth());
		
		zeroDrive = new Trigger() {
			public boolean get() {
				return driveController.getXButton() && driveController.getYButton();
			}
		};
		zeroDrive.whileActive(new ZeroSteer());
		
		zeroGyro = new JoystickButton(driveController, 1);
		zeroGyro.whileHeld(new ZeroGyro());
		
		zeroIntake = new JoystickButton(driveController, 2);
		zeroIntake.whileHeld(new ZeroIntake());
		
		fireShooter = new Trigger() {
			public boolean get() {
				return driveController.getRightTrigger() > 0.1;
			}
		};
		fireShooter.whileActive(new FireShooter());
		
		intakeUp = new Trigger() {
			public boolean get() {
				return copilotController.getPOV() == 0 || copilotController.getPOV() == 315 || copilotController.getPOV() == 45;
			}
		};
		intakeUp.whileActive(new IntakeUp());
		
		intakeDown = new Trigger() {
			public boolean get() {
				return copilotController.getPOV() == 180 || copilotController.getPOV() == 225 || copilotController.getPOV() == 135	;
			}
		};
		intakeDown.whileActive(new IntakeDown());
		
		autoAimLeft = new Trigger() {
			public boolean get() {
				return (driveController.getLeftTrigger() > 0.25) || (driveController.getPOV() == 270);
			}
		};
		autoAimLeft.whenActive(new AutoAim());
		
		autoAimRight = new Trigger() {
			public boolean get() {
				return driveController.getPOV() == 90;
			}
		};

		intakeShooter = new JoystickButton(driveController, 3);
		intakeShooter.whileActive(new IntakeShooter());
		
		intakeGoLow = new JoystickButton(driveController, 7);
		intakeGoToStorage = new JoystickButton(driveController, 6);
		intakeGoToIntake = new JoystickButton(driveController, 5);
		
		intakeGoLow.whenPressed(new IntakeGoToPosition(RobotMap.INTAKE_LOW_POSITION));
		intakeGoToStorage.whenPressed(new IntakeGoToPosition(RobotMap.INTAKE_STORAGE_POSITION));
		intakeGoToIntake.whenPressed(new IntakeGoToPosition(RobotMap.INTAKE_INTAKE_POSITION));
		
		shooterCornerShot = new JoystickButton(copilotController, 8);
		shooterWeak = new JoystickButton(copilotController, 7);
		shootOuterWorks = new JoystickButton(copilotController, 10);
		shootLowGoal = new JoystickButton(copilotController, 9);
		
		shooterCornerShot.whileActive(new SetShooterAngleUnderVertical(299));
		shooterWeak.whileActive(new BloopShot());
		shootOuterWorks.whileActive(new SetShooterAngleUnderVertical(270));
		shootLowGoal.whileActive(new LowGoalShoot());
	}
	
}
