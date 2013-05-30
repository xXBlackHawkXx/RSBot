package hawksoakdoors.nodes;

import hawksoakdoors.utils.Variables;

import java.awt.event.KeyEvent;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Keyboard;
import org.powerbot.game.api.util.Random;

public class Pay extends Node {

	@Override
	public void execute() {
		Variables.status = "Paying Butler";
		Task.sleep(Random.nextInt(200, 400));
		if (Widgets.get(1184, 18).validate()) {
			Keyboard.sendKey(((char)KeyEvent.VK_SPACE));
			Task.sleep(Random.nextInt(1200, 1500));
			Keyboard.sendKey('1');
			Task.sleep(Random.nextInt(800, 1200));
		}
	}

	@Override
	public boolean activate() {
		return Widgets.get(1184, 13).validate() && Widgets.get(1184, 13).getText().contains("coins");
	}	
	
}
