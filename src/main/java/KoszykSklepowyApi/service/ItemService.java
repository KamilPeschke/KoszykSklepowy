package KoszykSklepowyApi.service;


import KoszykSklepowyApi.Model.Item;
import KoszykSklepowyApi.Repository.ItemRepository;
import KoszykSklepowyApi.request.CreateItemRequest;
import KoszykSklepowyApi.response.ItemResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;

@Service
public class ItemService {

    private final ItemRepository itemRepository;


    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemResponse addItem(final CreateItemRequest createItemRequest){
        return new ItemResponse(itemRepository.save(Item.builder()
                .name(createItemRequest.getName())
                .price(createItemRequest.getPrice())
                .itemId(createItemRequest.getItemId())
                .build()));
    }

    public List<ItemResponse>getAllItems(){
        List<ItemResponse> itemList = new ArrayList<>();
        itemRepository.findAll().forEach(item -> itemList.add(new ItemResponse(item)));
        return itemList;
        }
    }

