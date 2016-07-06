package item.book;

import java.math.BigDecimal;

import item.Item;

public class Book implements Item {
	private final String title;
	private final String author;
	private final BigDecimal price;
	
	public Book(String title, String author, BigDecimal price) {
		this.title = title;
		this.author = author;
		this.price = price;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}

	@Override
	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public boolean match(Object obj) {
		if (obj instanceof String) {
			String matchString = ((String)obj).toLowerCase();
			return title.toLowerCase().contains(matchString)
					|| author.toLowerCase().contains(matchString)
					|| "book".contains(matchString);
		}
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Book) {
		Book otherBook = (Book) obj;
		return title.equals(otherBook.getTitle())
				&& author.equals(otherBook.getAuthor())
				&& price.doubleValue() == otherBook.getPrice().doubleValue();
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Type: BOOK"
				+ "\nTitle: " + title
				+  "\nAuthor: " + author
				+  "\nPrice: " + price;
	}
}
