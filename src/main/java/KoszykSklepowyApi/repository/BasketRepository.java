package KoszykSklepowyApi.repository;

import KoszykSklepowyApi.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket,Long> {

    Optional<List<Basket>> findByStatus(String status);
    Optional<Basket>findById(long Id);

}
