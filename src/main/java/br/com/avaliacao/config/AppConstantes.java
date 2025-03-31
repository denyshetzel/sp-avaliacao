package br.com.avaliacao.config;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class AppConstantes {

    @UtilityClass
    public static final class PATHS {

        @UtilityClass
        public static final class V1 {
            public static final String V1 = "/v1";
            public static final String UNIDADES = V1 + "/unidades";

            public static final String AUTH = "/api/auth";
        }
    }

}
