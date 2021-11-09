package cat.hortance.shoppingcart.Controller;

import cat.hortance.shoppingcart.Models.Cart;
import cat.hortance.shoppingcart.Models.Item;
import cat.hortance.shoppingcart.ServicesImpl.CartServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    CartServicesImpl cartServices;

    @GetMapping("/all")
    public List<Cart> getAll(){
        return  cartServices.getAll();
    }
    @PostMapping("/create-cart")
    public Cart createCart(@RequestBody Cart cart){
        return cartServices.createCart(cart.getPrice(),cart.getCategory());
    }

    @GetMapping("/{cartId}/items")
    public  List<Item> retrieveCartItems(@PathVariable Long cartId){
        return  cartServices.getItems(cartId, Item.getId());
    }

    @GetMapping("/{cartid}/items/{itemId}")
    public  Item retrieveItemDetailsForCart(
            @PathVariable Long cartId,
            @PathVariable Long itemId){
        return  cartServices.getItems(cartId,itemId);
    }

    @PostMapping("/cart/{cartId}/items")
    public Cart addItemTocart(@PathVariable Long itemId, @RequestBody Item newItem){
        return  cartServices.addItems(itemId,newItem);
    }
    @PutMapping("/edit-cart/{id}")
    public Cart editCart(@RequestBody Cart cart, @PathVariable Long id){
        return  cartServices.updateCart(id,cart);
    }

}
