package KoszykSklepowyApi.service;


import KoszykSklepowyApi.Exception.NotFoundException;
import KoszykSklepowyApi.model.Item;
import KoszykSklepowyApi.model.ProductMapper;
import KoszykSklepowyApi.repository.ItemRepository;
import KoszykSklepowyApi.request.CreateItemRequest;
import KoszykSklepowyApi.response.ItemResponse;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ProductMapper productMapper;


    public ItemService(ItemRepository itemRepository, ProductMapper productMapper) {
        this.itemRepository = itemRepository;
        this.productMapper = productMapper;
    }

    public ItemResponse addItem(final CreateItemRequest createItemRequest){
        return new ItemResponse(itemRepository.save(Item.builder()
                .name(createItemRequest.getName())
                .price(createItemRequest.getPrice())
                .itemId(createItemRequest.getItemId())
                .build()));
    }

    public List<ItemResponse>getAllItems(){

        List<Item> items = itemRepository.findAll();
        return items.stream().map(productMapper::itemResponse).toList();
        }

    public ItemResponse getItemById(Long itemId){
        return itemRepository.findById(itemId).map(productMapper::itemResponse)
                .orElseThrow(() -> new NotFoundException(MessageFormat
                        .format("Product with that ID: {0} not found.", itemId)));
    }

    public Boolean deleteItem(Long itemId){
        return itemRepository.findById(itemId).map(item -> {
            itemRepository.delete(item);
            return true;
        }).orElseThrow(() -> new NotFoundException(MessageFormat.format("Product with that ID {0} not found." , itemId)));
    }

}

