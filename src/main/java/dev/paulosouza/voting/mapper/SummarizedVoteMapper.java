package dev.paulosouza.voting.mapper;


import dev.paulosouza.voting.dto.response.SummarizedVoteResponse;
import dev.paulosouza.voting.model.SummarizedVote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SummarizedVoteMapper {

    SummarizedVoteMapper INSTANCE = Mappers.getMapper(SummarizedVoteMapper.class);

    @Mapping(target = "username", source = "id.option.username")
    @Mapping(target = "pollId", source = "id.poll.id")
    SummarizedVoteResponse toResponse(SummarizedVote entity);

}
