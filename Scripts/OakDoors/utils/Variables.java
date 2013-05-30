package hawksoakdoors.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class Variables {
	
	public static final Rectangle hideRec = new Rectangle(4, 366, 45, 21);
	
	public static boolean showPaint = true;
	
	public static final Color color1 = new Color(0, 0, 0);
	public static final Color color2 = new Color(255, 153, 0);
	public static final BasicStroke stroke1 = new BasicStroke(5);
	public static final BasicStroke stroke2 = new BasicStroke(2);
	public static final Font font1 = new Font("Verdana", 1, 16);
	public static final Font font2 = new Font("Verdana", 0, 12);
	public static final Font font3 = new Font("Verdana", 1, 12);
	
	
	public static final int oakPlank = 8778;
	public static final int doorSpot = 15327;
	public static final int oakDoor = 13345;
	public static final int oakDoorOpen = 13351;
	public static final int DemonButler = 4243;
	public static final int butler = 4241;
	
	public static int counter;
	public static int butlerCounter = 0;
	
	public static int doorsMade = 0;
	
	public static final int[] portal = {15477, 15478, 15479, 15480, 15481, 15482};
	public static WidgetChild widgetPortalEntry = Widgets.get(1188, 4);
	
	public static int startExp, startLvl;
	public static long startTime;
	public static String status = "";

}
