package peterkoncz;


/**
 * Basic stockItem class here we are gonna store the name, price, quantity
 * also we will override the equals and hashCode and implement comparable interface
 * so the stockItems can be used in a sorted collections
 */
public class StockItem implements Comparable<StockItem>{

    private final String name;
    private double price;
    private int quantityInStock;
    private int reserved = 0;

    public StockItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantityInStock = 0;
    }
    //overloaded constructor if you wish to set the quantity as well
    public StockItem(String name, double price, int quantityInStock) {
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int availableQuantity() {
        return quantityInStock - reserved; //change this because the quantity available is after reserving
    }

    public void setPrice(double price) { // do a validation for less then 0 number
        if (price > 0) {
            this.price = price;
            }
        }
    //setter for stock will be called to adjust stock
    //increase or decrease based on if stock lvl is increased or sold !
    public void adjustStock(int quantity){
        int newQuantity = this.quantityInStock + quantity;
        if (newQuantity >= 0){ //basic validation here !
            this.quantityInStock = newQuantity;
        }
    }

    //To allow stock to be reserved and un-reserved -> create methods for these operations.
    public int reserveStock(int quantity) { // this method checks if enough items are available before it reserves it
        if(quantity <= availableQuantity()){
            reserved += quantity;
            return quantity;
        }
        return 0;
    }

    public int unreservedStock (int quantity){
        if (quantity <= reserved){
            reserved -= quantity;
            return quantity;
        }
        return 0;
    }

    //Last change in this class is to finalising the stock lvl when Items are actually sold !
    // so we have to create a method for this.
    public int finaliseStock(int quantity){
        if (quantity<=reserved){
            quantityInStock -= quantity;
            reserved -= quantity;
            return quantity;
        }
        return 0;
    }




//Override the equals and the hasCode methods
    @Override
    public int hashCode() {
        return this.name.hashCode() + 22; //can be any number
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("Entering StockItem.equals");
        if (obj == this){
            return true; //if it is the same obj im memory
        }

        if ((obj == null) || (obj.getClass() != this.getClass())){
            return false;  //checking for null or diff classes
        }

        String objName = ((StockItem) obj).getName();
         return this.name.equals(objName);  //we are only checking for name and using the String equals
    }

    @Override
    public int compareTo(StockItem o) {
//        System.out.println("Entering StockItem.compareTo");
        if (this == o){ // checking if it is the same obj in memory
            return 0; //0 because they are equal
        }

        if (o != null) {//must check for null to avoid the errors in case null gets into the method
            return this.name.compareTo(o.getName());
        }
        throw new NullPointerException(); //if you want to use to compareTo function you make sure it is not null ! (in case if it is null throw an exception)
    }


    @Override
    public String toString() { //override the toString method as well to display the reserved field
        return this.name + " : price " + this.price + ". Reserved: " + this.reserved;
    }
}
