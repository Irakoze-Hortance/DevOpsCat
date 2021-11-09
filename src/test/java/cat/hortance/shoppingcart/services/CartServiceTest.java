package cat.hortance.shoppingcart.services;

import cat.hortance.shoppingcart.Models.Cart;
import cat.hortance.shoppingcart.Models.Item;
import cat.hortance.shoppingcart.Repository.CartRepository;
import cat.hortance.shoppingcart.ServicesImpl.CartServicesImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {
    @Mock
    CartRepository cartRepository;
    @InjectMocks
    CartServicesImpl cartService;

    @Test
    public void returnCarts(){
        when(cartRepository.findAll()).thenReturn(Arrays.asList(
                new Cart(200,"Fashion"),
                new Cart(4500,"Food")));
        assertEquals("Food",cartService.getAll().get(1).getCategory());
    }

    @Test
    public void createCart(){
        when(cartRepository.save(ArgumentMatchers.any(Cart.class))).thenReturn(
            new Cart(3400,"Drinks"));
        assertEquals("Drinks",cartService.createCart(2700,"Drinks").getCategory());
    }
    @Test
    public void deleteCart(){
        Cart cart= new Cart(7800,"Mixture");
        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
        cartService.deleteCart(cart.getId());
        verify(cartRepository).deleteById(cart.getId());
    }

    @Test
    public void returnCart(){
        Cart cart=new Cart();
        cart.setId(2L);
        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
        Cart expected=cartService.getCart(cart.getId());
        assertThat(expected).isSameAs(cart);
        verify(cartRepository).findById(cart.getId());
    }

    @Test
    public void  updateCart(){
        Cart cart=new Cart(1200,"wig");
        Cart newCart=new Cart(35000,"Wig");
        given(cartRepository.findById(cart.getId())).willReturn(Optional.of(cart));
        cartService.updateCart(cart.getId(),newCart);
        verify(cartRepository).save(newCart);
        verify(cartRepository).findById(cart.getId());
    }

    @Test
    public void addItemToCart(){
        Item item=new Item("Wig","Cosmetic");
        Cart cart=new Cart(35000,"Cosmo");
        cart.setItems(new ArrayList<Item>());
        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
        cartService.addItems(cart.getId(),item);

        verify(cartRepository).save(cart);
        verify(cartRepository).findById(cart.getId());
        List<Item> expected=cartService.getCart(cart.getId()).getItems();
        assertThat(expected).isSameAs(cart.getItems());
        verify(cartRepository).save(cart);
    }

    @Test
    public void retrieveCartItem(){
        Cart cart=new Cart(4500,"All that");
        cart.setItems(new ArrayList<Item>(Arrays.asList(
                new Item("watch","casual"),
                new Item("Tie","Formal"))));
        when (cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
        cartService.getItems(cart.getId(), item.getId());
        assertEquals("Tie",cartService.getItems(cart.getId(), item.getId()).get(1).getName());
    }
    @Test
    public void retrieveDetailsForItem(){
        Item item=new Item("Head phones","music");
        Cart cart=new Cart(3400,"Music");
        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
        when(cartService.getItems(cart.getId(),item.getId())).thenReturn((List<Item>) item);

        String expected="[name=Head phones,description=Music]";
        assertEquals(expected,cart.getItems());
        verify(cartRepository).findById(cart.getId());
    }
}
