package shoppingCartTest;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import customer.ShoppingCart;
import item.book.Book;
import item.notebook.Notebook;

public class ShoppingCartTest {

	@Test
	public void shoppingCartTestAddBook() {
		ShoppingCart shoppingCart = new ShoppingCart();
		
		Book book = new Book("T", "A", new BigDecimal(0));
		shoppingCart.add(book);
		
		assertEquals(1, shoppingCart.getItems().length);
	}
	
	@Test
	public void shoppingCartTestAddNotebook() {
		ShoppingCart shoppingCart = new ShoppingCart();
		
		Notebook notebook = new Notebook("Adidas", "A5", "Blank", 100, new BigDecimal(50));
		shoppingCart.add(notebook);
		
		assertEquals(1, shoppingCart.getItems().length);
	}
	
	@Test
	public void shoppingCartTestAddBookAndNotebook() {
		ShoppingCart shoppingCart = new ShoppingCart();
		
		Book book = new Book("T", "A", new BigDecimal(0));
		shoppingCart.add(book);
		
		assertEquals(1, shoppingCart.getItems().length);
		
		Notebook notebook = new Notebook("Adidas", "A5", "Blank", 100, new BigDecimal(50));
		shoppingCart.add(notebook);
		
		assertEquals(2, shoppingCart.getItems().length);
	}
	
	

}
