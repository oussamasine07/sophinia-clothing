package com.sophinia.backend.service;

import com.sophinia.backend.repository.DecorationRepository;
import org.springframework.stereotype.Service;

@Service
public class DecorationService {

    private final DecorationRepository decorationRepository;

    public DecorationService (
            final DecorationRepository decorationRepository
    ) {
        this.decorationRepository = decorationRepository;
    }

    public void index () {

    }

    public void show () {

    }

    public void create () {

    }

    public void update () {

    }

    public void delete () {

    }

}
