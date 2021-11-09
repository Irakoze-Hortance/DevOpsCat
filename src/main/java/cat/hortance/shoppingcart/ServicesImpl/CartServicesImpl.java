package cat.hortance.shoppingcart.ServicesImpl;

import cat.hortance.shoppingcart.Models.Cart;
import cat.hortance.shoppingcart.Models.Item;
import cat.hortance.shoppingcart.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServicesImpl {
    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getAll(){
        return cartRepository.findAll();
    }

    public Cart createCart(Integer price,String category){
        Cart cart=new Cart(price,category);
        return  cartRepository.save(cart);
    }

    public void deleteCart(Long id){
        cartRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cart with id " + id + "not found"));
        cartRepository.deleteById(id);
    }

    public Cart updateCart(long id, Cart cart){
        cartRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cart with id " + id + "not found"));
        cart.setId(id);
        return  cartRepository.save(cart);
    }

    public  Cart getCart(Long id){
        return  cartRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Cart with id " + id + "not found"));
    }

    public List<Item> getItems(Long cartId, Integer id){
        Cart cart=getCart(cartId);
        return cart.getItems();
    }
    public Item getItems(Long cartId,Long itemId){
        Cart cart=getCart(cartId);
        for(Item item:cart.getItems()){
            if(item.getId().equals(itemId)){
                return item;
            }
        }
        return null;
    }

    public Cart addItems(Long cartId, Item item){
        Cart cart=getCart(cartId);
        if(cart==null){
            return null;
        }
        cart.setItems((List<Item>) item);
        cartRepository.save(cart);
        return  cart;
    }
}
