package rules;

import java.util.List;

import kata9.Item;

public class PercentageBasedRule extends BaseRule {

	private String name;

	@Override
	public int consume(List<Item> items) {
		return 0;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public long getDiscount() {
		return 0;
	}

}
