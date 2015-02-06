package org.spartabots.frc2015.auto;

import org.spartabots.frc2015.action.Actions;
import org.spartabots.frc2015.action.SeriesAction;
import org.spartabots.frc2015.util.Util;

// TODO ESTIMATED VALUES ARE CURRENTLY BEING USED, change values to actual
// TODO move these actions into series and parallel and put them into functions that all 4 auto modes can use
public class Auto3Tote extends SeriesAction {
	
	@Override
	public void init() {
		robot.drive.resetGyro();
		robot.drive.resetEncoders();
		
		// Tote 1
		enqueue(Actions.clampIn());
		enqueueToProfile(Actions.elevatorTime(0.85, 2000));
		enqueue(Actions.traverseTime(200, 0.5));
		enqueue(Actions.driveDist(Util.toMeters(2.75), 0.85));
		enqueue(Actions.traverseTime(-200, 0.5));
		
		// Tote 2
		enqueue(Actions.elevatorTime(-0.75, 500)); // bring elevatorTime down a bit
		enqueue(Actions.clampOut()); // drop tote over other tote
		enqueue(Actions.elevatorTime(-0.85, 2000)); // bring elevatorTime down to bottom most tote
		enqueue(Actions.clampIn()); // pick up both totes (which should now be stacked)
		enqueueToProfile(Actions.elevatorTime(0.85, 2500)); // bring elevatorTime back up
		enqueue(Actions.traverseTime(200, 0.5));
		enqueue(Actions.driveDist(Util.toMeters(1.5), 0.85));
		enqueue(Actions.traverseTime(-200, 0.5));
		
		// Tote 3
		enqueue(Actions.elevatorTime(-0.75, 500)); // bring elevatorTime down a bit
		enqueue(Actions.clampOut()); // drop tote over other tote
		enqueue(Actions.elevatorTime(-0.85, 2000)); // bring elevatorTime down to bottom most tote
		enqueue(Actions.clampIn()); // pick up all three totes
		enqueueToProfile(Actions.elevatorTime(0.85, 2500)); // bring elevatorTime back up
		
		// Drive to auto zone
		enqueue(Actions.rotateDeg(90, 0.6)); // Rotate robot to face auto zone
		enqueueToProfile(Actions.elevatorTime(-0.85, 2500)); // bring elevatorTime down to right above floor
		enqueue(Actions.driveDist(Util.toMeters(8.5) ,0.85)); // Drive to auto zone
		enqueue(Actions.clampOut()); // Let go of all totes
		enqueue(Actions.driveDist(Util.toMeters(-2), 0.85)); // Back off
	}

}