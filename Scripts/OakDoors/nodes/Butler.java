package hawksoakdoors.nodes;

import hawksoakdoors.methods.Wait;
import hawksoakdoors.utils.Variables;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Keyboard;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class Butler extends Node {
	
	@Override
	public void execute() {
		Variables.status = "Getting Planks";
		
		NPC butler = NPCs.getNearest(Variables.butler);
		
		sleep(1000);
		
		if (Inventory.isFull() && Widgets.get(1188, 3).isOnScreen()) {
			Construct.makeDoor();
		}
		
		if (Widgets.get(1184, 13).validate() && Widgets.get(1184, 13).getText().contains("Your goods, ")) {
			Construct.makeDoor();
		}
		
		if (Inventory.getCount(Variables.oakPlank) <= 20 && Variables.counter <= 2) {
			System.out.println("Another 20");
			if (Widgets.get(1188, 3).validate() && Widgets.get(1188, 3).getText().contains("Fetch another 20 oak")) {			
				Keyboard.sendKey('1');
				/*Wait.waitForInventChange();
				Variables.counter = 0;
				Variables.butlerCounter = 1;
				if (NPCs.getNearest(Variables.butler) != null) {
					System.out.println("Waiting for inventory change");
					Wait.waitForInventChange();
				}*/
			} else if (Widgets.get(1188, 3).validate() && !Widgets.get(1188, 3).getText().contains("Fetch another 20 oak") && Widgets.get(1188, 24).getText().contains("Wooden")) {
				System.out.println("Fetching oak planks");
				Keyboard.sendKey('3');
				Task.sleep(Random.nextInt(1000, 1500));
				Keyboard.sendText("20", true);
				/*Wait.waitForInventChange();
				Variables.counter = 0;
				Variables.butlerCounter = 1;
				if (NPCs.getNearest(Variables.butler) != null) {
					System.out.println("Waiting for inventory change");
					Wait.waitForInventChange();
				}*/
			} else if (Widgets.get(1188, 3).validate() && Widgets.get(1188, 3).getText().contains("Wooden")) {
				System.out.println("Getting oak planks");
				Keyboard.sendKey('2');
				Task.sleep(Random.nextInt(1000, 1500));
				Keyboard.sendText("20", true);
				/*Wait.waitForInventChange();
				Variables.counter = 0;
				Variables.butlerCounter = 1;
				if (NPCs.getNearest(Variables.butler) != null) {
					System.out.println("Waiting for inventory change");
					Wait.waitForInventChange();
				}*/
			} else {
				if (NPCs.getNearest(Variables.butler) != null && Inventory.getCount(Variables.oakPlank) < 20) {
					if (Variables.butlerCounter == 0) {
						System.out.println("Fetch");
						NPCs.getNearest(Variables.butler).interact("Fetch");					
						final Timer timer = new Timer(3000);
						while (timer.isRunning() && !Widgets.get(1188, 3).isOnScreen()) {
							sleep(250);
						}
					}
					Variables.butlerCounter = 0;
				}
			}
		}
		
		/*if (Widgets.get(1188, 3).validate() && Widgets.get(1188, 3).getText().contains("Fetch another 24 oak")) {			
			Keyboard.sendKey('1');
			Wait.waitForInventChange();
		} else if (Widgets.get(1188, 3).validate() && !Widgets.get(1188, 3).getText().contains("Fetch another 24 oak") && Widgets.get(1188, 24).getText().contains("Wooden")) {
			Keyboard.sendKey('3');
			Task.sleep(Random.nextInt(1000, 1500));
			Keyboard.sendText("24", true);
			Wait.waitForInventChange();
				
		} else if (Widgets.get(1188, 3).validate() && Widgets.get(1188, 3).getText().contains("Wooden")) {
			Keyboard.sendKey('2');
			Task.sleep(Random.nextInt(1000, 1500));
			Keyboard.sendText("24", true);
			Wait.waitForInventChange();
		} else {
			if (NPCs.getNearest(Variables.DemonButler) != null && Inventory.getCount(Variables.oakPlank) < 18) {
				NPCs.getNearest(Variables.DemonButler).interact("Fetch");
				final Timer timer = new Timer(3000);
				while (timer.isRunning() && !Widgets.get(1188, 3).isOnScreen()) {
					sleep(250);
				}
			}
		}*/
	}

	@Override
	public boolean activate() {
		return SceneEntities.getNearest(Variables.doorSpot) != null 
				&& Inventory.getCount(Variables.oakPlank) <= 20 
				&& NPCs.getNearest(Variables.butler) != null 
				&& !Widgets.get(394, 11).validate(); //door widget
	}
	
}
