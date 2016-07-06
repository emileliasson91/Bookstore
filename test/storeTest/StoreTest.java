package storeTest;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import item.Item;
import item.Product;
import item.book.Book;
import item.notebook.Notebook;
import store.Store;

public class StoreTest {

	@Test
	public void storeTestAddBook() {
		Store store = new Store("Book Store");
		Book b1 = new Book("T1", "A1", new BigDecimal(500));
		
		assertTrue(store.add(b1, 5));
		
		Product[] products = store.list(""); // Fetches all books
		assertEquals(1, products.length);
		assertEquals(5, products[0].getQuantity());
		
		products = store.list("T"); // Fetches books that has a 'T' in either its title- or author-string
		assertEquals(1, products.length);
		assertEquals(5, products[0].getQuantity());
	}
	
	@Test
	public void storeTestAddIllegalObjectThroughTypeCasting() {
		Store store = new Store("Book Store");
		
		boolean expectedClassCastExceptionWasCaught = false;
		try {
			store.add((Item)new Object(), 9); // Cast an 'Object' as a 'Book'
			fail("Illegal object passed throught!");
		} catch (ClassCastException e) {
			expectedClassCastExceptionWasCaught = true;
		} finally {
			assertTrue(expectedClassCastExceptionWasCaught);
		}
		
		Product[] products = store.list(""); // Fetches all books
		assertEquals(0, products.length);
	}
	
	@Test
	public void storeTestAddNull() {
		Store store = new Store("Book Store");
		
		assertFalse(store.add(null, 9));
		
		Product[] products = store.list(""); // Fetches all books
		assertEquals(0, products.length);
	}
	
	@Test
	public void storeTestAddSameBookMultipleTimes() {
		Store store = new Store("Book Store");
		Book b1 = new Book("T1", "A1", new BigDecimal(500));
		
		assertTrue(store.add(b1, 5));
		
		Product[] products = store.list(""); // Fetches all books
		assertEquals(1, products.length);
		assertEquals(5, products[0].getQuantity());
		
		assertTrue(store.add(b1, 5));
		products = store.list(""); // Fetches all books
		assertEquals(1, products.length);
		assertEquals(10, products[0].getQuantity());
	}
	
	@Test
	public void storeTestAddSameBookWithDifferentPrices() {
		Store store = new Store("Book Store");
		
		Book b1 = new Book("T1", "A1", new BigDecimal(500));
		Book b2 = new Book("T1", "A1", new BigDecimal(1500));
		Book b3 = new Book("T1", "A1", new BigDecimal(2500));
		
		assertTrue(store.add(b1, 5));	
		
		Product[] products = store.list(""); // Fetches all books
		assertEquals(1, products.length);
		assertEquals(5, products[0].getQuantity());
		
		assertTrue(store.add(b2, 5));
		
		products = store.list(""); // Fetches all books
		assertEquals(2, products.length);
		assertEquals(5, products[0].getQuantity());
		assertEquals(5, products[1].getQuantity());
		
		assertTrue(store.add(b3, 5));
		
		products = store.list(""); // Fetches all books
		assertEquals(3, products.length);
		assertEquals(5, products[0].getQuantity());
		assertEquals(5, products[1].getQuantity());
		assertEquals(5, products[2].getQuantity());
	}
	
	@Test
	public void storeTestPassNullAsSearchString() {
		Store store = new Store("Book Store");
		
		Book b1 = new Book("T1", "A1", new BigDecimal(500));		
		assertTrue(store.add(b1, 5));	
		
		Product[] products = store.list(null); // Fetches all books
		assertEquals(1, products.length);
		assertEquals(5, products[0].getQuantity());
	}
	
	@Test
	public void storeTestAddBooksWithVaryingQuantity() {
		Store store = new Store("Book Store");
		Book b1 = new Book("T1", "A1", new BigDecimal(500));
		
		assertFalse(store.add(b1, -1));
		
		Product[] products = store.list(""); // Fetches all books
		assertEquals(0, products.length);
		
		assertTrue(store.add(b1, 0));
		
		products = store.list(""); // Fetches all books
		assertEquals(1, products.length);
		assertEquals(0, products[0].getQuantity());
	}
	
	@Test
	public void storeTestBuyABookThatIsInStock() {
		Store store = new Store("Book Store");
		Book b1 = new Book("T1", "A1", new BigDecimal(500));
		
		assertTrue(store.add(b1, 10));
		
		Product[] products = store.list(""); // Fetches all books
		assertEquals(1, products.length);
		
		int[] buyStatus = store.buy(b1);
		assertEquals(1, buyStatus.length);
		assertEquals(Store.OK, buyStatus[0]);
		
		products = store.list(""); // Fetches all books
		assertEquals(9, products[0].getQuantity());
	}
	
	@Test
	public void storeTestAttemptToBuyABookThatIsNotInStock() {
		Store store = new Store("Book Store");
		
		// Add a Book to the Book Department
		Book b1 = new Book("T1", "A1", new BigDecimal(500));
		assertTrue(store.add(b1, 0));
		
		// Get a list of all books
		Product[] products = store.list(""); // Fetches all books
		assertEquals(1, products.length);
		assertEquals(0, products[0].getQuantity());
		
		// Attempt to buy book
		int[] buyStatus = store.buy(b1);
		assertEquals(1, buyStatus.length);
		assertEquals(Store.NOT_IN_STOCK, buyStatus[0]);
	}
	
	@Test
	public void storeTestAttemptToBuyABookThatDoesNotExist() {
		Store store = new Store("Book Store");
	
		Book b1 = new Book("T1", "A1", new BigDecimal(500));
		
		// Get a list of all books
		Product[] products = store.list(""); // Fetches all books
		assertEquals(0, products.length);
		
		// Attempt to buy book
		int[] buyStatus = store.buy(b1);
		assertEquals(1, buyStatus.length);
		assertEquals(Store.DOES_NOT_EXIST, buyStatus[0]);
	}
	
	@Test
	public void storeTestExpansionAddAndBuy() {
		Store department = new Store("All-In-One Store");
		
		// Add a Book to the Store
		Book b1 = new Book("T1", "A1", new BigDecimal(500));
		assertTrue(department.add(b1, 1));
		
		// Add a Notebook to the Store
		Notebook nb1 = new Notebook("M", "A4", "Lined", 100, new BigDecimal(50));
		assertTrue(department.add(nb1, 1));
		
		// Get a list of all products
		Product[] products = department.list("");
		assertEquals(2, products.length);
		assertEquals(1, products[0].getQuantity());
		assertEquals(1, products[1].getQuantity());
		
		// Buy the book and the notebook
		int[] buyStatus = department.buy(products[0].getItem(), products[1].getItem());
		assertEquals(Store.OK, buyStatus[0]);
		assertEquals(Store.OK, buyStatus[1]);
		
		// Get a list of all products
		products = department.list("");
		assertEquals(2, products.length);
		assertEquals(0, products[0].getQuantity());
		assertEquals(0, products[1].getQuantity());
	}

}
