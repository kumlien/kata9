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
	
	final Integer numberOfFreeItems;
	
	public ItemBasedRule(String name, String sku, Integer numberOfRequiredItems, Integer numberOfFreeItems) {
		super(name, sku, numberOfRequiredItems);
		if(numberOfRequiredItems < numberOfFreeItems) {
			throw new IllegalArgumentException("The number of required items must be higher than the number of freebies");
		}
		this.numberOfFreeItems = numberOfFreeItems;
	}


	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getDiscount() {
		return Item.SKU_TO_PRICE_MAPPING.get(sku) * numberOfFreeItems;
	}

}
