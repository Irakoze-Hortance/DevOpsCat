package cat.hortance.shoppingcart.Controller;

import cat.hortance.shoppingcart.Models.Item;
import cat.hortance.shoppingcart.ServicesImpl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {
    @Autowired
    ItemServiceImpl itemService;

    @GetMapping("/get-all")
    public List<Item> getAll(){
        return  itemService.getAllItems();
    }

    @PostMapping("/add-item")
    public  Item addItem(@RequestBody Item item){
        return  itemService.addItem(item.getName(),item.getDescription());
    }

    @PostMapping("/edit-item/{itemIid}")
    public Item editItem(@RequestBody Item item, @PathVariable Long itemId){
        return itemService.updateItem(Math.toIntExact(itemId),item);
    }
}
