package hawksgranitesplitter.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.powerbot.game.api.wrappers.node.GroundItem;
import org.powerbot.game.api.wrappers.node.Item;

public class PriceWrapper {
	 private HashMap<Integer, Integer> priceMap = new HashMap<>();

	    public PriceWrapper(final int... ids) {
	        add(ids);
	    }

	    public void add(final int... itemIds) {
	        if (itemIds.length < 1) {
	            return;
	        }
	        List<Integer> nonCachedIds = new ArrayList<>();
	        for (int i : itemIds) {
	            if (!priceMap.containsKey(i)) {
	                nonCachedIds.add(i);
	            }
	        }
	        priceMap.putAll(lookup(nonCachedIds.toArray(new Integer[nonCachedIds.size()])));
	    }

	    public void add(final Item... items) {
	        final int[] ids = new int[items.length];
	        for (int i = 0; i < items.length; i++) {
	            ids[i] = items[i].getId();
	        }
	        add(ids);
	    }

	    public void add(final GroundItem... groundItems) {
	        final Item[] items = new Item[groundItems.length];
	        for (int i = 0; i < groundItems.length; i++) {
	            items[i] = groundItems[i].getGroundItem();
	        }
	        add(items);
	    }

	    public int getPrice(final int id) {
	        if (!priceMap.containsKey(id)) {
	            add(id);
	        }
	        return priceMap.get(id);
	    }

	    private HashMap<Integer, Integer> lookup(final Integer... ids) {
	        HashMap<Integer, Integer> map = new HashMap<>();
	        String add = "http://scriptwith.us/api/?return=text&item=";
	        for (int i = 0; i < ids.length; i++) {
	            add += ((i == ids.length - 1) ? ids[i] : ids[i] + ",");
	        }
	        try (final BufferedReader in = new BufferedReader(new InputStreamReader(
	                new URL(add).openConnection().getInputStream()))) {
	            final String line = in.readLine();
	            for (String s : line.split("[;]")) {
	                map.put(Integer.parseInt(s.split("[:]")[0]), Integer.parseInt(s.split("[:]")[1]));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return map;
	    }
}
