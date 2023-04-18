package KoszykSklepowyApi.model;

import KoszykSklepowyApi.response.ItemResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ItemResponse itemResponse(Item item);
}
