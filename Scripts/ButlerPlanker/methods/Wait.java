package hawksbutlerplanker.methods;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class Wait {

	public static void waitForInventChange() {
        final int start = Inventory.getCount();
        waitForCondition(new Condition() {
            @Override
            public boolean validate() {
                return Inventory.getCount() != start;
            }
        }, 5000);
    }

    public static boolean waitForLocChange() {
        final Tile start = Players.getLocal().getLocation();
        return waitForCondition(new Condition() {
            @Override
            public boolean validate() {
                return Calculations.distanceTo(start) >= 4;
            }
        }, 5000);
    }

    public static boolean waitForAnimationChange() {
        final int start = Players.getLocal().getAnimation();
        return waitForCondition(new Condition() {
            @Override
            public boolean validate() {
                return Players.getLocal().getAnimation() != start;
            }
        }, 5000);
    }

    public static boolean waitForWidget(final WidgetChild widget) {
        return waitForCondition(new Condition() {
            @Override
            public boolean validate() {
                return widget.isOnScreen();
            }
        }, 5000);
    }

    public static boolean waitForCondition(Condition c, int timeout) {
        Timer t = new Timer(timeout);
        boolean validated;
        while (!(validated = c.validate()) && t.isRunning()) {
            Task.sleep(10);
        }
        return validated;
    }

    public interface Condition {
        public boolean validate();
    }
	
}
