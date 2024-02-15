package dev.paulosouza.voting.mapper;


import dev.paulosouza.voting.dto.request.PollRequest;
import dev.paulosouza.voting.dto.response.PollResponse;
import dev.paulosouza.voting.dto.response.ResumedPollResponse;
import dev.paulosouza.voting.model.Poll;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { OptionMapper.class, SummarizedVoteMapper.class })
public interface PollMapper {

    PollMapper INSTANCE = Mappers.getMapper(PollMapper.class);

    Poll toEntity(PollRequest request);

    PollResponse toResponse(Poll entity);

    ResumedPollResponse toResumedResponse(Poll entity);

    Poll update(@MappingTarget Poll entity, PollRequest request);

}
