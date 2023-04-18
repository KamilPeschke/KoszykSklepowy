package KoszykSklepowyApi.response;


import KoszykSklepowyApi.model.Item;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

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
