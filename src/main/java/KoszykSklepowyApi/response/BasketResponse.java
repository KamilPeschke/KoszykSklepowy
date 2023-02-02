package KoszykSklepowyApi.response;

import KoszykSklepowyApi.Model.Basket;
import KoszykSklepowyApi.Model.Item;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasketResponse {

    private Long basketId;
    private List<Item> itemList;
    private double sum;
    private String status;

    public BasketResponse(Basket basket) {
        this.basketId = basket.getBasketId();
        this.itemList = basket.getItemList();
        this.sum = basket.getSum();
    }
}
