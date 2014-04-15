package rules;

import java.util.ArrayList;
import java.util.List;

import kata9.Item;

/**
 * Simple amount based rule. IF you buy 'neededItems' of 'sku's 
 * then you get 'discount' to the total receipt amount.
 * 
 * 
 * @author svante
 */
public class AmountBasedRule extends BaseRule {
	
	public final long discount;
	
	public AmountBasedRule(String name, String sku, long discount, int neededItems) {
		super(name, sku, neededItems);
		this.discount = discount;
	}
	
	/**
	 * Check if the cart contains the number of needed items for this rule
	 * to apply. If so, remove the items from the cart.
	 * 
	 */
	@Override
	public int consume(List<Item> cart) {
		List<Item> matchingItems = new ArrayList<Item>();
		
		for(Item item : cart) {
			System.out.println("AmountBasedRule comparing sku " + sku + " with " + item.sku);
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

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getDiscount() {
		return discount;
	}

}
