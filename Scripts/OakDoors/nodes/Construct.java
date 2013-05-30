package hawksoakdoors.nodes;

import hawksoakdoors.utils.Variables;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Construct extends Node {

	@Override
	public void execute() {
		Variables.status = "Constructing";	
		
		SceneObject doorSpot = SceneEntities.getNearest(Variables.doorSpot);
		SceneObject oakDoor = SceneEntities.getNearest(Variables.oakDoor);
		
		if (doorSpot != null && doorSpot.isOnScreen()) {
			if (Widgets.get(1306, 4).validate() && Widgets.get(1306, 4).isOnScreen()) {
				Widgets.get(1306, 4).click(true);	
				Variables.doorsMade++;
				final Timer timer = new Timer(3000);
				while (timer.isRunning() && oakDoor == null) {
					sleep(250);
				}
			}
			else if (!Widgets.get(1306, 4).isOnScreen()) {
				doorSpot.interact("Build");
				final Timer timer2 = new Timer(3000);
				while (timer2.isRunning() && !Widgets.get(1306, 4).isOnScreen()) {
					sleep(250);
				}
			}
			else {
				doorSpot.hover();
			}
		}
	
	}

	@Override
	public boolean activate() {
		return SceneEntities.getNearest(Variables.doorSpot) != null 
				&& Inventory.getCount(Variables.oakPlank) >= 10
				&& Variables.counter < 2;
	}
	
	public static void makeDoor() {
		SceneObject doorSpot = SceneEntities.getNearest(Variables.doorSpot);
		SceneObject oakDoor = SceneEntities.getNearest(Variables.oakDoor);
		
		if (doorSpot != null && doorSpot.isOnScreen()) {
			if (Widgets.get(1306, 4).validate() && Widgets.get(1306, 4).isOnScreen()) {
				Widgets.get(1306, 4).click(true);	
				Variables.doorsMade++;
				final Timer timer = new Timer(3000);
				while (timer.isRunning() && oakDoor == null) {
					sleep(250);
				}
			}
			else if (!Widgets.get(1306, 4).isOnScreen()) {
				doorSpot.interact("Build");
				final Timer timer2 = new Timer(3000);
				while (timer2.isRunning() && !Widgets.get(1306, 4).isOnScreen()) {
					sleep(250);
				}
			}
			else {
				doorSpot.hover();
			}
		}
	}
	
}
