package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static ru.iteco.fmhandroid.ui.data.DataHelper.waitForElement;
import static ru.iteco.fmhandroid.ui.data.DataHelper.withIndex;
import static ru.iteco.fmhandroid.ui.screenElements.MainElements.getNewsDescriptionText;
import static ru.iteco.fmhandroid.ui.screenElements.NewsElements.getNewsTitleText;
import static ru.iteco.fmhandroid.ui.screenElements.PanelElements.addNewsButton;
import static ru.iteco.fmhandroid.ui.screenElements.PanelElements.filterNewsButton;
import static ru.iteco.fmhandroid.ui.screenElements.PanelElements.getButtonDeleteNews;
import static ru.iteco.fmhandroid.ui.screenElements.PanelElements.getButtonEditNews;
import static ru.iteco.fmhandroid.ui.screenElements.PanelElements.getNewsPublicationDate;
import static ru.iteco.fmhandroid.ui.screenElements.PanelElements.newsDescriptionText;
import static ru.iteco.fmhandroid.ui.screenElements.PanelElements.newsList;
import static ru.iteco.fmhandroid.ui.screenElements.PanelElements.newsTitleText;
import static ru.iteco.fmhandroid.ui.screenElements.PanelElements.panelName;
import static ru.iteco.fmhandroid.ui.screenElements.PanelElements.sortNewsButton;
import static ru.iteco.fmhandroid.ui.screenElements.PanelElements.statusActive;
import static ru.iteco.fmhandroid.ui.screenElements.PanelElements.statusNotActive;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.data.DataHelper;

public class PanelSteps {

    public void checkPanelElements() {
        Allure.step("Проверка отображения всех элементов на экране 'Панель управления'");
        panelName.check(matches(isDisplayed()));
        sortNewsButton.check(matches(isDisplayed()));
        filterNewsButton.check(matches(isDisplayed()));
        addNewsButton.check(matches(isDisplayed()));
    }

    public String getFirstNewsDate(int index) {
        Allure.step("Получение даты первой новости");
        return DataHelper.getText(onView(withIndex(getNewsPublicationDate(), index)));
    }

    public String getFirstNewsDateAfterSecondSort(int index) {
        Allure.step("Получение даты первой новости после двойной сортировки");
        return DataHelper.getText(onView(withIndex(getNewsTitleText(), index)));

    }

    public void checkPanelSort() {
        Allure.step("Проверка сортировки новостей");
        String firstNewsPublication = getFirstNewsDate(0);
        clickSortNewsButton();
        newsList.perform(swipeDown());
        clickSortNewsButton();
        newsList.perform(swipeDown());
        String firstNewsPublicationAfterSecondSorting = getFirstNewsDateAfterSecondSort(0);
        assertEquals(firstNewsPublication, firstNewsPublicationAfterSecondSorting);
    }

    public void clickSortNewsButton() {
        Allure.step("Нажатие кнопки сортировки новостей");
        sortNewsButton.perform(click());
    }

    public void openNewsFilter() {
        Allure.step("Открытие фильтра новостей");
        filterNewsButton.perform(click());
    }

    public void clickCreateNewsButton() {
        Allure.step("Нажатие кнопки создания новости");
        addNewsButton.perform(click());
    }

    public void clickEditNews(int index) {
        Allure.step("Нажатие кнопки редактирования новости");
        onView(withIndex(getButtonEditNews(), index)).perform(click());
        onView(isRoot()).perform(waitForElement(withText("Editing"), 5000));
    }

    public void clickDeleteNews(int index) {
        Allure.step("Нажатие кнопки удаления новости");
        onView(withIndex(getButtonDeleteNews(), index)).perform(click());
        onView(isRoot()).perform(waitForElement(withText("OK"), 10000));
    }

    public void clickOneNewsItem(int index) {
        Allure.step("Нажатие на новость из списка");
        newsList.perform(actionOnItemAtPosition(index, click()));
    }

    public String getEditNewsTitle(int index) {
        Allure.step("Получение заголовка редактируемой новости");
        return DataHelper.getText(onView(withIndex(getNewsTitleText(), index)));

    }

    public String getEditNewsDescription(int index) {
        Allure.step("Получение описания редактируемой новости");
        return DataHelper.getText(onView(withIndex(getNewsDescriptionText(), index)));
    }

    public void checkStatusNotActive() {
        Allure.step("Проверка статуса новости 'Не активна'");
        statusNotActive.check(matches(withText("Not active")));
    }

    public void checkStatusActive() {
        Allure.step("Проверка статуса новости 'Активна'");
        statusActive.check(matches(withText("Active")));
    }

    public void checkCreateNews(int position, String titleText, String descriptionText) {
        Allure.step("Проверка заголовка и содержания созданной новости");
        newsList.perform(actionOnItemAtPosition(position, click()));
        onView(isRoot()).perform(waitForElement(getNewsDescriptionText(), 10000));
        newsTitleText.check(matches(withText(titleText)));
        newsDescriptionText.check(matches(withText(descriptionText)));
    }
}
