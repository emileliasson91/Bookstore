package store;

import item.Item;
import item.Product;

public interface ProductList {
	public Product[] list(String searchString);
	public boolean add(Item item, int quantity);
	public int[] buy(Item... items);
}
