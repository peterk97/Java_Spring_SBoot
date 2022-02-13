package academy.learnprogramming.model;

import lombok.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class TodoData {

    //== fields ==
    private static int idValue = 1;

    private final List<TodoItem> items = new ArrayList<>();

    //== constructor ==
    public TodoData(){

        //add some dummy data, suing the addItem method so it sets the id field
        addItem(new TodoItem("first", "first details", LocalDate.now()));
        addItem(new TodoItem("second", "second details", LocalDate.now()));
        addItem(new TodoItem("third", "third details", LocalDate.now()));
        addItem(new TodoItem("fourth", "fourth details", LocalDate.now()));

    }

    //== public methods ==
    public List<TodoItem> getItems(){
        return Collections.unmodifiableList(items);
    }

    public void addItem(@NonNull TodoItem toAdd){
        toAdd.setId(idValue);
        items.add(toAdd);
        idValue++;
    }

   public void removeItem(int id){
       ListIterator<TodoItem> itemIterator = items.listIterator();

       while(itemIterator.hasNext()){
           TodoItem item = itemIterator.next();

           if (item.getId() == id){
               itemIterator.remove();
               break;
           }
       }
   }

    public TodoItem getItem(int id){
        for (TodoItem item : items){
            if(item.getId() == id){
                return item;
            }
        }
        return null;
    }


    public void updateItem(@NonNull TodoItem toUpdate){
        ListIterator<TodoItem> itemIterator = items.listIterator();

        while (itemIterator.hasNext()){
            TodoItem item = itemIterator.next();

            if (item.equals(toUpdate)){
                itemIterator.set(toUpdate);
                break;
            }
        }
    }





}






/*
We are creating a data class for our to do list app to represent a data source or an in-memory
database. We will use a list to hold our data in memory.

We are aiming to make this class immutable so we do not want any other classes to have access to this class!

we are going to use lombok as well to validate a null parameter @NonNull (instead of an if statement and throw exception)
 */