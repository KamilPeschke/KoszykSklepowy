package KoszykSklepowyApi.controller;
import KoszykSklepowyApi.model.Item;
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
@RequestMapping("/api/basket")
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

    @PutMapping("/{id}")
    public ResponseEntity<BasketResponse> updateBasket(@RequestBody UpdateBasketRequest updateBasketRequest, @PathVariable Long id){
        return basketService.updateBasket(updateBasketRequest, id).map(m -> new ResponseEntity<>(new BasketResponse(m), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<BasketResponse> addBasket(){
        return new ResponseEntity<>(basketService.createNewBasket(), HttpStatus.CREATED);
    }

    @GetMapping("/basket/getAllBasket")
    public ResponseEntity<List<BasketResponse>> getAllBaskets(){
        return new ResponseEntity<>(basketService.getAllBaskets(), HttpStatus.OK);
    }

    @GetMapping("/{basketId}")
    public ResponseEntity<BasketResponse> getSingleBasket(@PathVariable Long basketId){
        return new ResponseEntity<>(basketService.getSingleBasket(basketId), HttpStatus.OK);
    }
}
