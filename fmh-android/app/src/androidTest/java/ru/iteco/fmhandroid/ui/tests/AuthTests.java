package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.data.DataHelper.authInfo;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.screenElements.AuthElements;
import ru.iteco.fmhandroid.ui.steps.AuthSteps;
import ru.iteco.fmhandroid.ui.steps.GeneralSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.SplashSteps;

@RunWith(AllureAndroidJUnit4.class)
public class AuthTests {

    AuthSteps authSteps = new AuthSteps();
    AuthElements authElement = new AuthElements();
    MainSteps mainSteps = new MainSteps();
    GeneralSteps generalSteps = new GeneralSteps();
    SplashSteps splashSteps = new SplashSteps();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void logoutCheck() {
        splashSteps.appDownload();
        try {
            authElement.loadAuthPage();
            authSteps.verifyAuthScreenElements();

        } catch (Exception e) {
            mainSteps.clickLogoutButton();
            authElement.loadAuthPage();
        }
    }

    @Test
    @DisplayName("Проверка отображения элементов на экране авторизации")
    @Description("Элементы экрана авторизации отображаются корректно")
    public void shouldVerifyAuthScreenElements() {
        authSteps.verifyAuthScreenElements();
    }

    @Test
    @DisplayName("Успешная авторизация с валидными данными")
    @Description("После ввода валидных данных происходит переход на главный экран приложения")
    public void shouldVerifyAuthorizationWithValidLoginAndPass() {
        authSteps.loginWithValidCredentials(authInfo());
        authSteps.clickSignInButton();
        mainSteps.waitForMainScreenLoad();
        mainSteps.verifyMainScreenElements();
    }

    @Test
    @DisplayName("Авторизация с невалидными логином и паролем")
    @Description("При вводе невалидных данных логина и пароля появляется сообщение 'Wrong login or password'")
    public void shouldVerifyAuthorizationWithInvalidLoginAndPass() {
        authSteps.loginWithInvalidCredentials(authInfo());
        authSteps.clickSignInButton();
        generalSteps.verifyInvalidAuthDataToast();
    }

    @Test
    @DisplayName("Авторизация с невалидным логином и валидным паролем")
    @Description("При вводе невалидного логина появляется сообщение 'Wrong login or password'")
    public void shouldVerifyAuthorizationWithInvalidLogin() {
        authSteps.loginWithInvalidUsername(authInfo());
        authSteps.clickSignInButton();
        generalSteps.verifyInvalidAuthDataToast();
    }

    @Test
    @DisplayName("Авторизация с валидным логином и невалидным паролем")
    @Description("При вводе невалидного пароля появляется сообщение 'Wrong login or password'")
    public void shouldVerifyAuthorizationWithInvalidPass() {
        authSteps.loginWithInvalidPassword(authInfo());
        authSteps.clickSignInButton();
        generalSteps.verifyInvalidAuthDataToast();
    }

    @Test
    @DisplayName("Авторизация с пустым логином")
    @Description("При авторизации с пустым логином появляется сообщение 'Login and password cannot be empty'")
    public void shouldVerifyAuthorizationWithEmptyLogin() {
        authSteps.loginWithEmptyUsername(authInfo());
        authSteps.clickSignInButton();
        generalSteps.verifyEmptyAuthDataToast();
    }

    @Test
    @DisplayName("Авторизация с пустым паролем")
    @Description("При авторизации с пустым паролем появляется сообщение 'Login and password cannot be empty'")
    public void shouldVerifyAuthorizationWithEmptyPass() {
        authSteps.loginWithEmptyPassword(authInfo());
        authSteps.clickSignInButton();
        generalSteps.verifyEmptyAuthDataToast();
    }

    @Test
    @DisplayName("Авторизация с пустыми логином и паролем")
    @Description("При авторизации с пустыми логином и паролем появляется сообщение 'Login and password cannot be empty'")
    public void shouldVerifyAuthorizationWithEmptyLoginAndPass() {
        authSteps.clickSignInButton();
        generalSteps.verifyEmptyAuthDataToast();
    }

    @Test
    @DisplayName("Выход из учетной записи ")
    @Description("Выход из учетной записи с помощью кнопки 'Log out'")
    public void shouldVerifyLogOutApplication() {
        authSteps.loginWithValidCredentials(authInfo());
        authSteps.clickSignInButton();
        mainSteps.waitForMainScreenLoad();
        mainSteps.verifyMainScreenElements();
        mainSteps.clickLogoutButton();
        authSteps.verifyAuthScreenElements();
    }
}
