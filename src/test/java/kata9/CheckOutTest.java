package kata9;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import rules.AmountBasedRule;
import rules.BaseRule;
import rules.PercentageBasedRule;

public class CheckOutTest {
	
	static List<BaseRule> defaultRules = new ArrayList<BaseRule>();
	static CheckOut defaultCheckOut = null;
	
	@BeforeClass public static void initRules() {
		defaultRules.add(new AmountBasedRule("Buy three Apples for 1.30 ","A", 20, 3));
		defaultRules.add(new AmountBasedRule("Buy two bananas for .45 ","B", 15, 2));
	}
	
	@Before public void initCheckOut() {
		defaultCheckOut = new CheckOut(defaultRules);
	}
	
	@Test public void test1() {
		createAndScanItems(defaultCheckOut, "A", 50, 1);
		Assert.assertEquals(50, defaultCheckOut.total(), 0.01);
	}
	
	@Test public void test2() {
		createAndScanItems(defaultCheckOut, "A", 50, 1);
		createAndScanItems(defaultCheckOut, "B", 30, 1);
		Assert.assertEquals(80, defaultCheckOut.total(), 0.01);
	}
	
	@Test public void test3() {
		createAndScanItems(defaultCheckOut, "C", 20, 1);
		createAndScanItems(defaultCheckOut, "D", 15, 1);
		createAndScanItems(defaultCheckOut, "B", 30, 1);
		createAndScanItems(defaultCheckOut, "A", 50, 1);
		Assert.assertEquals(115, defaultCheckOut.total(), 0.01);
	}
	
	@Test public void test4() {
		createAndScanItems(defaultCheckOut, "A", 50, 2);
		Assert.assertEquals(100, defaultCheckOut.total(), 0.01);
	}
	
	@Test public void test5() {
		createAndScanItems(defaultCheckOut, "A", 50, 3);
		Assert.assertEquals(130, defaultCheckOut.total(), 0.01);
	}
	
	@Test public void test6() {
		createAndScanItems(defaultCheckOut, "A", 50, 4);
		Assert.assertEquals(180, defaultCheckOut.total(), 0.01);
	}
	
	@Test public void test7() {
		createAndScanItems(defaultCheckOut, "A", 50, 5);
		Assert.assertEquals(230, defaultCheckOut.total(), 0.01);
	}
	
	@Test public void test8() {
		createAndScanItems(defaultCheckOut, "A", 50, 6);
		Assert.assertEquals(260, defaultCheckOut.total(), 0.01);
	}
	
	@Test public void test9() {
		Assert.assertEquals(0, defaultCheckOut.total(), 0.01);
		createAndScanItems(defaultCheckOut, "A", 50, 1);
		Assert.assertEquals(50, defaultCheckOut.total(), 0.01);
		createAndScanItems(defaultCheckOut, "B", 30, 1);
		Assert.assertEquals(80, defaultCheckOut.total(), 0.01);
		createAndScanItems(defaultCheckOut, "A", 50, 1);
		Assert.assertEquals(130, defaultCheckOut.total(), 0.01);
		createAndScanItems(defaultCheckOut, "A", 50, 1);
		Assert.assertEquals(160, defaultCheckOut.total(), 0.01);
		createAndScanItems(defaultCheckOut, "B", 30, 1);
		Assert.assertEquals(175, defaultCheckOut.total(), 0.01);
		
	}
	
	@Test public void testAmountBased1() {
		List<BaseRule> rules = new ArrayList<BaseRule>();
		rules.add(new AmountBasedRule("Three apples gives 20 discount", "A", 20, 3));
		CheckOut co = new CheckOut(rules);
		createAndScanItems(co, "A", 50, 3);
		Assert.assertEquals(130, co.total(), 0.01);
	}
	
	@Test public void testAmountBased2() {
		List<BaseRule> rules = new ArrayList<BaseRule>();
		rules.add(new AmountBasedRule("Three apples gives 20 discount", "A", 20, 3));
		CheckOut co = new CheckOut(rules);
		createAndScanItems(co, "A", 50, 6);
		Assert.assertEquals(260, co.total(), 0.01);
	}
	
	@Test public void testPercentBased() {
		List<BaseRule> rules = new ArrayList<BaseRule>();
		rules.add(new PercentageBasedRule("10% discount on all Dates","D", 1, 10));
		CheckOut co = new CheckOut(rules);
		createAndScanItems(co, "d", 15, 1);
		Assert.assertEquals(13.5, co.total(), 0.01);
	}
	
	void createAndScanItems(CheckOut co, String sku, long price, int noOfItems) {
		for(int i=0; i<noOfItems; i++) {
			Item item = new Item(sku, price);
			co.scan(item);
		}
	}
}
