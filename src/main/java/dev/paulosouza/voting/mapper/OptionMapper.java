package dev.paulosouza.voting.mapper;

import dev.paulosouza.voting.dto.request.OptionRequest;
import dev.paulosouza.voting.dto.response.OptionResponse;
import dev.paulosouza.voting.model.Option;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OptionMapper {

    OptionMapper INSTANCE = Mappers.getMapper(OptionMapper.class);

    Option toEntity(OptionRequest request);

    OptionResponse toResponse(Option entity);

    Option update(@MappingTarget Option entity, OptionRequest request);

}
