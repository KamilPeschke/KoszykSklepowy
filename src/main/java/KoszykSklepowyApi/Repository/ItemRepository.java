package KoszykSklepowyApi.Repository;

import KoszykSklepowyApi.Model.Item;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item,Long> {

}
