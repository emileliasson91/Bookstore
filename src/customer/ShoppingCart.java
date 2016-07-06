package customer;

import java.util.ArrayList;

import item.Item;

public class ShoppingCart {
	private final ArrayList<Item> items;
	
	public ShoppingCart() {
		items = new ArrayList<>();
	}
	
	public boolean add(Item item) {
		return items.add((Item)item);
	}
	
	public boolean remove(int index) {
		items.remove(index);
		return true;
	}
	
	public Item[] getItems() {
		return items.toArray(new Item[items.size()]);
	}

	public void removeByStatus(int[] status, int removeStatus) {
		if (status != null) {
			// Remove backwards so that none of the bought items index are affected
			for (int i = status.length - 1; i >= 0; i--)
				if (status[i] == removeStatus)
					remove(i);	
		}
	}
}
