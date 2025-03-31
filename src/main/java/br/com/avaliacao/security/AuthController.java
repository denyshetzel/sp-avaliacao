package br.com.avaliacao.security;

import br.com.avaliacao.config.AppConstantes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping(AppConstantes.PATHS.V1.AUTH)
public class AuthController {

    private final JwtTokenProviderService tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestParam String username, @RequestParam String password) {
        String token = tokenProvider.generateToken(username);
        //return ResponseEntity.ok(new JwtAuthenticationResponse(token));
        return ResponseEntity.ok(token);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestParam String token) {
        if (tokenProvider.validateToken(token)) {
            String username = tokenProvider.getUsernameFromJWT(token);
            String newToken = tokenProvider.generateToken(username);
            //return ResponseEntity.ok(new JwtAuthenticationResponse(newToken));
            return ResponseEntity.ok(newToken);
        }
        return ResponseEntity.badRequest().body("Token inválido");
    }
}