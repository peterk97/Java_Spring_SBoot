package academy.learnprogramming.controller;

import academy.learnprogramming.model.TodoData;
import academy.learnprogramming.model.TodoItem;
import academy.learnprogramming.service.TodoItemService;
import academy.learnprogramming.util.AttributeNames;
import academy.learnprogramming.util.Mappings;
import academy.learnprogramming.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

//This controller will contain request methods for the to-do items!
//we need a couple of request methods to view a single item, deleting items, and so on!
@Controller
@Slf4j
public class TodoItemController {

    //== fields ==
    private final TodoItemService service;

    //== constructors ==
    @Autowired
    public TodoItemController(TodoItemService service) {
        this.service = service;
    }

    //== model attributes ==
    //in this case the model attribute name will be the class name with first letter lower case
    @ModelAttribute
    public TodoData todoData(){
        return service.getData();
    }

    //== handler methods ==

    // http://localhost:8080/todo-list/itmes
    @GetMapping(Mappings.ITEMS)
    public String items(){
        return ViewNames.ITEMS_LIST;
    }

    //this will be used ot edit an item
    @GetMapping(Mappings.ADD_ITEM) //executing get http request
    public String addEditItem(@RequestParam(required = false, defaultValue = "-1") int id, Model model){
        //editing functionality
        log.info("Editing id = {}",id);
        TodoItem todoItem = service.getItem(id);

        //if the service doesn't find a matching item ID it will return null, and then we create a new item
        //and add that to the model as an attribute.
        //However, the service does find an item the if null statement does not execute and the existing item can be modified.
        if (todoItem == null){
            todoItem = new TodoItem("","", LocalDate.now());
        }

        //we will add a new todoItem and then edit it as a model attribute.
        model.addAttribute(AttributeNames.TODO_ITEM,todoItem);
        //we also need to return a view
        return ViewNames.ADD_ITEM;
    }


    // This method will be called after the form has been submitted in add_item.jsp
    // it is going to use the http post method
    // this is a post method once we submit the form we can return to the table and see the item right away
    //post redirect get pattern! spring grants us the functionality to redirect
    // after post to some other view by simply prefixing the view name with a redirect String
    //we have to use the @ModelAttribute and this method will give us a complete object with all the fields set.
    @PostMapping(Mappings.ADD_ITEM)
    public String processItem(@ModelAttribute(AttributeNames.TODO_ITEM) TodoItem todoItem){
        log.info("todoItem form = {}", todoItem);

        //handling adding and editing.
        //if it is 0 we now that we have a new item, and we have to add it otherwise we call the update item
        if (todoItem.getId() == 0){
            //calling the service to add the item!
            service.addItem(todoItem);
        }else {
            service.updateItem(todoItem);
        }

        //with this return we are redirecting our view to another URL
            return "redirect:/" + Mappings.ITEMS;
    }

    //since the processItem method is executed when we post the form we need to create another request method that will display the form!

    //delete Item method()
    @GetMapping(Mappings.DELETE_ITEM)
    public String deleteItem(@RequestParam int id){
        log.info("Deleting an item with id= {}",id);
        service.removeItem(id);
        return "redirect:/" + Mappings.ITEMS;
    }


    //view item mapping
    //calling the service to get the item based on the id and adding it to the model.
    @GetMapping(Mappings.VIEW_ITEM)
    public String viewItem(@RequestParam int id, Model model){
        TodoItem todoItem = service.getItem(id);
        model.addAttribute(AttributeNames.TODO_ITEM,todoItem);
        return ViewNames.VIEW_ITEM;
    }



}

/**
 * To expose the to-do items as model attributes we are going to create an instance of to-do data inside a method
 * and annotate the method with the model attribute annotation!
 */