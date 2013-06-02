package hawksgranitesplitter.nodes;

import hawksgranitesplitter.utils.Variables;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Tabs;

public class Failsafes extends Node {

	@Override
	public boolean activate() {
		return true;
	}

	@Override
	public void execute() {

		if (!Tabs.INVENTORY.isOpen()) {
			Variables.status = "Opening inventory";
			Tabs.INVENTORY.open();
		}

	}

}
