package item;

import java.math.BigDecimal;

public interface Item {
	public BigDecimal getPrice();
	public boolean match(Object obj);
}
