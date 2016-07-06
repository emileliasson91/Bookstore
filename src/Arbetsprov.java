import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import customer.Customer;
import item.book.Book;
import item.notebook.Notebook;
import store.Store;

public class Arbetsprov {
	
	public static void main(String[] args) {
		// Create a store
		Store store = new Store("SomeName");
		
		URL url = null;
		InputStream inputStream = null;
		DataInputStream dataInputStream = null;
		
		try {
			url = new URL("http://www.contribe.se/bookstoredata/bookstoredata.txt");
			
			inputStream = url.openStream();
			dataInputStream = new DataInputStream(new BufferedInputStream(inputStream));
			
			String s = null;
			while ((s = dataInputStream.readLine()) != null)
				makeBookAndAdd(store, s);
			
		} catch (MalformedURLException e) {
			System.out.println("URL is invalid!");
			System.out.println("Books could not be featched!");
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Something went wrong...");
			//e.printStackTrace();
		} finally {
			try {
				if (dataInputStream != null)
					dataInputStream.close();
			} catch (IOException e) {
				//e.printStackTrace();
			}
			
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
		
		/*** Remove comment and extend the store to include some hard-coded notebooks ***/
		//extendStore(store);
		

		// Give new customer a reference to the bookstore
		//  and proceed to main menu
		new Customer(store).mainMenu();	
	}
	
	public static void makeBookAndAdd(Store store, String string) {
		try {
			String[] params = string.split(";"); // split parameter into multiple parameters of type string
			params[2] = params[2].replaceAll(",", ""); // remove any commas from price-string
			params[3] = params[3].replaceAll(",", ""); // remove any commas from quantity-string
			
			System.out.print("Attempting to add:: ");
			Arrays.stream(params).forEach(param -> System.out.print(param + " "));
			System.out.println();
			
			// Attempt to add new book
			store.add(new Book(params[0], params[1], new BigDecimal(Double.parseDouble(params[2]))), Integer.parseInt(params[3]));
		} catch (NullPointerException e) {
			System.out.println("null!");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Parameters are missing!");
		} catch (NumberFormatException e) {
			System.out.println("Parameter is not a number!");
		}
	}
	
	public static void extendStore(Store store) {
		System.out.println("Extening store to include notebooks!");
		Notebook nb1 = new Notebook("Adidas", "A5", "Blank", 100, new BigDecimal(50));
		Notebook nb2 = new Notebook("Rhodia", "A3", "Lined", 200, new BigDecimal(150));
		Notebook nb3 = new Notebook("Noto", "A4", "Blank", 200, new BigDecimal(125));
		Notebook nb4 = new Notebook("Pop", "A6", "Lined", 200, new BigDecimal(190));
		Notebook nb5 = new Notebook("Cuppy cakes", "A5", "Blank", 50, new BigDecimal(80));
		
		store.add(nb1, 1);
		store.add(nb2, 2);
		store.add(nb3, 0);
		store.add(nb4, 1);
		store.add(nb5, 7);
		
		System.out.println("Notebooks added!");
	}

}
