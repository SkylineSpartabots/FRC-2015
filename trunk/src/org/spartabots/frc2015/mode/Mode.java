package org.spartabots.frc2015.mode;

import org.spartabots.frc2015.Robot;

import edu.wpi.first.wpilibj.Timer;

/*
 *  SAFETY PIG HAS ARRIVED!
 * 
 *   _._ _..._ .-',     _.._(`))
 *  '-. `     '  /-._.-'    ',/
 *     )         \            '.
 *    / _    _    |             \
 *   |  a    a    /              |
 *   \   .-.                     ;  
 *    '-('' ).-'       ,'       ;
 *       '-;           |      .'
 *          \           \    /
 *          | 7  .__  _.-\   \
 *          | |  |  ``/  /`  /
 *         /,_|  |   /,_/   /
 *            /,_/      '`-'
 * 
 */
public abstract class Mode {
	Robot robot;
	private int mode;
	public static final int CONTROL = 0;
	public static final int AUTONOMOUS = 1;
	
	public Mode(Robot robot, int mode) {
		this.robot = robot;
		this.mode = mode;
	}
	
	public final void start() {
		if (mode == CONTROL) {
			init();
			while (robot.isOperatorControl() && robot.isEnabled()) {
				this.controlPeriodic();
				Timer.delay(0.005);
			}
			done();
		} else if (mode == AUTONOMOUS) {
			init();
			while (robot.isAutonomous() && robot.isEnabled()) {
				this.autoPeriodic();
				Timer.delay(0.005);
			}
			done();
		}
	}
	
	public void init() {
		
	}
	
	public void done() {
		
	}

	public void controlPeriodic() {
	}

	public void autoPeriodic() {
	}
}