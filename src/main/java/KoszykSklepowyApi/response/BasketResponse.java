package KoszykSklepowyApi.response;

import KoszykSklepowyApi.model.Basket;
import KoszykSklepowyApi.model.Item;
import KoszykSklepowyApi.model.OrderStatus;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasketResponse {

    private Long basketId;

    @With
    private double sum;

    private String status;

    private OrderStatus orderStatus;

    public BasketResponse(Basket basket) {
        this.basketId = basket.getBasketId();
        this.sum = basket.getSum();
        this.orderStatus =basket.getOrderStatus();
    }

}