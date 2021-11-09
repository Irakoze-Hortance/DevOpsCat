package cat.hortance.shoppingcart.ServicesImpl;

import cat.hortance.shoppingcart.Models.Item;
import cat.hortance.shoppingcart.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl {
    @Autowired
    ItemRepository itemRepository;

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    public Item addItem(String name, String description){
        Item item=new Item(name,description);
        return  itemRepository.save(item);
    }

    public void deleteItem(Integer id){
        itemRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Item not found with id" + id));
        itemRepository.deleteById(id);
    }

    public Item updateItem(Long id, Item item){
        itemRepository.findById(Math.toIntExact(id))
                .orElseThrow(()->new RuntimeException("Item now found with id " + id));
        item.setId(id);
        return itemRepository.save(item);
    }
    public  Item getItem(Integer id){
        return itemRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Item with id " + id + "not found"));

    }
}
