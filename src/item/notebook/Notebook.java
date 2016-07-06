package item.notebook;

import java.math.BigDecimal;

import item.Item;

public class Notebook implements Item {
	
	private final String manufacturer;
	private final String pageSize;
	private final String pageType;
	private final int pageCount;
	private final BigDecimal price;
	
	public Notebook(String manufacturer, String pageSize, String pageType, int pageCount, BigDecimal price) {
		this.manufacturer = manufacturer;
		this.pageSize = pageSize;
		this.pageType = pageType;
		this.pageCount = pageCount;
		this.price = price;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}
	
	public String getPageSize() {
		return pageSize;
	}
	
	public String getPageType() {
		return pageType;
	}
	
	public int getPageCount() {
		return pageCount;
	}

	@Override
	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public boolean match(Object obj) {
		if (obj instanceof String) {
			String matchString = ((String)obj).toLowerCase();
			return manufacturer.toLowerCase().contains(matchString)
					|| "notebook".contains(matchString);
		}
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Notebook) {
			Notebook other = (Notebook) obj;
			return manufacturer.equals(other.getManufacturer())
					&& pageSize.equals(other.getPageSize())
					&& pageType.equals(other.getPageType())
					&& pageCount == other.getPageCount()
					&& price.doubleValue() == other.getPrice().doubleValue();
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Type: NOTEBOOK"
				+ "\nManufacturer: " + manufacturer
				+ "\nPage size: " + pageSize
				+ "\nPage type: " + pageType
				+ "\nPage count: " + pageCount;
	}

}
