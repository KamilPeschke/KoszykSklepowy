package KoszykSklepowyApi.controller;

import KoszykSklepowyApi.model.OrderStatus;
import KoszykSklepowyApi.repository.BasketRepository;
import KoszykSklepowyApi.repository.ItemRepository;
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

    @BeforeEach
    public void cleanUp() {
        itemRepository.deleteAll();
        basketRepository.deleteAll();
    }

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
        void newBasketShouldBeEmpty() throws Exception {

            mockMvc.perform(post("/api/basket"));

            MvcResult mvcResult = mockMvc.perform(get("/api/basket/1"))
                    .andExpect(status().isOk())
                    .andReturn();

            String contentAsString = mvcResult.getResponse().getContentAsString();

            var basket = objectMapper.readValue(contentAsString, BasketResponse.class);

            Assertions.assertEquals(OrderStatus.EMPTY, basket.getOrderStatus());
    }

}

