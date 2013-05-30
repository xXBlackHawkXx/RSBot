package hawksoakdoors.nodes;

import hawksoakdoors.utils.Variables;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Keyboard;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Destroy extends Node {

	@Override
	public void execute() {
		SceneObject oakDoorOpen = SceneEntities.getNearest(Variables.oakDoorOpen);
		SceneObject oakDoor = SceneEntities.getNearest(Variables.oakDoor);
		
		if (Widgets.get(1188, 3).validate() && Widgets.get(1188, 3).getText().contains("Yes")) {
			Keyboard.sendKey('1');
			final Timer timer = new Timer(1500);
			while (timer.isRunning() && Widgets.get(1188, 3).isOnScreen()) {
				sleep(20);
			}
		}
		else if (oakDoorOpen != null){
			Variables.status = "Closing Door";
			oakDoorOpen.interact("Close");
			final Timer timer = new Timer(1500);
			while (timer.isRunning() && oakDoorOpen.isOnScreen()) {
				sleep(20);
			}
		}
		else if (oakDoor != null) {
			Variables.status = "Constructing";
			oakDoor.interact("Remove");
			Variables.counter++;
			final Timer timer = new Timer(1500);
			while (timer.isRunning() && !Widgets.get(1188, 3).isOnScreen()) {
				sleep(20);
			}
		} 
		else {
			return;
		}
	}

	@Override
	public boolean activate() {
		return SceneEntities.getNearest(Variables.oakDoor) != null 
				|| SceneEntities.getNearest(Variables.oakDoorOpen) != null;
	}
	
	public static void closeDoor() {
		SceneObject oakDoorOpen = SceneEntities.getNearest(Variables.oakDoorOpen);
		Variables.status = "Closing Door";
		oakDoorOpen.interact("Close");
		final Timer timer = new Timer(1500);
		while (timer.isRunning() && oakDoorOpen.isOnScreen()) {
			sleep(20);
		}
	}
	
}