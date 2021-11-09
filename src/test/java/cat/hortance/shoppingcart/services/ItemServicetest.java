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
                new Item(1L,"shoes","womenswear"),
                new Item(2L,"belt","menswear")));
        Assertions.assertEquals("belt",itemService.getItem(2).getName());
    }

    @Test
    public void deleteItem(){
        Item ir=new Item(3L,"shirt","all");
        when(itemRepository.findById(Math.toIntExact(ir.getId()))).thenReturn(Optional.of(ir));
        itemService.deleteItem(Math.toIntExact(ir.getId()));
        verify(itemRepository).deleteById(Math.toIntExact(ir.getId()));
    }

    @Test
    public void updateItem(){
        Item optItem=new Item(4L,"Mask","health");
        Item newItem= new Item("Medical","Outfit");
        given(itemRepository.findById(Math.toIntExact(optItem.getId()))).willReturn(Optional.of(optItem));

        itemService.updateItem(optItem.getId(),newItem);
        verify(itemRepository).save(newItem);
        verify(itemRepository).findById(Math.toIntExact(optItem.getId()));
    }
    @Test
    public void returnItem(){
        Item item= new Item();
        item.setId(2L);
        when(itemRepository.findById(Math.toIntExact(item.getId()))).thenReturn(Optional.of(item));
        Item expected=itemService.getItem(Math.toIntExact(item.getId()));
        assertThat(expected).isSameAs(item);
        verify(itemRepository).findById(Math.toIntExact(item.getId()));

    }

}
