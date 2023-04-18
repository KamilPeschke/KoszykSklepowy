package KoszykSklepowyApi.model;

import KoszykSklepowyApi.model.Basket;
import KoszykSklepowyApi.response.BasketResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface BasketMapper {

    @Mapping(target = "sum", ignore = true)
    BasketResponse basketresponse(Basket basket);
}
