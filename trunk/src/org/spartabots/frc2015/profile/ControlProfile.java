package org.spartabots.frc2015.profile;

import org.spartabots.frc2015.Ports;
import org.spartabots.frc2015.util.Util;
import org.spartabots.frc2015.util.XboxController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ControlProfile extends Profile {
    public XboxController driveController = new XboxController(Ports.Computer.Usb0) ;
	double prevLeftX = 0;
	int i = 0;
	boolean isBDown = false;
	
	public ControlProfile() {
		super(Profile.CONTROL);
	}
	
	@Override
	public void init() {
		
	}
	
	@Override
	public void controlPeriodic() {
        i++;
        SmartDashboard.putNumber("Teleop Alive", i);
        SmartDashboard.putNumber("Gyro Angle", robot.drive.getGyroAngle());
		
		double traverseMove = -Util.cutoff(driveController.getLeftTriggerAxis()) + Util.cutoff(driveController.getRightTriggerAxis());
        double leftY = Util.cutoff(driveController.getLeftYAxis());
        double rightX = Util.cutoff(driveController.getRightXAxis());
        
        // Traversing wheel
        double curve = robot.drive.curveDrive(traverseMove, prevLeftX, true, 3);
        SmartDashboard.putNumber("Curve Drive", curve);
        robot.drive.traverse.set(curve);
        
        // Speedmode (slow vs fast, caps max speed)
        if (driveController.getBButton()) {
        	if (!isBDown) {
	        	isBDown = true;
	            robot.drive.toggleSpeedMode();
        	}
        } else {
        	isBDown = false;
        }
        
        // Drives robot with curves
        robot.drive.drive(leftY, rightX);
        
        prevLeftX = traverseMove;
	}
	
	@Override
	public void done() {
		
	}
}