package kata9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rules.AmountBasedRule;
import rules.BaseRule;

public class Main {
	
	private static final Map<String, Long> SKU_TO_PRICE_MAPPING = new HashMap<String, Long>();
	
	private static final List<BaseRule> rules = new ArrayList<BaseRule>();
	
	static {
		//Add some base prices
		SKU_TO_PRICE_MAPPING.put("A", 50l);
		SKU_TO_PRICE_MAPPING.put("B", 30l);
		SKU_TO_PRICE_MAPPING.put("C", 20l);
		SKU_TO_PRICE_MAPPING.put("D", 15l);
		
		//Add some rules for discounts
		rules.add(new AmountBasedRule("Buy three Apples for 1.30 ","A", 20, 3));
		rules.add(new AmountBasedRule("Buy two bananas for .45 ","B", 15, 2));
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
			if(sku == null || !SKU_TO_PRICE_MAPPING.containsKey(sku)) {
				System.out.println("Not adding unknown sku '" + sku+ "'");
				continue;
			}
			Item item = new Item(sku, SKU_TO_PRICE_MAPPING.get(sku));
			co.scan(item);
		}
		
		co.printReceipt();
		
	}
}
