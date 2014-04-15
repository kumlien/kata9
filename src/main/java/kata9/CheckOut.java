package kata9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public double total() {
		List<Item> itemsForRules = new ArrayList<Item>(items); 
		double grossAmount = getGrossAmount();
		double netAmount = grossAmount;

		rulesWhichApply = new ArrayList<BaseRule>();
		
		for(BaseRule rule : rules) {
			apply(rule, itemsForRules, rulesWhichApply);
		}
		
		//Go through the list of rules which applies
		for(BaseRule rule : rulesWhichApply) {
			netAmount -= rule.getDiscount();	
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
		double grossAmount = getGrossAmount();
		double netAmount = total();
		
		System.out.println("\n\n********* - START OF RECEIPT - ***********\n");
		
		Map<String, Integer> productCount = getProductCount(items);
		for(String sku : productCount.keySet()) {
			long price = Item.SKU_TO_PRICE_MAPPING.get(sku);
			long count = productCount.get(sku);
			System.out.println("You bought " + count + " number of " + sku + " á " + price + " = " + (double)(price * count));
		}
		
		System.out.println("\nAmount before discounts is: \t" + grossAmount + "\n");
		
		Map<BaseRule, Integer> ruleCount = getRuleCount(rulesWhichApply);
		for(BaseRule bs : ruleCount.keySet()) {
			int count = ruleCount.get(bs);
			System.out.println("The discount '" + bs.getName() + "' was applied " + count + " number of times, totalling " + count*bs.getDiscount() + " in discounts");
		}
		
		System.out.println("\nTotal discount is:\t\t" + totalDiscounts());
		System.out.println("Amount after discounts is:\t" + netAmount);
		
		System.out.println("\n\n********* - END OF RECEIPT - ***********");
	}
	
	private Map<BaseRule, Integer> getRuleCount(List<BaseRule> rules) {
		Map<BaseRule, Integer> aggregate = new HashMap<BaseRule, Integer>();
		for(BaseRule bs : rules) {
			Integer amount = aggregate.get(bs);
			if(amount == null) amount = 0;
			aggregate.put(bs, ++amount);
		}
		return aggregate;
	}
	
	
	private Map<String, Integer> getProductCount(List<Item> items) {
		Map<String, Integer> aggregate = new HashMap<String, Integer>();
		for(Item item : items) {
			Integer amount = aggregate.get(item.sku);
			if(amount == null) amount = 0;
			aggregate.put(item.sku,++amount);
		}
		
		return aggregate;
	}
	
	//Calculate total discounts
	private double totalDiscounts() {
		double total = 0;
		for(BaseRule rule : rulesWhichApply) {
			total += rule.getDiscount();
		}
		return total;
	}

	//Calculate the gross amount
	private double getGrossAmount() {
		double grossAmount = 0;
		for(Item item : items) {
			grossAmount += item.price;
		}
		return grossAmount;
	}
	
	
}
