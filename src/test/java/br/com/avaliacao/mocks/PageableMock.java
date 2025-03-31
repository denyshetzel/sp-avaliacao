package br.com.avaliacao.mocks;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@UtilityClass
public class PageableMock {

    public static Pageable pageable(){
        return PageRequest.of(0, 10);
    }

    public static <T> Page<T> createPage(List<T> list) {
        return new PageImpl<>(list);
    }

}
