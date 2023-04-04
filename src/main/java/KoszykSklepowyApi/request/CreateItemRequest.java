package KoszykSklepowyApi.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateItemRequest {

    private Long itemId;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "price is required")
    private double price;
}
