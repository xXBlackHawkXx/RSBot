package hawksbutlerplanker.methods;

import hawksbutlerplanker.main.Data;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.input.Keyboard;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Portal extends Node {

	@Override
	public void execute() {
		System.out.println("Entering portal");
		SceneObject portal = SceneEntities.getNearest(Data.portal);
		if (portal != null) {
			if (portal.isOnScreen()) {
				portal.interact("Enter");
				sleep(350);
			} else {
				Camera.turnTo(portal.getLocation());
			}
		} else {
			System.out.println("Cannot find portal");
			//stop();
		}
			
		while (Data.widgetPortalEntry.isOnScreen()) {
			Keyboard.sendKey('1');
			sleep(1500);
		}
	}
		
	@Override
	public boolean activate() {
		return SceneEntities.getNearest(Data.portal) !=null;	
	}
	
}
