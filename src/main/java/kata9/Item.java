package kata9;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Item {
	
	public static final Map<String, Long> SKU_TO_PRICE_MAPPING = new HashMap<String, Long>();
	
	private final UUID uid;
	
	static {
		//Add some base prices
		SKU_TO_PRICE_MAPPING.put("A", 50l);
		SKU_TO_PRICE_MAPPING.put("B", 30l);
		SKU_TO_PRICE_MAPPING.put("C", 20l);
		SKU_TO_PRICE_MAPPING.put("D", 15l);
	}
	
	/**
	 * The sku of this product
	 */
	public final String sku;
	
	/**
	 * Price in sub units (let's say cent)
	 */
	public final long price;
	
	
	public Item(String sku, long price) {
		this.uid = UUID.randomUUID();
		this.sku = sku;
		this.price = price;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}




	@Override
	public String toString() {
		return "Item [sku=" + sku + ", price=" + price + "]";
	}
}
