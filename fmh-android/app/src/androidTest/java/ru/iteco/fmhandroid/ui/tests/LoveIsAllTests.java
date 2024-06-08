package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.data.DataHolder;
import ru.iteco.fmhandroid.ui.steps.AuthSteps;
import ru.iteco.fmhandroid.ui.steps.LoveSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.SplashSteps;

@RunWith(AllureAndroidJUnit4.class)
public class LoveIsAllTests {

    AuthSteps authSteps = new AuthSteps();
    LoveSteps loveSteps = new LoveSteps();
    MainSteps mainSteps = new MainSteps();
    SplashSteps splashSteps = new SplashSteps();
    DataHolder dataHolder = new DataHolder();

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
            mainSteps.clickMissionButton();
        }
    }

    @Test
    @DisplayName("Проверка отображения элементов экрана 'Любовь — это всё'")
    @Description("Проверка корректности отображения всех элементов экрана 'Любовь — это всё'")
    public void shouldVerifyLoveIsAllScreenElements() {
        loveSteps.verifyMissionElementsAreDisplayed();
    }

    @Test
    @DisplayName("Разворачивание и сворачивание цитаты в разделе 'Любовь — это всё'")
    @Description("При нажатии на цитату происходит её разворачивание, при повторном нажатии — сворачивание")
    public void shouldVerifyQuoteExpandAndCollapse() {
        loveSteps.toggleQuoteByIndex(1);

        loveSteps.verifyQuoteDescriptionIsDisplayed(dataHolder.quoteText);
        loveSteps.toggleQuoteByIndex(1);
        loveSteps.verifyQuoteDescriptionIsNotDisplayed(dataHolder.quoteText);
    }
}
