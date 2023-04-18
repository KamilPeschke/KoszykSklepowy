package KoszykSklepowyApi.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Products_List")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long itemId;

    @NotNull(message = "Product name is required.")
    private String name;
    private double price;
}


