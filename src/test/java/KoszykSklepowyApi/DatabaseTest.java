package KoszykSklepowyApi;

import KoszykSklepowyApi.Model.Item;
import KoszykSklepowyApi.Repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class DatabaseTest {

    @Autowired
  private ItemRepository itemRepository;

    @BeforeEach
    private void cleanUp(){
        itemRepository.deleteAll();
    }

    @Test
    public void initializationDB(){
        itemRepository.save(Item.builder()
                .name("cukier")
                .price(32.5)
                .build());
        assertFalse(itemRepository.findAll().isEmpty());
    }
}
