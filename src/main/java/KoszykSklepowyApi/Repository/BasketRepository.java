package KoszykSklepowyApi.Repository;

import KoszykSklepowyApi.Model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket,Long> {

    Optional<List<Basket>> findByStatus(String status);

}
