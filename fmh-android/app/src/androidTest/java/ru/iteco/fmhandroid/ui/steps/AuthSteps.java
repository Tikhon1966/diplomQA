package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static ru.iteco.fmhandroid.ui.data.DataHelper.authInfo;
import static ru.iteco.fmhandroid.ui.data.DataHelper.emptyLogin;
import static ru.iteco.fmhandroid.ui.data.DataHelper.emptyPassword;
import static ru.iteco.fmhandroid.ui.data.DataHelper.invalidAuthData;
import static ru.iteco.fmhandroid.ui.data.DataHelper.invalidLoginData;
import static ru.iteco.fmhandroid.ui.data.DataHelper.invalidPassData;
import static ru.iteco.fmhandroid.ui.screenElements.AuthElements.loginText;
import static ru.iteco.fmhandroid.ui.screenElements.AuthElements.authText;
import static ru.iteco.fmhandroid.ui.screenElements.AuthElements.passText;
import static ru.iteco.fmhandroid.ui.screenElements.AuthElements.signButton;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.data.DataHelper;

public class AuthSteps {

    public void verifyAuthScreenElements() {
        Allure.step("Проверка отображения всех элементов на экране авторизации");
        authText.check(matches(isDisplayed()));
        loginText.check(matches(isDisplayed()));
        passText.check(matches(isDisplayed()));
        signButton.check(matches(isDisplayed()));
    }

    public void loginWithValidCredentials(DataHelper.User info) {
        Allure.step("Авторизация с корректными данными");
        verifyAuthScreenElements();
        loginText.perform(replaceText(authInfo().getLogin()));
        passText.perform(replaceText(authInfo().getPass()));
    }

    public void loginWithInvalidCredentials(DataHelper.User info) {
        Allure.step("Авторизация с некорректными данными");
        verifyAuthScreenElements();
        loginText.perform(replaceText(invalidAuthData().getLogin()));
        passText.perform(replaceText(invalidAuthData().getPass()));
    }

    public void loginWithInvalidUsername(DataHelper.User info) {
        Allure.step("Авторизация с некорректным паролем и корректным логином");
        verifyAuthScreenElements();
        loginText.perform(replaceText(invalidLoginData().getLogin()));
        passText.perform(replaceText(authInfo().getPass()));
    }

    public void loginWithInvalidPassword(DataHelper.User info) {
        Allure.step("Авторизация с невалидным паролем и валидным логином");
        verifyAuthScreenElements();
        loginText.perform(replaceText(authInfo().getLogin()));
        passText.perform(replaceText(invalidPassData().getPass()));
    }

    public void loginWithEmptyPassword(DataHelper.User info) {
        Allure.step("Авторизация с пустым паролем");
        loginText.perform(replaceText(authInfo().getLogin()));
        passText.perform(replaceText(emptyPassword().getPass()));
    }

    public void loginWithEmptyUsername(DataHelper.User info) {
        Allure.step("Авторизация с пустым логином");
        loginText.perform(replaceText(emptyLogin().getLogin()));
        passText.perform(replaceText(authInfo().getPass()));
    }

    public void clickSignInButton() {
        Allure.step("Нажатие кнопки 'Войти'");
        signButton.perform(click());
    }
}
