package kata9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rules.AmountBasedRule;
import rules.BaseRule;
import rules.ItemBasedRule;
import rules.PercentageBasedRule;

/**
 * Main class. 
 * 
 * Init with some rules. 
 * 
 * 
 * @author svante
 *
 */
public class Main {
	
	private static final List<BaseRule> rules = new ArrayList<BaseRule>();
	
	static {
		//Add some rules for discounts
		rules.add(new AmountBasedRule("Buy three Apples for 130 ","A", 20, 3));
		rules.add(new AmountBasedRule("Buy two Bananas for 45 ","B", 15, 2));
		rules.add(new ItemBasedRule("Buy two Coconuts, get one for free!", "C", 2, 1));
		rules.add(new PercentageBasedRule("10% discount on all Dates","D", 1, 10));
	}
	
	public static void main(String... args) {
		System.out.println("Starting with args: " + Arrays.asList(args));
		
		if(args.length < 1) {
			System.err.println("Please start me with a list of products like: 'mvn exec:java -Dexec.arguments=\"ABABBA\"");
			System.exit(1);
		}
		CheckOut co = new CheckOut(rules);
		
		for(char charSku : args[0].toCharArray()) {
			String sku = String.valueOf(charSku).toUpperCase();
			if(sku == null || !Item.SKU_TO_PRICE_MAPPING.containsKey(sku)) {
				System.out.println("Not adding unknown sku '" + sku+ "'");
				continue;
			}
			Item item = new Item(sku, Item.SKU_TO_PRICE_MAPPING.get(sku));
			co.scan(item);
		}
		
		co.printReceipt();
		
	}
}
