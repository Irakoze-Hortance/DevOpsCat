package cat.hortance.shoppingcart.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="items")
public class Item {
    @Id
    private Integer id;
    private String name;
    private String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString(){
        return  String.format(
             "Item [id=%s, name=%s,description=%s]",id,name,description
        );
    }
}
