package cat.hortance.shoppingcart.services;

import cat.hortance.shoppingcart.Models.Item;
import cat.hortance.shoppingcart.Repository.ItemRepository;
import cat.hortance.shoppingcart.ServicesImpl.ItemServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemServicetest {
    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    ItemServiceImpl itemService;

    @Test
    public  void returnItems(){
        when(itemRepository.findAll()).thenReturn(Arrays.asList(
                new Item(1,"shoes","womenswear"),
                new Item(2,"belt","menswear")));
        Assertions.assertEquals("belt",itemService.getItem(2).getName());
    }

    @Test
    public void deleteItem(){
        Item ir=new Item(3,"shirt","all");
        when(itemRepository.findById(ir.getId())).thenReturn(Optional.of(ir));
        itemService.deleteItem(ir.getId());
        verify(itemRepository).deleteById(ir.getId());
    }

    @Test
    public void updateItem(){
        Item optItem=new Item(4,"Mask","health");
        Item newItem= new Item("Medical","Outfit");
        given(itemRepository.findById(optItem.getId())).willReturn(Optional.of(optItem));

        itemService.updateItem(optItem.getId(),newItem);
        verify(itemRepository).save(newItem);
        verify(itemRepository).findById(optItem.getId());
    }
    @Test
    public void returnItem(){
        Item item= new Item();
        item.setId(2);
        when(itemRepository.findById(item.getId())).thenReturn(Optional.of(item));
        Item expected=itemService.getItem(item.getId());
        assertThat(expected).isSameAs(item);
        verify(itemRepository).findById(item.getId());

    }

}
