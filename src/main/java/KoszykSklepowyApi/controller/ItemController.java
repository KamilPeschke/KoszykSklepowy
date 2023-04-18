package KoszykSklepowyApi.controller;


import KoszykSklepowyApi.request.CreateItemRequest;
import KoszykSklepowyApi.response.ItemResponse;
import KoszykSklepowyApi.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping()
    public ResponseEntity<ItemResponse> addItem(@Valid @RequestBody CreateItemRequest createItemRequest){
        return new ResponseEntity<>(itemService.addItem(createItemRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<Boolean> deleteItem(@PathVariable Long itemId){
        return new ResponseEntity<>(itemService.deleteItem(itemId), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ItemResponse>>getAllItems(){
        return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemResponse> getItem(@PathVariable Long itemId){
        return new ResponseEntity<>(itemService.getItemById(itemId), HttpStatus.OK);
    }

}


