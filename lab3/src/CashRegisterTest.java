import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;


public class CashRegisterTest {

	@Test
	public void test() {
		
		// Ones: 100
        // Fives: 10
        // Tens: 10
        // Twenties: 10
        // Fifties: 10
        // Hundreds: 10
        // Pennies: 1,000
        // Nickles: 100
        // Dimes: 100
        // Quarters: 10
        CashRegister cr = new CashRegister(100,10,10,10,10,10,1000,100,100,10);
        
        //120.00
        // Ones: 0
        // Fives: 0
        // Tens: 0
        // Twenties: 1
        // Fifties: 0
        // Hundreds: 1
        BillPack purchaseBills = new BillPack(0, 0, 0, 1, 0, 1);
        
        //0.00
        // Pennies: 0
        // Nickles: 0
        // Dimes: 0
        // Quarters: 0
        CoinPack purchaseCoins = new CoinPack(0, 0, 0, 0);

        double notebookPrice = 10.57;
        double change = cr.purchaseItem(notebookPrice, purchaseBills, purchaseCoins);
		
    	assertTrue(change == 108.80);
    	
    	// Ones: 100
        // Fives: 10
        // Tens: 10
        // Twenties: 10
        // Fifties: 10
        // Hundreds: 10
        // Pennies: 1,000
        // Nickles: 100
        // Dimes: 100
        // Quarters: 10
        CashRegister cr2 = new CashRegister(100,10,10,10,10,10,1000,100,100,10, new PennsylvaniaTax());
        
        double change2 = cr2.purchaseItem(notebookPrice, purchaseBills, purchaseCoins);
        
        assertTrue(change2 == 108.80);
        
        
     // The below shows how to instantiate a new CashRegister, scan multiple items, and complete
        // the purchase of multiple items. Note that a cash register can also be instantiated
        // with the BillPack and CoinPack objects instead of individual values for each type of
        // coin and bill
        BillPack crBills = new BillPack(10, 10, 10, 10, 10, 10);
        CoinPack crCoins = new CoinPack(1000, 100, 100, 100);

        CashRegister multiItems = new CashRegister(crBills, crCoins);

        // Create a list of item prices and item names. The cash register will keep track of the
        // item name and it's price as you scan new items before finalizing the purchase.
        List<Double> itemPrices = new ArrayList<>();
        List<String> itemNames = new ArrayList<>();

        itemPrices.add(10.00);
        itemNames.add("Expensive Soda");
        itemPrices.add(24.99);
        itemNames.add("Very Good Chocolate");
        itemPrices.add(0.89);
        itemNames.add("Very Bad Chocolate");

        // Run through the list of items, scan them into the cash register using their price and
        // name
        for(int i = 0; i < itemPrices.size(); ++i)
            multiItems.scanItem(itemPrices.get(i), itemNames.get(i));

        // The bills and coins the user will be using the purchase the above list of 3 items
        BillPack bills = new BillPack(1, 1, 2, 1, 0, 0);
        CoinPack coins = new CoinPack(10, 1, 2, 5);

        // Finalize the purchase and gather the returned change if the purchase is successful
        double multiChange = multiItems.finalizePurchase(bills, coins);
        
        //35.88+2.15 (38.03) -47.6
        
        assertTrue(multiChange == 9.57);
        
        BillPack crBills2 = new BillPack(10, 10, 10, 10, 10, 10);
        CoinPack crCoins2 = new CoinPack(1000, 100, 100, 100);

        CashRegister multiItems2 = new CashRegister(crBills2, crCoins2);

        // Create a list of item prices and item names. The cash register will keep track of the
        // item name and it's price as you scan new items before finalizing the purchase.
        List<Double> itemPrices2 = new ArrayList<>();
        List<String> itemNames2 = new ArrayList<>();

        itemPrices2.add(10.00);
        itemNames2.add("Expensive Soda");
        itemPrices2.add(24.99);
        itemNames2.add("Very Good Chocolate");
        itemPrices2.add(0.89);
        itemNames2.add("Very Bad Chocolate");

        // Run through the list of items, scan them into the cash register using their price and
        // name
        for(int i = 0; i < itemPrices2.size(); ++i)
            multiItems2.scanItem(itemPrices2.get(i), itemNames2.get(i));

        // The bills and coins the user will be using the purchase the above list of 3 items
        BillPack bills2 = new BillPack(1, 1, 3, 0, 0, 0);
        CoinPack coins2 = new CoinPack(13, 1, 6, 5);

        // Finalize the purchase and gather the returned change if the purchase is successful
        double multiChange2 = multiItems2.finalizePurchase(bills2, coins2);
        
        //35.88+2.15 (38.03) -38.03
        
        assertTrue(multiChange2 == 0.00);
        
        Drawer defDrawer = new Drawer();
        Drawer defDrawer2 = new Drawer(0, 0, 0, 0, 0, 0, 0, 0, 0, 0); 
        
        assertTrue(defDrawer.dime() == 0);
        assertTrue(defDrawer2.dime() == 0);
        assertTrue(defDrawer.fifty() == 0);
        assertTrue(defDrawer2.fifty() == 0);
        assertTrue(defDrawer.ten() == 0);
        assertTrue(defDrawer2.ten() == 0);
        assertTrue(defDrawer.twenty() == 0);
        assertTrue(defDrawer2.twenty() == 0);
        
        
        
     // Ones: 100
        // Fives: 10
        // Tens: 10
        // Twenties: 10
        // Fifties: 10
        // Hundreds: 10
        // Pennies: 1,000
        // Nickles: 100
        // Dimes: 100
        // Quarters: 10
        CashRegister cr3 = new CashRegister(100,10,10,10,10,10,1000,100,100,10);
        
        //120.00
        // Ones: 0
        // Fives: 0
        // Tens: 0
        // Twenties: 1
        // Fifties: 0
        // Hundreds: 1
        BillPack purchaseBills3 = new BillPack(0, 0, 0, 1, 1, 0);
        
        //0.00
        // Pennies: 0
        // Nickles: 0
        // Dimes: 0
        // Quarters: 0
        CoinPack purchaseCoins3 = new CoinPack(0, 0, 0, 0);

        double notebookPrice3 = 10.57;
        double change3 = cr3.purchaseItem(notebookPrice3, purchaseBills3, purchaseCoins3);
		
    	assertTrue(change3 == 58.80);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testNegativeBalance() {
		CashRegister cr = new CashRegister(100,-10,10,10,10,10,1000,100,100,10);
		//throw new IllegalArgumentException();
	}


}
