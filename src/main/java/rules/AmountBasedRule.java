package rules;


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
	

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getDiscount() {
		return discount;
	}

}
