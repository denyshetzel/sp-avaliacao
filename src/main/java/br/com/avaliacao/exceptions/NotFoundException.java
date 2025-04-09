package br.com.avaliacao.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
public class NotFoundException extends RuntimeException {

    public static final String RECURSO_NAO_ENCONTRADO = "Recurso não encontrado: {}";

    public NotFoundException(String message) {
        super(message);
        log.warn(RECURSO_NAO_ENCONTRADO, message);
    }

    public NotFoundException(Integer resourceId) {
        this(String.format(String.format("Recurso %s não encontrado", resourceId)));
    }

}
