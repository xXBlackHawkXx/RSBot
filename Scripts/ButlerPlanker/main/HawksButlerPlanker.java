package hawksbutlerplanker.main;

import java.awt.Graphics;

import hawksbutlerplanker.methods.*;

import org.powerbot.core.Bot;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.widget.WidgetCache;
import org.powerbot.game.client.Client;



@Manifest(
		authors = { "xXBlackHawkXx" }, 
		description = "Makes oak planks with your house demon butler", 
		name = "Hawks Butler Planker"
		)

public class HawksButlerPlanker extends ActiveScript implements PaintListener {

	private Client client = Bot.client();

	private final Tree script = new Tree(new Node[] {
			new MakePlanks(), 
			new FailSafes(), 
			new PayButler(), 
			new Portal() 
	});

	@Override
	public int loop() {
		final Node stateNode = script.state();
		if (Game.getClientState() != Game.INDEX_MAP_LOADED) {
			return 2000;
		}
		if (client != Bot.client()) {
			WidgetCache.purge();
			Bot.context().getEventManager().addListener(this);
			client = Bot.client();
		}
		if (stateNode != null && Game.isLoggedIn()) {
			script.set(stateNode);
			final Node setNode = script.get();
			if (setNode != null) {
				getContainer().submit(setNode);
				setNode.join();
			}

		}
		return 250;
	}

	public void onStart() {
		log.info("Started, VERSION 'WON'T FUCKING WORK'");
		
		Data.startTime = System.currentTimeMillis();
	}

	@Override
	public void onRepaint(Graphics arg0) {
		// TODO Auto-generated method stub
		
	}

}
