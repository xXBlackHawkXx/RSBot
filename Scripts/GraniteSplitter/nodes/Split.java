package hawksgranitesplitter.nodes;

import hawksgranitesplitter.utils.Variables;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.Item;

public class Split extends Node {

	@Override
	public boolean activate() {
		Variables.inventorySpace = 28 - Inventory.getCount();
		return (!Inventory.isFull()) && Variables.inventorySpace >= 3 && (Inventory.contains(Variables.fiveKgGranite) || Inventory.contains(Variables.twoKgGranite));
	}

	@Override
	public void execute() {
		
		Item fiveKg = Inventory.getItem(Variables.fiveKgGranite);
		Item twoKg = Inventory.getItem(Variables.twoKgGranite);

		if (Inventory.contains(Variables.fiveKgGranite)) {
			Variables.status = "Crafting 5kg block";
			fiveKg.getWidgetChild().interact("Craft");
			Timer timer = new Timer(4000);
			while (timer.isRunning() && !Widgets.get(1370, 38).validate()) {
				sleep(20);
			}
			if (Widgets.get(1370, 38).click(true)) {
				Timer timer2 = new Timer(10000);
				while (timer2.isRunning() && Inventory.contains(Variables.fiveKgGranite)) {
					sleep(20);
				}
			}
		} 
		else if (Inventory.contains(Variables.twoKgGranite)) {
			Variables.inventorySpace = 28 - Inventory.getCount();
			
			Variables.status = "Crafting 2kg block";
			twoKg.getWidgetChild().interact("Craft");
			Timer timer = new Timer(4000);
			while (timer.isRunning() && !Widgets.get(1370, 38).validate()) {
				sleep(20);
			}
			if (Widgets.get(1370, 38).click(true)) {
				Timer timer2 = new Timer(10000);
				while (timer2.isRunning() && Inventory.contains(Variables.twoKgGranite)) {
					sleep(20);
				}
			}
		}

	}

}
