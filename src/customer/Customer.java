package customer;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import item.Item;
import item.Product;
import store.Store;

public class Customer {
	private final Scanner scanner;
	private final ShoppingCart shoppingCart;
	private final Store store;
	
	public Customer(Store store) {
		scanner = new Scanner(System.in);
		shoppingCart = new ShoppingCart();
		this.store = store;
	}
	
	public void mainMenu() {
		while (true) {			
			// Print the menu
			System.out.println("***MAIN MENU***");
			System.out.println("1. View Store");
			System.out.println("9. View Your Shopping Cart");
			System.out.println("0. Exit");
			System.out.print("Enter option: ");
			
			int option = -1;
			try {
				option = scanner.nextInt();
			} catch(InputMismatchException e) {
				option = -1;
			} finally {
				scanner.nextLine(); // consume leftover newline
			}
			
			if (option == 0)
				break;
			
			switch (option) {
			case 1:
				storeMenu();
				break;
			case 9:
				shoppingCartMenu();
				break;
			default:
				System.out.println("Invalid option!");	
			}
		}
	}
	
	private void storeMenu() {
		while (true) {
			// Print the menu
			System.out.println("***STORE MENU***");
			System.out.println("1. Search For Products");
			System.out.println("2. List All Products");
			System.out.println("9. View Your Shopping Cart");
			System.out.println("0. Go Back");
			System.out.print("Enter option: ");
			
			int option = -1;
			try {
				option = scanner.nextInt();
			} catch(InputMismatchException e) {
				option = -1;
			} finally {
				scanner.nextLine(); // consume leftover newline
			}
			
			if (option == 0)
				break;
			
			switch (option) {
			case 1:
				selectionMenu(productSearch(store));
				break;
			case 2:
				selectionMenu(store.list(null));
				break;
			case 9:
				shoppingCartMenu();
				break;
			default:
				System.out.println("Invalid option!");
			}
		}
	}
	
	private Product[] productSearch(Store store) {
		System.out.print("Enter searchstring: ");
		String searchstring = scanner.nextLine();
		return store.list(searchstring);
	}
	
	private void selectionMenu(Product[] products) {	
		while (true) {
			System.out.println("---PRODUCT SELECTION LIST---");
			listProducts(products);
			
			System.out.print("Enter INDEX of the desired book you would like to add to your shopping cart (or '0' to go back): ");
			int option = -1;
			try {
				option = scanner.nextInt();
				scanner.nextLine(); // consume leftover newline
			} catch(InputMismatchException e) {
				scanner.nextLine(); // consume leftover newline
				System.out.println("Invalid index!");
				continue;
			}
			
			if (option == 0)
				break;
			
			if (option > products.length) {
				System.out.println("Invalid index!");
				continue;
			}
			
			if (shoppingCart.add(products[option-1].getItem())) {
				System.out.println("Item was added to the shopping cart!");
				System.out.println("Shopping cart has been updated!");
			} else
				System.out.println("Item could not be added to the shopping cart!");
		}
	}
	
	private void listProducts(Product[] products) {
		AtomicInteger indexCounter = new AtomicInteger(1);
		Arrays.stream(products).forEach(product -> System.out.println("Index : " + indexCounter.getAndIncrement() + "\n" + product.toString() + "\n"));
	}
	
	private void shoppingCartMenu() {
		while (true) {
			System.out.println("***SHOPPING CART MENU***");
			System.out.println("1. Remove Books");
			System.out.println("2. Buy Books");
			System.out.println("0. Go back");
			System.out.print("Enter option: ");
			
			int option = -1;
			try {
				option = scanner.nextInt();
			} catch(InputMismatchException e) {
				option = -1;
			} finally {
				scanner.nextLine(); // consume leftover newline
			}
			
			if (option == 0)
				break;
			
			switch (option) {
			case 1:
				shoppingCartRemoveItemsMenu();
				break;
			case 2:
				shoppingCartBuyItems();
				break;
			default:
				System.out.println("Invalid option!");
			}
		}
	}
	
	private void shoppingCartRemoveItemsMenu() {
		while (true) {
			System.out.println("***SHOPPING CART REMOVE ITEMS MENU***");
			Item[] items = shoppingCart.getItems();
			listItems(items);
			
			System.out.print("Enter INDEX of the desired book you would like to remove from your shopping cart (or '0' to go back): ");
			int option = -1;
			try {
				option = scanner.nextInt();
				scanner.nextLine(); // consume leftover newline
			} catch(InputMismatchException e) {
				scanner.nextLine(); // consume leftover newline
				System.out.println("Invalid index!");
				continue;
			}
			
			if (option == 0)
				break;
			
			if (option > items.length) {
				System.out.println("Invalid index!");
				continue;
			}
			
			if (shoppingCart.remove(option-1)) {
				System.out.println("Item was removed!");
				System.out.println("Shopping cart has been updated!");
			} else
				System.out.println("Item was not removed!");
		}
	}
	
	private void listItems(Item[] items) {
		AtomicInteger indexCounter = new AtomicInteger(1);
		Arrays.stream(items).forEach(item -> System.out.println("Index : " + indexCounter.getAndIncrement() + "\n" + item.toString() + "\n"));
	}

	private void printPurchaseStatus(int status, Item item, BigDecimal totalPrice) {
		
		if (status == Store.DOES_NOT_EXIST) {
			System.out.println(item.toString() + " ---DOES NOT EXIST");
		}
		
		else if (status == Store.NOT_IN_STOCK) {
			System.out.println(item.toString() + " ---NOT IN STOCK");
		}
		
		else if (status == Store.OK) {
			System.out.println(item.toString() + " ---OK");
			totalPrice = new BigDecimal(totalPrice.doubleValue() + item.getPrice().doubleValue());
		}
	}
	
	private void shoppingCartBuyItems() {
		Item[] items = shoppingCart.getItems();
		int[] buyStatus = store.buy(items);
		
		System.out.println("***PURCHASE MADE***");
		BigDecimal totalPrice = new BigDecimal(0);
		AtomicInteger i = new AtomicInteger(0);
		Arrays.stream(buyStatus).forEach(status -> printPurchaseStatus(status, items[i.getAndIncrement()], totalPrice));
		System.out.println("Total price for purchase was: " + totalPrice.doubleValue());
		
		// Remove successfully bought items from the shopping cart
		shoppingCart.removeByStatus(buyStatus, Store.OK);
		System.out.println("Shopping Cart has been updated!");
		System.out.println("Successfully bought items has been removed from the shopping cart!");
	}
}
