package KoszykSklepowyApi.service;

import KoszykSklepowyApi.Exception.NotFoundException;
import KoszykSklepowyApi.model.Basket;
import KoszykSklepowyApi.model.BasketMapper;
import KoszykSklepowyApi.model.Item;
import KoszykSklepowyApi.model.OrderStatus;
import KoszykSklepowyApi.repository.BasketRepository;
import KoszykSklepowyApi.repository.ItemRepository;
import KoszykSklepowyApi.request.CreateBasketRequest;
import KoszykSklepowyApi.request.UpdateBasketRequest;
import KoszykSklepowyApi.response.BasketResponse;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BasketService {

    private final BasketRepository basketRepository;
    private final ItemRepository itemRepository;
    private final BasketMapper basketMapper;

    public BasketService(BasketRepository basketRepository, ItemRepository itemRepository, BasketMapper basketMapper) {
        this.basketRepository = basketRepository;
        this.itemRepository = itemRepository;
        this.basketMapper = basketMapper;
    }

    private Basket saveBasket(Basket basket) {
        return basketRepository.save(basket);
    }

    public List<BasketResponse> getAllBaskets(){
        List<BasketResponse> basketResponses = new ArrayList<>();
        basketRepository.findAll().forEach(basket -> basketResponses.add(new BasketResponse(basket)));
        return basketResponses;
    }

    public Optional<Basket> updateBasket(UpdateBasketRequest updateBasketRequest, Long id) {
        return basketRepository.findById(id).
                map(basket -> updateBasket(basket, updateBasketRequest));
    }

    public Basket updateBasket(Basket basket, UpdateBasketRequest updateBasketRequest) {
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

    public boolean deleteBasket(Long id) {
        return basketRepository.findById(id).map(basket -> {
            basketRepository.delete(basket);
            return true;
        }).orElse(false);
    }

    public BasketResponse getSingleBasket(Long basketId){
        return basketRepository.findById(basketId).map(basket ->
                basketMapper.basketresponse(basket).withSum(updateTotalPrice(basket))).orElseThrow(() ->
                new NotFoundException(MessageFormat.format("Order with ID: {0} not found.",basketId)));
    }

    public double updateTotalPrice(Basket basket){
        return getTotalSum(basket.getItemList());
    }

    public BasketResponse createNewBasket(){
        return basketMapper.basketresponse(basketRepository.save(Basket.builder()
                .orderStatus(OrderStatus.EMPTY).build()));
    }
}




