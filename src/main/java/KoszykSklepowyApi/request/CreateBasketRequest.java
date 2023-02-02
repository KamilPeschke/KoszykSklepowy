package KoszykSklepowyApi.request;

import KoszykSklepowyApi.Model.Item;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateBasketRequest {

    private Long basketId;

    private String status;

    @NotNull
    private double sum;

    private List<Item> itemList = new ArrayList<>();

}
