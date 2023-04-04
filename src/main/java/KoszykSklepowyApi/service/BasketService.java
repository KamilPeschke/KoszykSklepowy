package KoszykSklepowyApi.service;

import KoszykSklepowyApi.Model.Basket;
import KoszykSklepowyApi.Model.Item;
import KoszykSklepowyApi.Repository.BasketRepository;
import KoszykSklepowyApi.Repository.ItemRepository;
import KoszykSklepowyApi.request.CreateBasketRequest;
import KoszykSklepowyApi.request.UpdateBasketRequest;
import KoszykSklepowyApi.response.BasketResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BasketService {

    private final BasketRepository basketRepository;
    private final ItemRepository itemRepository;

    public BasketService(BasketRepository basketRepository, ItemRepository itemRepository) {
        this.basketRepository = basketRepository;
        this.itemRepository = itemRepository;
    }

    private Basket saveBasket(Basket basket) {
        return basketRepository.save(basket);
    }

    public List<BasketResponse> getAllBaskets(){
        List<BasketResponse> basketResponses = new ArrayList<>();
        basketRepository.findAll().forEach(basket -> basketResponses.add(new BasketResponse(basket)));
        return basketResponses;
    }

    public BasketResponse createBasket(final CreateBasketRequest createBasketRequest) {
        return new BasketResponse(basketRepository.save(Basket.builder()
                .basketId(createBasketRequest.getBasketId())
                .itemList(createBasketRequest.getItemList())
                .status(createBasketRequest.getStatus())
                .sum(getTotalSum(createBasketRequest.getItemList()))
                .build()));
    }

    public Optional<Basket> updateBasket(UpdateBasketRequest updateBasketRequest, Long id) {
        return basketRepository.findById(id).
                map(basket -> updateBasket(basket, updateBasketRequest));
    }

    private Basket updateBasket(Basket basket, UpdateBasketRequest updateBasketRequest) {
        basket.setItemList(updateBasketRequest.getItemList());
        basket.setSum(getTotalSum(updateBasketRequest.getItemList()));
        basket.setStatus(updateBasketRequest.getStatus());
        return saveBasket(basket);
    }

    private double getTotalSum(List<Item> itemList) {
        double sum = 0;
        for (Item item : itemList) {
            sum = sum + item.getPrice();
        }
        return sum;
    }

    public Optional<BasketResponse> findById(long id) {
        return basketRepository.findById(id).map(BasketResponse::new);
    }

    public List<BasketResponse> findByStatus(String status) {
        List<BasketResponse> basketResponses = new ArrayList<>();
        basketRepository.findByStatus(status)
                .map(t -> {
                    t.forEach(e -> basketResponses.add(BasketResponse.builder()
                            .itemList(e.getItemList())
                            .sum(e.getSum())
                            .basketId(e.getBasketId())
                            .status(e.getStatus())
                            .build()));
                    return basketResponses;
                });
        return basketResponses;
    }

    public boolean deleteBasket(Long id) {
        return basketRepository.findById(id).map(basket -> {
            basketRepository.delete(basket);
            return true;
        }).orElse(false);
    }

}



