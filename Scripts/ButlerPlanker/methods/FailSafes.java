package hawksbutlerplanker.methods;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Tabs;

public class FailSafes extends Node {

	@Override
	public boolean activate() {
		return true;
	}

	@Override
	public void execute() {
		
		if (!Tabs.INVENTORY.isOpen()) {
			System.out.println("Opening inventory");
			Tabs.INVENTORY.open();
			sleep(250);
		}
		
	}

}
