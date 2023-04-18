package KoszykSklepowyApi.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateItemRequest {

    private Long itemId;

    @NotBlank(message = "name is required")
    private String name;

    private double price;
}
