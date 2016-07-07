# Bookstore

Bookstore
To have the bookstore be extended, all items that can be added to the store implements the 'Item'-interface. Also, the Store implements the 'ProductList'-interface instead of the 'BookList'-interface to make it more generic.

The 'ProductList'-interface retuns an array of products (an item of type Item and a quantity of type integer), unlike the 'BookList'-interface that only returned an array of books.

No client-server setup was implemented since it wasen't requested.

public interface BookList { </br>
public Book[] list(String searchString); </br>
public boolean add(Book book, int quantity); </br>
public int[] buy(Book... books); </br>
} </br>

public interface ProductList { </br>
public Product[] list(String searchString); </br>
public boolean add(Item item, int quantity); </br>
public int[] buy(Item... items); </br>
} </br>

public class Product { </br>
private final Item item; </br>
private final int quantity; </br>
... </br>
} </br>

public class ShoppingCart { </br>
private final ArrayList items; </br>
... </br>
} </br>

View the diagram.png for a better overview of the system.
