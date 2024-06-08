package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.ui.data.DataHelper.elementWaiting;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitForElement;
import static ru.iteco.fmhandroid.ui.data.DataHelper.withIndex;
import static ru.iteco.fmhandroid.ui.screenElements.MainElements.aboutText;
import static ru.iteco.fmhandroid.ui.screenElements.MainElements.allNews;
import static ru.iteco.fmhandroid.ui.screenElements.MainElements.allNewsButton;
import static ru.iteco.fmhandroid.ui.screenElements.MainElements.childNews;
import static ru.iteco.fmhandroid.ui.screenElements.MainElements.getNewsDescriptionText;
import static ru.iteco.fmhandroid.ui.screenElements.MainElements.logoutButton;
import static ru.iteco.fmhandroid.ui.screenElements.MainElements.logoutText;
import static ru.iteco.fmhandroid.ui.screenElements.MainElements.mainText;
import static ru.iteco.fmhandroid.ui.screenElements.MainElements.menuButton;
import static ru.iteco.fmhandroid.ui.screenElements.MainElements.missionButton;
import static ru.iteco.fmhandroid.ui.screenElements.MainElements.newsBlockButton;
import static ru.iteco.fmhandroid.ui.screenElements.MainElements.newsList;
import static ru.iteco.fmhandroid.ui.screenElements.MainElements.newsText;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.data.DataHelper;

public class MainSteps {
    public void waitForMainScreenLoad() {
        Allure.step("Загрузка главного экрана");
        elementWaiting(withText("ALL NEWS"), 10000);
    }

    public void verifyMainScreenElements() {
        Allure.step("Проверка элементов главного экрана");
        allNews.check(matches(isDisplayed()));
    }

    public void toggleNewsSection() {
        Allure.step("Переключение отображения блока новостей");
        newsBlockButton.check(matches(isDisplayed()));
        newsBlockButton.perform(click());
    }

    public void verifyNewsSectionCollapsed() {
        Allure.step("Проверка, что блок новостей свернут");
        allNewsButton.check(matches(not(isDisplayed())));
    }

    public void verifyNewsSectionDisplayed() {
        Allure.step("Проверка отображения блока новостей");
        newsList.check(matches(isDisplayed()));
    }

    public void expandSpecificNews(int position) {
        Allure.step("Раскрытие выбранной новости");
        childNews.perform(actionOnItemAtPosition(position, click()));
    }

    public void verifyNewsDescriptionDisplayed(int position) {
        Allure.step("Проверка отображения описания новости");
        String descriptionText = DataHelper.getText(onView(withIndex(getNewsDescriptionText(), position)));
    }

    public void clickAllNewsButton() {
        Allure.step("Нажатие на кнопку 'All News'");
        allNewsButton.check(matches(isDisplayed()));
        allNewsButton.perform(click());
    }

    public void clickMissionButton() {
        Allure.step("Нажатие на кнопку 'Our Mission'");
        missionButton.check(matches(isDisplayed()));
        missionButton.perform(click());
    }

    public void clickLogoutButton() {
        Allure.step("Нажатие на кнопку 'Выйти'");
        logoutButton.check(matches(isDisplayed()));
        logoutButton.perform(click());
        onView(isRoot()).perform(waitForElement(withText("Log out"), 5000));
        logoutText.check(matches(isDisplayed()));
        logoutText.perform(click());
    }

    public void clickMenuButton() {
        Allure.step("Нажатие на кнопку меню");
        menuButton.check(matches(isDisplayed()));
        menuButton.perform(click());
    }

    public void verifyMenuList() {
        Allure.step("Проверка списка бокового меню");
        mainText.check(matches(isDisplayed()));
        newsText.check(matches(isDisplayed()));
        aboutText.check(matches(isDisplayed()));
    }

    public void navigateToNews() {
        Allure.step("Переход к разделу 'News' в боковом меню");
        menuButton.perform(click());
        newsText.check(matches(isDisplayed()));
        newsText.perform(click());
    }

    public void navigateToAbout() {
        Allure.step("Переход к разделу 'About' в боковом меню");
        menuButton.perform(click());
        aboutText.check(matches(isDisplayed()));
        aboutText.perform(click());
    }

    public void navigateToMain() {
        Allure.step("Переход к разделу 'Main' в боковом меню");
        menuButton.perform(click());
        mainText.check(matches(isDisplayed()));
        mainText.perform(click());
    }
}
