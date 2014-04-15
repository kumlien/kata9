package rules;

import java.util.ArrayList;
import java.util.List;

import kata9.Item;

/**
 * Simple item based rule which works like:
 * 
 * 	If you buy x number of Item y You get z numbers of the SAME item for 'free' 
 * 
 * @author svante
 */
public class ItemBasedRule extends BaseRule {
	
	public final String name;
	final String sku;
	final Integer numberOfRequiredItems;
	final Integer numberOfFreeItems;
	
	public ItemBasedRule(String name, String sku, Integer numberOfRequiredItems, Integer numberOfFreeItems) {
		if(numberOfRequiredItems < numberOfFreeItems) {
			throw new IllegalArgumentException("The number of required items must be higher than the number of freebies");
		}
		this.name = name;
		this.sku = sku;
		this.numberOfRequiredItems = numberOfRequiredItems;
		this.numberOfFreeItems = numberOfFreeItems;
	}

	@Override
	public int consume(List<Item> cart) {
		List<Item> matchingItems = new ArrayList<Item>();
		
		for(Item item : cart) {
			System.out.println("AmountBasedRule comparing sku " + sku + " with " + item.sku);
			if(item.sku.equals(sku)) {
				matchingItems.add(item);
			}
			if(matchingItems.size() == numberOfRequiredItems) {
				System.out.println(name + " applies, removing " + numberOfRequiredItems + " items from the cart");
				cart.removeAll(matchingItems);
				return numberOfRequiredItems;
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
		return Item.SKU_TO_PRICE_MAPPING.get(sku) * numberOfFreeItems;
	}

}
