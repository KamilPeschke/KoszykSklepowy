package KoszykSklepowyApi.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    EMPTY("EMPTY"),
    PROCESSING("PROCESSING"),
    COMPLETED("COMPLETED"),
    CANCELED("CANCELED");

    private final String value;
}
