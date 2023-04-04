package KoszykSklepowyApi.Controller;
import KoszykSklepowyApi.Model.Item;
import KoszykSklepowyApi.Repository.ItemRepository;
import KoszykSklepowyApi.request.CreateBasketRequest;
import KoszykSklepowyApi.request.CreateItemRequest;
import KoszykSklepowyApi.request.UpdateBasketRequest;
import KoszykSklepowyApi.response.BasketResponse;
import KoszykSklepowyApi.response.ItemResponse;
import KoszykSklepowyApi.service.BasketService;
import KoszykSklepowyApi.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api")
public class BasketController {

    private final BasketService basketService;
    private final ItemService itemService;

    List<Item> itemList = new ArrayList<>();

    public BasketController(BasketService basketService, ItemService itemService) {
        this.basketService = basketService;
        this.itemService = itemService;
    }


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
    @PostMapping("/item")
    public ResponseEntity<ItemResponse> addItem(@Valid @RequestBody CreateItemRequest createItemRequest){
        return new ResponseEntity<>(itemService.addItem(createItemRequest), HttpStatus.CREATED);
    }

    @PutMapping("/basket/{id}")
    public ResponseEntity<BasketResponse> updateBasket(@RequestBody UpdateBasketRequest updateBasketRequest, @PathVariable Long id){
        return basketService.updateBasket(updateBasketRequest, id).map(m -> new ResponseEntity<>(new BasketResponse(m), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/basket")
    public ResponseEntity<BasketResponse> addBasket(@Valid @RequestBody CreateBasketRequest createBasketRequest){
        return new ResponseEntity<>(basketService.createBasket(createBasketRequest), HttpStatus.CREATED);
    }

    @GetMapping("/basket")
    public ResponseEntity<List<BasketResponse>> getAllBaskets(){
        return new ResponseEntity<>(basketService.getAllBaskets(), HttpStatus.OK);
    }

}
