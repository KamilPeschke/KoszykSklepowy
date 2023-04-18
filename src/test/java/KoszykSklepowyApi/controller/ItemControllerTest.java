package KoszykSklepowyApi.controller;

import KoszykSklepowyApi.model.Item;
import KoszykSklepowyApi.response.ItemResponse;
import KoszykSklepowyApi.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemService itemService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldCreateAndReturnListOfItems() throws Exception{

        mockMvc.perform(post("/api/item")
                .content(objectMapper.writeValueAsBytes(ItemResponse.builder()
                        .itemId(3L)
                        .price(42.5)
                        .name("cheese")
                        .build()))
                .contentType("application/json"))
                .andExpect(status().isCreated());

        MvcResult mvcResult = mockMvc.perform(get("/api/item"))
                .andExpect(status().isOk())
                .andReturn();

        var items = Arrays.asList(
                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Item[].class));

        Assertions.assertEquals(1, items.size());
    }

    @Test
    void shouldDeleteItem()throws Exception{

        var itemsBeforeDelete = itemService.getAllItems().size();

        mockMvc.perform(delete("/api/item/delete/1"))
                .andExpect(status().isOk())
                .andReturn();

        var itemsAfterDelete = itemService.getAllItems().size();

        Assertions.assertEquals(itemsBeforeDelete -1, itemsAfterDelete);
    }
}
