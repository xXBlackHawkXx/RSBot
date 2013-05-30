package hawksoakdoors.main;

import hawksoakdoors.nodes.*;
import hawksoakdoors.utils.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.powerbot.core.Bot;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.WidgetCache;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.client.Client;
import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;

@Manifest(
		name = "Hawks Oak Doors",
		authors = "xXBlackHawkXx",
		description = "Builds oak doors with a demon butler.",
		version = 1.971,
		website = "http://www.powerbot.org/community/topic/683133-hawks-oak-doors/"
		)

public class HawksOakDoors extends ActiveScript implements PaintListener, MouseListener, MessageListener {

	private Client client = Bot.client();
	
	private Tree script = new Tree(new Node[] {	
			new Pay(),			
			new Butler(),
			new Destroy(),
			new Construct()
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
		if(stateNode != null && Game.isLoggedIn()) {
			script.set(stateNode);
			final Node setNode = script.get();
			if(setNode != null) {
				getContainer().submit(setNode);
				setNode.join();
			}
	            
		}
		return 250;
	}
	
	@Override
	public void onStart() {
		
		Mouse.setSpeed(Mouse.Speed.VERY_FAST);
		
		Variables.startExp = Skills.getExperience(22);
		Variables.startLvl = Skills.getLevel(22);
		Variables.startTime = System.currentTimeMillis();
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		if(Variables.hideRec.contains(p)){
			Variables.showPaint = !Variables.showPaint;
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {}

	
	@Override
	public void mouseExited(MouseEvent e) {}

	
	@Override
	public void mousePressed(MouseEvent e) {}

	
	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void messageReceived(MessageEvent e) {
		String txt = e.getMessage().toLowerCase();
		if (txt.contains("you can't reach that.")) {
			Destroy.closeDoor();
		}
		
	}
	
	@Override
	public void onRepaint(Graphics g) {

		long runtime = System.currentTimeMillis() - Variables.startTime;
		String time = Time.format(runtime);
		int expGained = Skills.getExperience(22) - Variables.startExp;
		int expHour = (int) ((expGained) * 3600D / ((System.currentTimeMillis() / 1000) - (Variables.startTime/ 1000)));
		int toLevel = Skills.getExperienceToLevel(Skills.CONSTRUCTION, Skills.getLevel(Skills.CONSTRUCTION) + 1);
		int level = Skills.getRealLevel(Skills.CONSTRUCTION);
		int doorsMadeHour = (int) ((Variables.doorsMade) * 3600D / ((System.currentTimeMillis() / 1000) - (Variables.startTime/ 1000)));
		
		/*Graphics2D g2d = (Graphics2D) g1;
		
		g2d.setColor(Color.WHITE);
        g2d.fill(new Rectangle(Mouse.getX() + 1, Mouse.getY() - 4, 2, 15));
    	g2d.fill(new Rectangle(Mouse.getX() - 6, Mouse.getY() + 2, 16, 2));
			
		Graphics2D g = (Graphics2D)g1;
			
		g.setColor(Variables.color1);
		g.fillRect(319, 193, 195, 142);
		g.setColor(Variables.color2);
		g.setStroke(Variables.stroke1);
		g.drawRect(319, 193, 195, 142);
		g.setFont(Variables.font1);
		g.drawString("Hawk's Oak Doors", 335, 220);
		g.setStroke(Variables.stroke2);
		g.drawLine(338, 229, 469, 229);
		g.setFont(Variables.font2);
		g.drawString("Runtime: " + time, 335, 249);
		g.drawString("Current Lvl: " + Skills.getLevel(22) + " (" + (Skills.getLevel(22) - Variables.startLvl) + ")", 335, 264);
		g.drawString("Exp Gained: " + expGained, 335, 279);
		g.drawString("Exp to Next Lvl: " + Skills.getExperienceToLevel(Skills.CONSTRUCTION, Skills.getLevel(Skills.CONSTRUCTION) + 1) + " (" + (Skills.getLevel(Skills.CONSTRUCTION)  + 1)+ ")", 335, 294);
		g.drawString("Exp/Hour: " + (int) ((expGained) * 3600D / ((System.currentTimeMillis() / 1000) - (Variables.startTime/ 1000))), 335, 309);
		g.setFont(Variables.font3);
		g.drawString("Status: " + Variables.status, 335, 327);
		g.drawString("v.1.97", 475, 327);*/
		
Graphics g2 = (Graphics2D)g;
		
		if(Variables.showPaint){
			
			g.setColor(Color.BLACK);
			g.fillRect(6, 394, 507, 129); //background
			g.fillRect(3, 365, 47, 23); //hide/show
			
			g.setColor(Color.ORANGE);
			Point p = Mouse.getLocation();
			g.drawLine(0, p.y, 766, p.y);
			g.drawLine(p.x, 0, p.x, 554);
			
			g.setFont(new Font("Arial", 0, 14));
			g.drawString("Hide", 12, 381);
			
			g.setFont(new Font("Arial", 0, 17));
			g.drawString("Hawks Oak Doors", 190, 416);
			g.setFont(new Font("Arial", 0, 14));

			g.drawString("Level: " + level + " (" + (level - Variables.startLvl) + ")", 65, 438);
			g.drawString("Exp Gained: " + expGained, 65, 455);
			g.drawString("Exp/H: " + expHour, 65, 472);
			g.drawString("Exp TNL: " + toLevel, 65, 489);
			g.drawString("TTL: COMING SOON", 65, 506);
			
			g.drawString("Doors Made: " + Variables.doorsMade, 320, 450);
			g.drawString("Doors Made/H: " + doorsMadeHour, 320, 467);		
			g.drawString("Runtime: " + time, 320, 484);
			g.drawString("Status: " + Variables.status, 320, 501);
			
			g.setFont(new Font("Arial", 0, 10));
			g.drawString("v1.971", 467, 517);

		}
		else {
			g2.setColor(Color.BLACK);
			g2.fillRect(3, 365, 47, 23); //hide/show
			g2.fillRect(8, 510, 133, 12); //name cover
			g2.setColor(Color.ORANGE);
			g2.setFont(new Font("Arial", 0, 14));
			g2.drawString("Show", 9, 381);
			
			Point p = Mouse.getLocation();
			g2.drawLine(0, p.y, 766, p.y);
			g2.drawLine(p.x, 0, p.x, 554);
		}
	}
}