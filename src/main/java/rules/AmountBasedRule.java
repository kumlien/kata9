package rules;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	public final String sku;
	
	public final int neededItems;
	
	public static final Target target = Target.TOTAL;
	
	public final String name;
	
	
	public AmountBasedRule(String name, String sku, long discount, int neededItems) {
		this.name = name;
		this.sku = sku;
		this.discount = discount;
		this.neededItems = neededItems;
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
				System.out.println("AmountBasedRule applies, removing " + neededItems + " items from the cart");
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
	public long getDiscount() {
		return discount;
	}

}
