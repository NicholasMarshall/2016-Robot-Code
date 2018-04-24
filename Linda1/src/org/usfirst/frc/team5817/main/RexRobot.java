package org.usfirst.frc.team5817.main;

import org.usfirst.frc.team5817.commands.auto.CrossLowBarFromNeutralZone;
import org.usfirst.frc.team5817.commands.auto.CrossLowBarFromNeutralZoneAndComeBack;
import org.usfirst.frc.team5817.commands.auto.DoNothingAuto;
import org.usfirst.frc.team5817.commands.auto.LowBarOneBallAuto;
import org.usfirst.frc.team5817.commands.auto.ReachFromNeutral;
import org.usfirst.frc.team5817.commands.auto.SideLowBarOneBallAuto;
import org.usfirst.frc.team5817.commands.auto.SpyBoxShoot;
import org.usfirst.frc.team5817.commands.auto.SpyBoxShootAndCross;
import org.usfirst.frc.team5817.commands.auto.TestAuto;
import org.usfirst.frc.team5817.commands.auto.TwoBallAuto;
import org.usfirst.frc.team5817.subsystems.Conveyor;
import org.usfirst.frc.team5817.subsystems.Intake;
import org.usfirst.frc.team5817.subsystems.IntakeRotation;
import org.usfirst.frc.team5817.subsystems.Shooter;
import org.usfirst.frc.team5817.subsystems.ShooterRotation;
import org.usfirst.frc.team5817.subsystems.SwerveWheel;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RexRobot extends IterativeRobot {
	
	public static OI oi;
	
	public static SwerveWheel frontLeftSwerveWheel = new SwerveWheel(RobotMap.FRONT_LEFT_DRIVE, RobotMap.FRONT_LEFT_STEER, false, false, false, false);
	public static SwerveWheel frontRightSwerveWheel = new SwerveWheel(RobotMap.FRONT_RIGHT_DRIVE, RobotMap.FRONT_RIGHT_STEER, false, true, true, false);
	public static SwerveWheel rearLeftSwerveWheel = new SwerveWheel(RobotMap.REAR_LEFT_DRIVE, RobotMap.REAR_LEFT_STEER, false, false, false, false);
	public static SwerveWheel rearRightSwerveWheel = new SwerveWheel(RobotMap.REAR_RIGHT_DRIVE, RobotMap.REAR_RIGHT_STEER, false, false, true, false);
	
	/* Ankylosaurus (Practive robot) wheels
	public static SwerveWheel frontLeftSwerveWheel = new SwerveWheel(RobotMap.FRONT_LEFT_DRIVE, RobotMap.FRONT_LEFT_STEER, false, false, false, false);
	public static SwerveWheel frontRightSwerveWheel = new SwerveWheel(RobotMap.FRONT_RIGHT_DRIVE, RobotMap.FRONT_RIGHT_STEER, false, false, true, false);
	public static SwerveWheel rearLeftSwerveWheel = new SwerveWheel(RobotMap.REAR_LEFT_DRIVE, RobotMap.REAR_LEFT_STEER, false, false, true, false);
	public static SwerveWheel rearRightSwerveWheel = new SwerveWheel(RobotMap.REAR_RIGHT_DRIVE, RobotMap.REAR_RIGHT_STEER, false, true, true, false);
	*/
	
	public static AHRS navx;
	
	public static Intake intake = new Intake();
	public static IntakeRotation intakeRotation = new IntakeRotation();
	public static Shooter shooter = new Shooter();
	public static ShooterRotation shooterRotation = new ShooterRotation();
	public static Conveyor conveyor = new Conveyor();
	
	private Command autoCommand;
	private static NetworkTable gripTable;
	
	private SendableChooser autoChooser;
	
	public RexRobot() {
		gripTable = NetworkTable.getTable("GRIP");
	}
	
	public void robotInit() {
		oi = new OI();
		
		frontLeftSwerveWheel.setPID(0.06, 0.0004, 0.0, 2.5, 0.0, 0.0);
		frontRightSwerveWheel.setPID(0.07, 0.0003, 0.0, 2.5, 0.0, 0.0);
		rearLeftSwerveWheel.setPID(0.07, 0.0003, 0.0, 2.5, 0.0, 0.0);
		rearRightSwerveWheel.setPID(0.07, 0.0003, 0.0, 2.5, 0.0, 0.0);
		
		autoChooser = new SendableChooser();
		autoChooser.addDefault("Do Nothing", new DoNothingAuto());
		autoChooser.addObject("Cross Low Bar", new CrossLowBarFromNeutralZone());
		autoChooser.addObject("Cross Low Bar And Come Back", new CrossLowBarFromNeutralZoneAndComeBack());
		autoChooser.addObject("Reach", new ReachFromNeutral());
		autoChooser.addObject("Spy Box Shoot", new SpyBoxShoot());
		autoChooser.addObject("Spy Box Shoot and Cross", new SpyBoxShootAndCross());
		autoChooser.addObject("Low Bar One Ball Auto", new LowBarOneBallAuto());
		autoChooser.addObject("Side Low Bar One Ball Auto", new SideLowBarOneBallAuto());
		autoChooser.addObject("Test Auto", new TestAuto());
		autoChooser.addObject("Spy Box Shoot and Cross Back", new SpyBoxShootAndCrossBack());
		SmartDashboard.putData("Auto Modes", autoChooser);
		
		SmartDashboard.putBoolean("Banner Sensor Tripped", false);
		
		navx = new AHRS(Port.kMXP);
    }
	
	public void autonomousInit() {
		autoCommand = (Command) autoChooser.getSelected();
		autoCommand.start();
	}
	
	public void teleopInit() {
		if(autoCommand != null) {
			autoCommand.cancel();
		}
	}
	
	public void disabledInit() {
		if(autoCommand != null) {
			autoCommand.cancel();
		}
	}
	
    public void autonomousPeriodic() {
    	Scheduler.getInstance().run();
    	SmartDashboard.putBoolean("Banner Sensor Tripped", !RexRobot.intake.isBallLoaded());
    	SmartDashboard.putNumber("Shooter Position", RexRobot.shooterRotation.getRaw());
    }
    
    public void teleopPeriodic() {
    	Scheduler.getInstance().run();
    	SmartDashboard.putBoolean("Banner Sensor Tripped", !RexRobot.intake.isBallLoaded());
    	SmartDashboard.putNumber("Shooter Position", RexRobot.shooterRotation.getRaw());
    	SmartDashboard.putNumber("Battery Voltage", RexRobot.getBatteryVoltage());
    }
    
    public void disabledPeriodic() {
    	Scheduler.getInstance().run();
    	SmartDashboard.putBoolean("Banner Sensor Tripped", !RexRobot.intake.isBallLoaded());
    	SmartDashboard.putNumber("Shooter Position", RexRobot.shooterRotation.getRaw());
    	SmartDashboard.putNumber("Battery Voltage", RexRobot.getBatteryVoltage());
    }
	
    public static double getCenterY() {
    	for(double number : gripTable.getNumberArray("goalContoursReport/centerY", new double[0])) {
    		return number;
    	}
    	return 0.0;
    }
    
    public static double getCenterX() {
    	for(double number : gripTable.getNumberArray("goalContoursReport/centerX", new double[0])) {
    		return number;
    	}
    	return 0.0;
    }
    
    public static double getBestCenterX() {
    	double smallestNumber = 9001;
    	for(double number : gripTable.getNumberArray("goalContoursReport/centerX", new double[0])) {
    		if(number < smallestNumber) {
    			smallestNumber = number;
    		}
    	}
    	if(smallestNumber != 9001) {
    		return smallestNumber;
    	}
    	return 0.0;
    }
    
    public static double getBestCenterY() {
    	double smallestNumber = 9001;
    	int index = 0;
    	int counter = 0;
    	for(double number : gripTable.getNumberArray("goalContoursReport/centerX", new double[0])) {
    		if(number < smallestNumber) {
    			smallestNumber = number;
    			index = counter;
    		}
    		counter++;
    	}
    	double[] array = gripTable.getNumberArray("goalContoursReport/centerY", new double[0]);
    	if(array.length > 0) {
    		return array[index];
    	}
    	return 0.0;
    }
    
    public static double getOtherBestCenterX() {
    	double largestNumber = 0.0;
    	for(double number : gripTable.getNumberArray("goalContoursReport/centerX", new double[0])) {
    		if(number > largestNumber) {
    			largestNumber = number;
    		}
    	}
    	return 0.0;
    }
    
    public static double getOtherBestCenterY() {
    	double largestNumber = 0.0;
    	int index = 0;
    	int counter = 0;
    	for(double number : gripTable.getNumberArray("goalContoursReport/centerX", new double[0])) {
    		if(number > largestNumber) {
    			largestNumber = number;
    			index = counter;
    		}
    		counter++;
    	}
    	double[] array = gripTable.getNumberArray("goalContoursReport/centerY", new double[0]);
    	if(array.length > 0) {
    		return array[index];
    	}
    	return 0.0;
    }
    
    public static double getAngleChange(double centerX, double distance) {
    	return Math.toDegrees(Math.atan((160 - centerX) * Math.tan(Math.toRadians(33.5)) / 160)) + 90.0 - Math.toDegrees(Math.atan(0.75 * distance));
    }
    
    public static double getBatteryVoltage() {
    	return DriverStation.getInstance().getBatteryVoltage();
    }
    
}