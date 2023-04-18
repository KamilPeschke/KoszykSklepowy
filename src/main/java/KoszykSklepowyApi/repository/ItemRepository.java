package KoszykSklepowyApi.repository;

import KoszykSklepowyApi.model.Item;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item,Long> {

}
