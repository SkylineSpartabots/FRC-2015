package org.spartabots.frc2015.action;

import org.spartabots.frc2015.Robot;

import edu.wpi.first.wpilibj.Timer;

/**
 * Action<br/></br>
 * 
 * The init() method is called once to perform the action. The action will continue
 * to be performed until either the action times out or the action decides it should
 * stop running by the isRunning method. Once the action is complete, the done method
 * will be called stop and cleanup whatever needs to be stopped or cleaned up.
 */
public abstract class Action {
	protected Timer timer = new Timer();
	protected boolean done = false;
	protected static Robot robot = Robot.getInstance();
	protected boolean init = false;
	protected double timeout = 100; // in seconds
	protected boolean canTimeOut = true;
	protected boolean enqueue2 = false; // for actions when added to series
	
	public abstract void init();
	/**
	 * Periodically called during action run-time
	 *  
	 * @return running whether or not to continue running
	 */
	public abstract boolean runPeriodic();
	public abstract void done();
	
	public boolean running() {
		if (!init) {
			init = true;
			timer.start();
			init();
		}
		
		if (done || !runPeriodic() || isTimedOut()) {
			timer.stop();
			done();
			return false;
		}
		return true;
		
	}
	
	public void cancel() {
		done = true;
	}
	
	public boolean isDone() {
		return done;
	}
	
	public boolean isTimedOut() {
		return canTimeOut && timer.get() > timeout;
	}
	
	public double getTime() {
		return timer.get();
	}
	
	public void setTimeout(double milliseconds) {
		this.timeout = (milliseconds / 1000.0D);
	}
}