package KoszykSklepowyApi.response;


import KoszykSklepowyApi.Model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemResponse {

    private Long itemId;
    private String name;
    private double price;

    public ItemResponse(Item item) {
        this.itemId = item.getItemId();
        this.name = item.getName();
        this.price = item.getPrice();
    }
}
