package KoszykSklepowyApi.request;

import KoszykSklepowyApi.model.Item;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBasketRequest {

    private long id;
    private double sum;
    private List<Item> itemList = new ArrayList<>();
    private String status;
    }

