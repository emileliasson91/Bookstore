package item;

public class Product {
	private final Item item;
	private final int quantity;
	
	public Product(Item item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}
	
	public Item getItem() {
		return item;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	@Override
	public boolean equals(Object obj) {
		return false;
	}
	
	@Override
	public String toString() {
		return item.toString() + "\nQuantity: " + quantity;
	}

}
