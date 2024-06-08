package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static ru.iteco.fmhandroid.ui.screenElements.EditNewsElements.editingName;
import static ru.iteco.fmhandroid.ui.screenElements.EditNewsElements.newsCategoryText;
import static ru.iteco.fmhandroid.ui.screenElements.EditNewsElements.newsDescriptionText;
import static ru.iteco.fmhandroid.ui.screenElements.EditNewsElements.newsPublishTimeText;
import static ru.iteco.fmhandroid.ui.screenElements.EditNewsElements.newsTitleEditText;
import static ru.iteco.fmhandroid.ui.screenElements.EditNewsElements.switcher;

import io.qameta.allure.kotlin.Allure;

public class NewsEditSteps {
    public void verifyEditNewsScreenElements() {
        Allure.step("Проверка элементов экрана - Редактирование новости");
        editingName.check(matches(isDisplayed()));
        newsCategoryText.check(matches(isDisplayed()));
        newsTitleEditText.check(matches(isDisplayed()));
        newsPublishTimeText.check(matches(isDisplayed()));
        newsDescriptionText.check(matches(isDisplayed()));
        switcher.check(matches(isDisplayed()));
    }

    public void toggleNewsStatus() {
        Allure.step("Переключение статуса новости");
        switcher.perform(click());
    }

    public void updateNewsTitle(String text) {
        Allure.step("Обновление заголовка новости");
        newsTitleEditText.perform(replaceText(text));
    }

    public void updateNewsDescription(String text) {
        Allure.step("Обновление описания новости");
        newsDescriptionText.perform(replaceText(text));
    }
}
