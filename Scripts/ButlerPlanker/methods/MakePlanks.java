package hawksbutlerplanker.methods;

import java.awt.event.KeyEvent;

import hawksbutlerplanker.main.Data;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Keyboard;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class MakePlanks extends Node {

	@Override
	public boolean activate() {
		return Inventory.contains(Data.notedOakLogs);
	}

	@Override
	public void execute() {

		if (Widgets.get(1184, 13).validate() && Widgets.get(1184, 13).getText().contains("coins")) {
			System.out.println("Paying butler");
			if (Widgets.get(1184, 18).validate()) {
				Keyboard.sendKey(((char)KeyEvent.VK_SPACE));
				Task.sleep(Random.nextInt(1200, 1500));
				Keyboard.sendKey('1');
				Task.sleep(Random.nextInt(800, 1200));
			}
		}
		
		//System.out.println("Making planks");
		NPC butler = NPCs.getNearest(Data.demonButler);
		
		if (butler != null && butler.isOnScreen()) {
			
			if (Widgets.get(1188, 3).validate() && Widgets.get(1188, 3).getText().contains("I have returned")) {
				System.out.println("Talking to butler");
				butler.interact("Fetch");
				final Timer timer = new Timer(2000);
				while (timer.isRunning() && !Widgets.get(1188, 3).isOnScreen()) {
					sleep(250);
				}
			}
				
			if ((Widgets.get(1188, 3).validate() && Widgets.get(1188, 3).getText().contains("Un-cert another 26 oak logs")) || (Widgets.get(1184, 13).validate() && Widgets.get(1184, 13).getText().contains("I have returned"))) {
				System.out.println("Uncerting more");
				Keyboard.sendKey('1');
				final Timer timer2 = new Timer(6000);
				while (timer2.isRunning() && !Widgets.get(1188, 3).isOnScreen()) {
					sleep(250);
				}
			} else if (Widgets.get(1188, 3).validate() && Widgets.get(1188, 3).getText().contains("Wooden")) {
				System.out.println("Using logs on butler");
				Inventory.getItem(Data.notedOakLogs).getWidgetChild().interact("Use");
				final Timer timer3 = new Timer(6000);
				while (timer3.isRunning() && !Inventory.isItemSelected()) {
					sleep(250);
				}
				butler.click(true);
				final Timer timer4 = new Timer(6000);
				while (timer4.isRunning() && !Widgets.get(1188, 3).getText().contains("Un-cert")) {
					sleep(250);
				}
			} else if (Widgets.get(1188, 3).validate() && Widgets.get(1188, 3).getText().contains("Un-cert")) {
				System.out.println("Clicking uncert");	
				Keyboard.sendKey('1');
				final Timer timer5 = new Timer(6000);
				while (timer5.isRunning() && Widgets.get(1188, 3).getText().contains("Un-cert")) {
					sleep(250);
				}		
			} else if (Widgets.get(752, 4).validate() && Widgets.get(752, 4).isOnScreen() && Widgets.get(752, 4).getText().contains("Enter amount")) {
				Keyboard.sendText("26", true);
				final Timer timer6 = new Timer(6000);
				while (timer6.isRunning() && !Widgets.get(1188, 3).isOnScreen()) {
					sleep(250);
				}
			} else if (Widgets.get(1188, 3).validate() && Widgets.get(1188, 3).getText().contains("Accept logs")) {
				System.out.println("Sending planks to bank");
				Keyboard.sendKey('2');
				final Timer timer8 = new Timer(6000);
				while (timer8.isRunning() && !Widgets.get(1188, 3).isOnScreen()) {
					sleep(250);
				}	
			} else {
				System.out.println("Talking to butler");
				butler.interact("Fetch");
				final Timer timer = new Timer(2000);
				while (timer.isRunning() && !Widgets.get(1188, 3).isOnScreen()) {
					sleep(250);
				}
			}
		} else {
			Walking.walk(butler.getLocation());
			final Timer timer = new Timer(2000);
			while (timer.isRunning() && !butler.isOnScreen()) {
				sleep(250);
			}
		}
		
	}
	
}
