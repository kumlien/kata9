package rules;

import java.util.ArrayList;
import java.util.List;

import kata9.Item;


/**
 * A base rule...
 * 
 * Simplification: we don't allow stacking of rules.
 * 
 */
public abstract class BaseRule {
	
	protected final String sku;
	protected final String name;
	protected final int neededItems;
	
	public BaseRule(String name, String sku, int neededItems) {
		this.name = name;
		this.sku = sku;
		this.neededItems = neededItems;
	}
	
	/**
	 * Will consume the number of items used by this rule (if any).
	 * 
	 * @param items
	 * @return the number of items consumed
	 */
	public int consume(List<Item> cart) {
		List<Item> matchingItems = new ArrayList<Item>();
		
		for(Item item : cart) {
			System.out.println("BaseRule comparing sku " + sku + " with " + item.sku);
			if(item.sku.equals(sku)) {
				matchingItems.add(item);
			}
			if(matchingItems.size() == neededItems) {
				System.out.println(name + " applies, removing " + neededItems + " items from the cart");
				cart.removeAll(matchingItems);
				return neededItems;
			}
		}
		return 0;
	}

	public abstract String getName();
	
	
	/**
	 * Used to get the discount for this rule
	 * 
	 * @param items The full list of items in the cart
	 */
	public abstract double getDiscount();
	
	public String toString() {
		return getName();
	}
	
}
