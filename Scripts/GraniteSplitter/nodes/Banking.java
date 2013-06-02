package hawksgranitesplitter.nodes;

import hawksgranitesplitter.utils.Variables;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;

public class Banking extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount() >= 27 || (!Inventory.contains(Variables.fiveKgGranite) || !Inventory.contains(Variables.twoKgGranite));
	}

	@Override
	public void execute() {
		if (Bank.isOpen()) {
			if (Inventory.getCount() == 0) {
				if (Bank.getItemCount(Variables.fiveKgGranite) > 0) {
					Variables.status = "Withdrawing 5kg blocks";
					Bank.withdraw(Variables.fiveKgGranite, 3);
					Timer timer = new Timer(4000);
					while (timer.isRunning() && !Inventory.contains(Variables.fiveKgGranite)) {
						sleep(20);
					}
					Bank.close();
					Timer timer2 = new Timer(4000);
					while (timer2.isRunning() && Bank.isOpen()) {
						sleep(20);
					}
				} else if (Bank.getItemCount(Variables.twoKgGranite) > 0) {
					Variables.status = "Withdrawing 2kg blocks";
					Bank.withdraw(Variables.twoKgGranite, 7);
					Timer timer = new Timer(4000);
					while (timer.isRunning() && !Inventory.contains(Variables.twoKgGranite)) {
						sleep(20);
					}
					Bank.close();
					Timer timer2 = new Timer(4000);
					while (timer2.isRunning() && Bank.isOpen()) {
						sleep(20);
					}
				} else {
					System.out.println("Out of granite");
				}
			} else {
				Variables.status = "Deposition inventory";
				Bank.depositInventory();
				Timer timer = new Timer(4000);
				while (timer.isRunning() && !(Inventory.getCount() == 0)) {
					sleep(20);
				}
			}			
		} else {
			Variables.status = "Opening bank";
			Bank.open();
			Timer timer = new Timer(4000);
			while (timer.isRunning() && !Bank.isOpen()) {
				sleep(20);
			}
		}
	}

}
