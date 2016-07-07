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
		
		// Add one book
		assertTrue(shoppingCart.add(book));
		
		// Check so that there is one book in the shopping cart
		assertEquals(1, shoppingCart.getItems().length);
	}
	
	@Test
	public void shoppingCartTestAddNotebook() {
		ShoppingCart shoppingCart = new ShoppingCart();
		
		Notebook notebook = new Notebook("Adidas", "A5", "Blank", 100, new BigDecimal(50));
		
		// Add one notebook
		assertTrue(shoppingCart.add(notebook));
		
		// Check so that there is one notebook in the shopping cart
		assertEquals(1, shoppingCart.getItems().length);
	}
	
	@Test
	public void shoppingCartTestAddBookAndNotebook() {
		ShoppingCart shoppingCart = new ShoppingCart();
		
		Book book = new Book("T", "A", new BigDecimal(0));
		
		// Add one book
		assertTrue(shoppingCart.add(book));
		
		// Check so that there is one item in the shopping cart
		assertEquals(1, shoppingCart.getItems().length);
		
		Notebook notebook = new Notebook("Adidas", "A5", "Blank", 100, new BigDecimal(50));
		
		// Add one notebook
		shoppingCart.add(notebook);
		
		// Check so that there are two items in the shopping cart
		assertEquals(2, shoppingCart.getItems().length);
	}
	
	
	@Test
	public void shoppingCartTestAddBookAndRemove() {
		ShoppingCart shoppingCart = new ShoppingCart();
		
		Book book = new Book("T", "A", new BigDecimal(0));
		shoppingCart.add(book);
		
		// Check that book was added
		assertEquals(1, shoppingCart.getItems().length);
		
		// Remove book
		assertTrue(shoppingCart.remove(0));
		
		// Check so that the shopping cart is empty
		assertEquals(0, shoppingCart.getItems().length);
	}
	
	@Test
	public void shoppingCartAttemptRemovalOnEmptyShoppingCart() {
		ShoppingCart shoppingCart = new ShoppingCart();
		
		// attempt removal
		assertFalse(shoppingCart.remove(0));
		
		// attempt removal
		assertFalse(shoppingCart.remove(-1));
		
		// attempt removal
		assertFalse(shoppingCart.remove(1));
	}
	

}
