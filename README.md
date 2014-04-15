kata9
=====


####Execute it!

* Clone the repo (`git clone https://github.com/kumlien/kata9.git`)
* `cd kata9`
* `mvn clean install`
* Run the program something like this: `mvn exec:java -Dexec.arguments="abcccaad"` 
* You should see some output on the screen (fingers crossed...) 

####Items
There are four items in the shop:
* A (Apples) with a cost of 50
* B (Bananas) with a cost of 30
* C (Coconuts) with a cost of 20
* D (Dates) with a cost of 15

####Rules
Four discounts are configured:

* Buy three Apples for a total of 130 (AmountBasedRule)
* Buy two Bananas for a total of 45 (AmountBasedRule)
* Buy two Coconuts and get one for free (ItemBasedRule)
* There is a 10% discount on dates! (PercentageBasedRule)
