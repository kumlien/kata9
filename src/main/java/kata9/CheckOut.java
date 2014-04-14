package kata9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rules.AmountBasedRule;
import rules.BaseRule;

public class CheckOut {
	
	
	public final List<BaseRule> rules;
	
	private final List<Item> items = new ArrayList<Item>(); 
	
	//Put it here just to make it available for the dummy printReceipt method
	List<BaseRule> rulesWhichApply;
	
	public CheckOut(List<BaseRule> rules) {
		this.rules = Collections.unmodifiableList(rules);
	}
	
	public void scan(Item item) {
		System.out.println("Item added to basket: " + item);
		items.add(item);
	}
	
	public long total() {
		List<Item> itemsForRules = new ArrayList<Item>(items); 
		long grossAmount = getGrossAmount();
		long netAmount = grossAmount;

		rulesWhichApply = new ArrayList<BaseRule>();
		
		for(BaseRule rule : rules) {
			apply(rule, itemsForRules, rulesWhichApply);
		}
		
		//Go through the list of rules which applies
		for(BaseRule rule : rulesWhichApply) {
			if(rule instanceof AmountBasedRule) {
				netAmount = netAmount -= ((AmountBasedRule)rule).discount;
			}
		}
		
		return netAmount;
	}
	
	//Run the rule on the items until it doesn't apply
	//Add it to the list or rules which apply if it applies
	void apply(BaseRule rule, List<Item> items, List<BaseRule> rulesWhichApply) {
		if(rule.consume(items) >0) {
			rulesWhichApply.add(rule);
			apply(rule, items, rulesWhichApply);
		}
	}
	
	
	/**
	 * Print something including the total
	 */
	public void printReceipt() {
		long grossAmount = getGrossAmount();
		long netAmount = total();
		
		System.out.println("\n\n********************");
		System.out.println("This is your receipt:\n");
		for(Item item : items) {
			System.out.println("\t" + item);
		}
		System.out.println("\n\nAmount before discounts is: \t" + grossAmount + "\n");
		
		for(BaseRule rule : rulesWhichApply) {
			System.out.println("You got a discount: " + rule + " (discount = " + rule.getDiscount() + ")");
		}
		
		System.out.println("\nTotal discount is:\t\t" + totalDiscounts());
		System.out.println("Amount after discounts is:\t" + netAmount);
	}

	private long totalDiscounts() {
		long total = 0;
		for(BaseRule rule : rulesWhichApply) {
			total += rule.getDiscount();
		}
		return total;
	}

	private long getGrossAmount() {
		long grossAmount = 0;
		for(Item item : items) {
			grossAmount += item.price;
		}
		return grossAmount;
	}
	
	
}
