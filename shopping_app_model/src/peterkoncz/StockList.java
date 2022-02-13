package peterkoncz;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This is going to hold a list of all the stock items in a map
 * we need some methods to add stock to this class and also
 * and remove them when the stock item product is actually sold
 */

public class StockList {
/*
  *Right now the StockList class uses a HashMap where the order of the items are chaotic
  * but we can change it to a LinkedHashMap (in the constructor) and because of the Comparable interface it will be an ordered collection
  * because it implements the Comparable and we overwrote the equals, hashCode and the compareTo methods !!
  * Everything will show in alphabetical order !
  *
  * NOTE: you do not have to change the Unmodifiable map return because there is no such a thing like unmodifiable LinkedHMap
  * because this is just a view of the collection it will have the same ordering as the list
  *
  * also this is a big advantage of using generics !
 */
    private final Map<String, StockItem> list;

    //Constructor initializing the map !
    public StockList() {
        //this.list = new HashMap<>();
        this.list = new LinkedHashMap<>();
    }

    public int addStock(StockItem item){
        if (item != null){
            //check if we already have quantity of this item
            StockItem inStock = list.getOrDefault(item.getName(), item);
            //if there are already stocks of this item, adjust quantity
            if (inStock != item){
                item.adjustStock(inStock.availableQuantity());
            }
            list.put(item.getName(), item);
            return item.availableQuantity();
        }
        return 0; //returning 0 if item is null!
    }

    //Modify this method instead of adjusting stock directly we are just calling
    //the finalise stock method with a positive quantity rather then a negative
    //when it was adjusting the stock
    //only sell stock needs to check if item exits on the list and quantity is greater then 0!
    public int sellStock(String name, int quantity){
        StockItem inStock = list.get(name);

        if ((inStock != null) && (quantity > 0)){
            return inStock.finaliseStock(quantity);
        }
            return 0;

// save the old sellStock method code :
//        StockItem inStock = list.getOrDefault(name,null);
//
//        if ((inStock != null) && (inStock.availableQuantity() >= quantity) && (quantity > 0)){  //here we are also checking for the null regarding to inStock
//            inStock.adjustStock(-quantity);  // if all true we are doing an adjustment //minus so we are deducting
//            return quantity;//indicating how many items we are selling from stock
//        }
//        return 0;
    }

    //adding the new methods like reserve / unreserved stock !
    public int reserveStock (String name, int quantity){
        StockItem inStock = list.get(name);

        if ((inStock != null) && (quantity > 0)){
            return inStock.reserveStock(quantity);
        }
        return 0;
    }

    public int unreserveStock(String name, int quantity){
        StockItem inStock = list.get(name);

        if ((inStock != null) && (quantity > 0)){
            return inStock.unreservedStock(quantity);
        }
        return 0;
    }


    //creating a getter here so we pass the name of the Stock item and returning the StickItem as an Object
    public StockItem get(String key){
        return list.get(key); //it will return the obj or null if cannot find anything
    }

    //here we will return the Items the Map
    public Map<String, StockItem> Items(){
        return Collections.unmodifiableMap(list); //this will return the Map called list

    }

    //one possible option to prevent changes from outer code we are going to provide a price list method!
    //here we are providing an unmodifiable map for name and prices for the caller to use
    //the entries in the map consists from a pairs of Strings and Doubles now both of these are immutable
    //so not only the map cannot be modified but the individual entries in it cannot be either!
    public Map<String,Double> PriceList(){
        Map<String,Double> prices = new LinkedHashMap<>();
        for (Map.Entry<String,StockItem> item : list.entrySet()){
            prices.put(item.getKey(), item.getValue().getPrice());
        }
        return Collections.unmodifiableMap(prices);
    }


    //Override toString method but this case it will it wont be just a single item it will return a while List of items
    //even tough  it is a single method , you can use it with a class like this which holds a quantity of even number of objects
    //(it is not a good idea to do this because toString is intended to debugging)
    @Override
    public String toString() {
        String s = "\nStock List\n";
        double totalCost = 0.0;
        for (Map.Entry<String, StockItem> item : list.entrySet()) {
            StockItem stockItem = item.getValue();

            double itemValue = stockItem.getPrice() * stockItem.availableQuantity();

            s = s + stockItem + ". There are " + stockItem.availableQuantity() + " in stock. Value of items: ";
            s = s + String.format("%.2f",itemValue) + "\n";
            totalCost += itemValue;
        }

        return s + "Total stock value " + totalCost;
    }

















}
