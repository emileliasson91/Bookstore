package store;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import item.Item;
import item.Product;

public class Store implements ProductList {
	
	public final static int OK = 0;
	public final static int NOT_IN_STOCK = 1;
	public final static int DOES_NOT_EXIST = 2;
	
	private final String name;
	private final ArrayList<Item> items;
	private final ArrayList<Integer> quantities;
	
	public Store(String name) {
		this.name = name;
		items = new ArrayList<>();
		quantities = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}

	@Override
	public Product[] list(String searchString) {
		if (searchString == null)
			searchString = "";
		ArrayList<Product> matchingProducts = new ArrayList<>();
		for (int i = 0; i < items.size(); i++) {
			if (((Item)items.get(i)).match(searchString))
				matchingProducts.add(new Product(items.get(i), quantities.get(i)));	
		}
		return matchingProducts.toArray(new Product[matchingProducts.size()]);
	}

	@Override
	public boolean add(Item item, int quantity) {
		if (item == null || quantity < 0)
			return false;
		int index = -1;
		if ((index = items.indexOf(item)) != -1)
			quantities.set(index, quantities.get(index) + quantity);
		else  {
			items.add(item);
			quantities.add(quantity);
		}
		return true;
	}
	
	private void buyIfInStock(int[] buyStatus, int i, Item item) {
		int index = -1;
		if ((index = items.indexOf(item)) != -1) {
			if (quantities.get(index).intValue() <= 0)
				buyStatus[i] = NOT_IN_STOCK;
			else {
				quantities.set(index, quantities.get(index) - 1);
				buyStatus[i] = OK;
			}		
		} else 
			buyStatus[i] = DOES_NOT_EXIST;
	}

	@Override
	public int[] buy(Item... items) {
		if (items == null)
			return new int[0];
		int[] buyStatus = new int[items.length];
		AtomicInteger i = new AtomicInteger(0);
		Arrays.stream(items).forEach(item -> buyIfInStock(buyStatus, i.getAndIncrement(), item));
		return buyStatus;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Store) {
			Store other = (Store) obj;
			return name.equals(other.getName());
		}
		return false;
	}
	
	@Override
	public String toString() {
		AtomicReference<String> result = new AtomicReference<String>("Name: " + name + "\n");
		Arrays.stream(list("")).forEach(product -> result.set(result.get() + "\n" + product.toString()));
		return result.toString();
	}


}
