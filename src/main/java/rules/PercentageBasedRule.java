package rules;

import kata9.Item;


/**
 * Percentage based discount rule. The discount is applied if
 * the number of required items with the specified sku is in
 * the list of items. Can be used to offer 10% discount on a
 * certain sku for example.
 * 
 * @author svante
 */
public class PercentageBasedRule extends BaseRule {
	
	final int discountInPercent; 
	
	public PercentageBasedRule(String name, String sku, int neededItems, int discountInPercent) {
		super(name, sku, neededItems);
		this.discountInPercent = discountInPercent;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getDiscount() {
		long price = Item.SKU_TO_PRICE_MAPPING.get(sku);
		double total = price * neededItems;
		double multiplicand = (double)discountInPercent / 100; 
		double discount = total * multiplicand;
		return discount;
	}
}
