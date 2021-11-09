package cat.hortance.shoppingcart.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer price;
    private String category;
    private List<Item> items;

    public Cart(Integer price,List<Item> items,String category) {
        this.price = price;
        this.items=items;
        this.category=category;
    }


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Cart(Integer price, String category) {
        this.price = price;
        this.category=category;
    }
}
