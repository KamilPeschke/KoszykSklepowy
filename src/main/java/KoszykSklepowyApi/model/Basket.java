package KoszykSklepowyApi.model;


import jakarta.persistence.*;
import lombok.*;
import org.aspectj.weaver.ast.Or;

import java.util.List;



@Setter
@Getter
@AllArgsConstructor
@Entity
@Builder
@NoArgsConstructor
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long basketId;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "basket_id")
    private List<Item> itemList;

    private String status;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private double sum;

}



