package rules;

import java.util.List;

import kata9.Item;


/**
 * A base rule...
 * 
 * Simplification: we don't allow stacking of rules.
 * 
 */
public abstract class BaseRule {
	

	/**
	 * Will consume the number of items uses by this rule (if any).
	 * 
	 * @param items
	 * @return the number of items consumed
	 */
	public abstract int consume(List<Item> items);

	public abstract String getName();
	
	public abstract long getDiscount();
	
	public String toString() {
		return getName();
	}
	
}
