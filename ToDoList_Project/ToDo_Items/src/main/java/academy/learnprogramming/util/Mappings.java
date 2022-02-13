package academy.learnprogramming.util;

//This class will hold constants for all the mapping in our application!
//all the controllers will use this class for mapping!

//final because it doesn't need to be extended
public final class Mappings {


    //== constants ==
    public static final String ITEMS = "items";
    public static final String ADD_ITEM = "addItem";
    public static final String DELETE_ITEM = "deleteItem";
    public static final String VIEW_ITEM = "viewItem";

    // == constructors ==
    //private because it is no reason to instantiating it!
    private Mappings(){

    }
}