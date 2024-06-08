package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.steps.AboutSteps;
import ru.iteco.fmhandroid.ui.steps.AuthSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.SplashSteps;

@RunWith(AllureAndroidJUnit4.class)
public class AboutTests {

    AuthSteps authSteps = new AuthSteps();
    AboutSteps aboutSteps = new AboutSteps();
    MainSteps mainSteps = new MainSteps();
    SplashSteps splashSteps = new SplashSteps();


    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void logoutCheck() {
        splashSteps.appDownload();
        try {
            mainSteps.waitForMainScreenLoad();
        } catch (Exception e) {
            authSteps.loginWithValidCredentials(DataHelper.authInfo());
            authSteps.clickSignInButton();
        } finally {
            mainSteps.waitForMainScreenLoad();
            mainSteps.navigateToAbout();
        }
    }

    @Test
    @DisplayName("Проверка отображения элементов на экране 'О приложении'")
    public void shouldVerifyAboutScreenElements() {
        aboutSteps.verifyAboutScreenElements();
    }

    @Test
    @DisplayName("Проверка возврата на главный экран из экрана 'О приложении'")
    public void shouldVerifyNavigationToMainScreen() {
        aboutSteps.verifyAboutScreenElements();
        aboutSteps.clickReturnButton();
        mainSteps.verifyMainScreenElements();
    }

    @Test
    @DisplayName("Проверка кликабельности ссылок на экране 'О приложении'")
    public void shouldVerifyLinksAreClickable() {
        aboutSteps.verifyPrivacyPolicyLinkIsClickable();
        aboutSteps.verifyTermsOfUseLinkIsClickable();
    }


}
