package KoszykSklepowyApi.controller;


import KoszykSklepowyApi.Model.Basket;
import KoszykSklepowyApi.Model.Item;
import KoszykSklepowyApi.Repository.BasketRepository;
import KoszykSklepowyApi.Repository.ItemRepository;
import KoszykSklepowyApi.request.CreateBasketRequest;
import KoszykSklepowyApi.request.CreateItemRequest;
import KoszykSklepowyApi.request.UpdateBasketRequest;
import KoszykSklepowyApi.response.BasketResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BasketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private BasketRepository basketRepository;

    private final static Long BASKETID = Long.valueOf("2");
    private final static String STATUS = "APPROVED";
    private final static double SUM = 35.6;
    private final static List<Item> ITEM_LIST = new ArrayList<>();


    private final static Item DEFAULT_ITEM = new Item(1, "name", 2.4);
    private final static String NAME = "cukier";
    private final static double PRICE = 4.5;


    @BeforeEach
    public void cleanUp() {
        itemRepository.deleteAll();
        basketRepository.deleteAll();
    }

    ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void shouldUpdateBasket() throws Exception {

        basketRepository.save(Basket.builder()
                .basketId(1L)
                .sum(43.5)
                .status("ok")
                .itemList((List.of(DEFAULT_ITEM)))
                .build());

        final Long changeBasketId = Long.valueOf("4");
        final double changeSum = 55.6;
        final String changeStatus = "WAITING";
        List<Item> changeItemList1 = new ArrayList<>();


        mockMvc.perform(put("/api/basket/1")
                        .content(objectMapper.writeValueAsBytes(UpdateBasketRequest.builder()
                                .sum(changeSum)
                                .status(changeStatus)
                                .itemList(changeItemList1)
                                .build()))
                        .contentType("application/json"))
                .andExpect(status().isOk());

//        MvcResult mvcResult = mockMvc.perform(get("/api/basket"))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        var result = Arrays.asList(objectMapper.readValue(mvcResult
//                .getResponse()
//                .getContentAsString(), BasketResponse[].class));
//
//        Assertions.assertEquals(1, result.size());
//        Assertions.assertEquals(changeSum, result.get(0).getSum());
//        Assertions.assertEquals(changeStatus, result.get(0).getStatus());
//        Assertions.assertEquals(changeItemList1, result.get(0).getItemList());

    }

    @Test
    void shouldAddBasket() throws Exception {
        mockMvc.perform(post("/api/basket")
                        .content(objectMapper.writeValueAsBytes(CreateBasketRequest
                                .builder()
                                .itemList(List.of(DEFAULT_ITEM))
                                .basketId(1L)
                                .sum(35.4)
                                .status("ok")
                                .build())).contentType("application/json"))
                .andExpect(status().isCreated());

      //  MvcResult mvcResult = basketRepository.getClass().


//        = objectMapper.readValue(mvcResult
//                .getResponse()
//                .getContentAsString(), BasketResponse.class);
//
//       Assertions.assertEquals(ITEM_LIST, .getItemList());
//       Assertions.assertEquals(BASKETID, .getBasketId());
//       Assertions.assertEquals(SUM, .getSum());
//       Assertions.assertEquals(STATUS, .getStatus());

    }
}