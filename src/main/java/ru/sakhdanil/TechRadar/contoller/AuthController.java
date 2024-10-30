package ru.sakhdanil.TechRadar.contoller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sakhdanil.TechRadar.config.FuncName;
import ru.sakhdanil.TechRadar.models.JwtAuthenticationResponse;
import ru.sakhdanil.TechRadar.models.user.SignInRequest;
import ru.sakhdanil.TechRadar.models.user.SignUpRequest;
import ru.sakhdanil.TechRadar.service.AuthenticationService;


@Hidden
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {
    private final AuthenticationService authenticationService;


    @FuncName(name = "Вход/регистрация")
    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @FuncName(name = "Вход/регистрация")
    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }
}
