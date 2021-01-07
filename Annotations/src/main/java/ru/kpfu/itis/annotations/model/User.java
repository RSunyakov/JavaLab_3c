package ru.kpfu.itis.annotations.model;

import ru.kpfu.itis.annotations.annotation.HtmlForm;
import ru.kpfu.itis.annotations.annotation.HtmlInput;

@HtmlForm(method = "POST", action = "/test")
public class User {
    @HtmlInput(name = "login", placeholder = "Логин")
    private String login;
    @HtmlInput(name = "email", placeholder = "Email")
    private String email;
    @HtmlInput(name = "firstName", placeholder = "Имя")
    private String firstName;
    @HtmlInput(name = "lastName", placeholder = "Фамилия")
    private String lastName;
    @HtmlInput(type = "password", name = "password", placeholder = "Пароль")
    private String password;
}
