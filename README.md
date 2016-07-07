# Bookstore

Bookstore
To have the bookstore be extended, all items that can be added to the store implements the 'Item'-interface. Also, the Store implements the 'ProductList'-interface instead of the 'BookList'-interface to make it more generic.

The 'ProductList'-interface retuns an array of products (an item of type Item and a quantity of type integer), unlike the 'BookList'-interface that only returned an array of books.

No client-server setup was implemented since it wasen't requested.

public interface BookList { 
public Book[] list(String searchString);
public boolean add(Book book, int quantity);
public int[] buy(Book... books);
}

public interface ProductList {
public Product[] list(String searchString);
public boolean add(Item item, int quantity);
public int[] buy(Item... items);
}

public class Product {
private final Item item;
private final int quantity;
...
}

public class ShoppingCart {
private final ArrayList items;
...
}

View the diagram.png for a better overview of the system.
