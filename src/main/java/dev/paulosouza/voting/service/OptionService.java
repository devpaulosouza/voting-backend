package dev.paulosouza.voting.service;

import dev.paulosouza.voting.dto.request.OptionRequest;
import dev.paulosouza.voting.dto.response.OptionResponse;
import dev.paulosouza.voting.exception.NotFoundException;
import dev.paulosouza.voting.mapper.OptionMapper;
import dev.paulosouza.voting.model.Option;
import dev.paulosouza.voting.repository.OptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OptionService {

    private final OptionRepository repository;

    public OptionResponse create(OptionRequest request) {
        Option entity = OptionMapper.INSTANCE.toEntity(request);

        this.repository.save(entity);

        return OptionMapper.INSTANCE.toResponse(entity);
    }

    public OptionResponse find(String username) {
        Option entity = this.getById(username);

        return OptionMapper.INSTANCE.toResponse(entity);
    }

    public Page<OptionResponse> findAll(Pageable pageable) {
        Page<Option> entities = this.repository.findAll(pageable);

        return entities.map(OptionMapper.INSTANCE::toResponse);
    }

    public void delete(String username) {
        Option entity = this.getById(username);

        this.repository.delete(entity);
    }

    private Option getById(String username) {
        return this.repository.findById(username)
                .orElseThrow(() -> new NotFoundException(username));
    }

}
