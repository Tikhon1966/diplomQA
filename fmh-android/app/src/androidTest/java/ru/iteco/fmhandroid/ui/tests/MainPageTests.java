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
import ru.iteco.fmhandroid.ui.steps.AboutSteps;
import ru.iteco.fmhandroid.ui.steps.AuthSteps;
import ru.iteco.fmhandroid.ui.steps.LoveSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.NewsSteps;
import ru.iteco.fmhandroid.ui.steps.SplashSteps;

@RunWith(AllureAndroidJUnit4.class)
public class MainPageTests {
    AuthSteps authSteps = new AuthSteps();
    AboutSteps aboutSteps = new AboutSteps();
    MainSteps mainSteps = new MainSteps();
    NewsSteps newsSteps = new NewsSteps();
    LoveSteps loveSteps = new LoveSteps();
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
        }
    }

    @Test
    @DisplayName("Открытие новости из списка")
    @Description("Проверка открытия и отображения описания новости при нажатии на неё")
    public void shouldVerifyNewsItemExpansion() {
        mainSteps.expandSpecificNews(0);
        mainSteps.verifyNewsDescriptionDisplayed(0);
    }


    @Test
    @DisplayName("Проверка отображения разделов меню")
    @Description("Проверка, что в меню отображаются разделы Main, News и About")
    public void shouldVerifyMenuScreenList() {
        mainSteps.clickMenuButton();
        mainSteps.verifyMenuList();
    }

    @Test
    @DisplayName("Разворачивание и сворачивание блока новостей")
    @Description("Проверка сворачивания и разворачивания блока новостей при нажатии на него")
    public void shouldVerifyExpandAndCollapseNewsBlock() {
        mainSteps.toggleNewsSection();
        mainSteps.verifyNewsSectionCollapsed();
        mainSteps.toggleNewsSection();
        mainSteps.verifyNewsSectionDisplayed();
    }

    @Test
    @DisplayName("Переход на экран 'Любовь — это всё'")
    @Description("Проверка перехода на экран 'Любовь — это всё' из главного экрана")
    public void shouldVerifyGoToLoveIsAllScreen() {
        mainSteps.clickMissionButton();
        loveSteps.verifyMissionElementsAreDisplayed();
    }

    @Test
    @DisplayName("Переход на экран новостей и возврат на главный экран")
    @Description("Проверка перехода на экран новостей через кнопку 'All News' и возврат на главный экран")
    public void shouldVerifyAllNewsButton() {
        mainSteps.clickAllNewsButton();
        newsSteps.verifyNewsScreenElements();
        mainSteps.navigateToMain();
        mainSteps.verifyMainScreenElements();
    }

    @Test
    @DisplayName("Переход на экран новостей через меню")
    @Description("Проверка перехода на экран новостей через пункт 'News' в боковом меню")
    public void shouldVerifyGoToNews() {
        mainSteps.navigateToNews();
        newsSteps.verifyNewsScreenElements();
    }

    @Test
    @DisplayName("Переход на экран 'О приложении' через экран новостей")
    @Description("Проверка перехода на экран 'О приложении' через пункт 'News' в боковом меню и затем 'About'")
    public void shouldVerifyGoToAboutThroughNews() {
        mainSteps.navigateToNews();
        newsSteps.verifyNewsScreenElements();
        mainSteps.navigateToAbout();
        aboutSteps.verifyAboutScreenElements();
    }


}
