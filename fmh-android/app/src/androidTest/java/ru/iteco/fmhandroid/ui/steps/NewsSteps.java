package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.data.DataHelper.elementWaiting;
import static ru.iteco.fmhandroid.ui.data.DataHelper.withIndex;
import static ru.iteco.fmhandroid.ui.screenElements.NewsElements.allNewsBlock;
import static ru.iteco.fmhandroid.ui.screenElements.NewsElements.childNewsButton;
import static ru.iteco.fmhandroid.ui.screenElements.NewsElements.editNewsButton;
import static ru.iteco.fmhandroid.ui.screenElements.NewsElements.filterNewsButton;
import static ru.iteco.fmhandroid.ui.screenElements.NewsElements.getDescriptionTextNewsView;
import static ru.iteco.fmhandroid.ui.screenElements.NewsElements.getNewsMainList;
import static ru.iteco.fmhandroid.ui.screenElements.NewsElements.getNewsTitleText;
import static ru.iteco.fmhandroid.ui.screenElements.NewsElements.newsName;
import static ru.iteco.fmhandroid.ui.screenElements.NewsElements.sortNewsButton;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.assertion.ViewAssertions;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.data.DataHolder;

public class NewsSteps {
    DataHolder dataHolder = new DataHolder();

    public void loadNewsList() {
        Allure.step("Загрузка списка новостей");
        elementWaiting(getNewsMainList(), 10000);
    }

    public void verifyNewsScreenElements() {
        Allure.step("Элементы экрана - 'Новости'");
        newsName.check(matches(isDisplayed()));
        allNewsBlock.check(matches(isDisplayed()));
    }

    public void openNewsFilter() {
        Allure.step("Открыть фильтр");
        filterNewsButton.check(matches(isDisplayed()));
        filterNewsButton.perform(click());
    }

    public void clickSortNewsButton() {
        Allure.step("Нажать - сортировку");
        sortNewsButton.check(matches(isDisplayed()));
        sortNewsButton.perform(click());
    }

    public void clickEditNewsButton() {
        Allure.step("Нажать - редактирование");
        editNewsButton.check(matches(isDisplayed()));
        editNewsButton.perform(click());
    }

    public void expandNewsItem(int position) {
        Allure.step("Развернуть выбранную новость");
        childNewsButton.perform(actionOnItemAtPosition(position, click()));
    }

    public String getFirstNewsTitle(int index) {
        Allure.step("Заголовок первой новости");
        return DataHelper.getText(onView(withIndex(getNewsTitleText(), index)));
    }

    public String getFirstNewsTitleAfterSecondSort(int index) {
        Allure.step("Заголовок первой новости после второй сортировки");
        return DataHelper.getText(onView(withIndex(getNewsTitleText(), index)));
    }

    public String getCreatedNewsDescription(int index) {
        Allure.step("Описание созданной новости");
        return DataHelper.getText(onView(withIndex(getDescriptionTextNewsView(), index)));
    }

    public boolean isNewsDeleted(String title, String description) {
        try {
            onView(withText(title)).check(ViewAssertions.doesNotExist());
            onView(withText(description)).check(ViewAssertions.doesNotExist());
            return true;
        } catch (NoMatchingViewException e) {
            return false;
        }
    }

}
