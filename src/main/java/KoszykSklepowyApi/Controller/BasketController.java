package KoszykSklepowyApi.Controller;
import KoszykSklepowyApi.Model.Item;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/basket")
public class BasketController {

    List<Item> itemList;
    @GetMapping("/all")
    public List<Item> getAll(){
        return itemList;
    }
    @GetMapping
    public Item getById(@RequestParam int index) {
        Optional<Item> first = itemList.stream().
                filter(element -> element.getItemId() == index).findFirst();
        return first.get();
    }
    @PostMapping
    public boolean addItem(@RequestBody Item item, int quantity){
        return itemList.add(item);
    }
}
