package hawksgranitesplitter.main;

import hawksgranitesplitter.nodes.*;
import hawksgranitesplitter.utils.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.powerbot.core.Bot;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.widget.WidgetCache;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.client.Client;

@Manifest(
		authors = "xXBlackHawkXx", 
		description = "Splits blocks of granite at any bank", 
		name = "Hawks Granite Splitter", 
		version = 0.1
		)

public class HawksGraniteSplitter extends ActiveScript implements PaintListener, MouseListener {

	private Client client = Bot.client();

	private final Tree script = new Tree(new Node[] {			
			new Split() ,
			new Banking(), 		
			new Failsafes()
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
		
		PriceWrapper priceWrapper = new PriceWrapper();
		
		Variables.startTime = System.currentTimeMillis();
		
		if (Variables.fiveKgGranitePrice == 0) {			
			Variables.fiveKgGranitePrice = priceWrapper.getPrice(Variables.fiveKgGranite);
			Variables.twoKgGranitePrice = priceWrapper.getPrice(Variables.halfKgGranite);
			Variables.halfKgGranitePrice = priceWrapper.getPrice(Variables.halfKgGranite);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		if (Variables.hideRec.contains(p)) {
			Variables.showPaint = !Variables.showPaint;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
	

	@Override
	public void onRepaint(Graphics g) {
		
		long runtime = System.currentTimeMillis() - Variables.startTime;
		String time = Time.format(runtime);

		Graphics g2 = (Graphics2D) g;

		if (Variables.showPaint) {

			g.setColor(Color.BLACK);
			g.fillRect(6, 394, 507, 129); // background
			g.fillRect(3, 365, 47, 23); // hide/show

			g.setColor(Color.CYAN);
			Point p = Mouse.getLocation();
			g.drawLine(0, p.y, 766, p.y);
			g.drawLine(p.x, 0, p.x, 554);

			g.setFont(new Font("Arial", 0, 14));
			g.drawString("Hide", 12, 381);

			g.setFont(new Font("Arial", 0, 17));
			g.drawString("Hawks Granite Splitter", 190, 416);
			g.setFont(new Font("Arial", 0, 14));

			g.drawString("Money made: ", 65, 458);
			g.drawString("Per hour: ", 65, 475);

			g.drawString("Runtime: " + time, 320, 458);
			g.drawString("Status: " + Variables.status, 320, 475);

			g.setFont(new Font("Arial", 0, 10));
			g.drawString("v0.1", 467, 517);

		} else {
			g2.setColor(Color.BLACK);
			g2.fillRect(3, 365, 47, 23); // hide/show
			g2.fillRect(8, 510, 133, 12); // name cover
			g2.setColor(Color.CYAN);
			g2.setFont(new Font("Arial", 0, 14));
			g2.drawString("Show", 9, 381);

			Point p = Mouse.getLocation();
			g2.drawLine(0, p.y, 766, p.y);
			g2.drawLine(p.x, 0, p.x, 554);
		}
	}

}
