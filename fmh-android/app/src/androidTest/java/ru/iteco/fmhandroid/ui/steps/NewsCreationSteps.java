package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static ru.iteco.fmhandroid.ui.screenElements.CreateNewsElements.category;
import static ru.iteco.fmhandroid.ui.screenElements.CreateNewsElements.categoryText;
import static ru.iteco.fmhandroid.ui.screenElements.CreateNewsElements.creating;
import static ru.iteco.fmhandroid.ui.screenElements.CreateNewsElements.description;
import static ru.iteco.fmhandroid.ui.screenElements.CreateNewsElements.descriptionText;
import static ru.iteco.fmhandroid.ui.screenElements.CreateNewsElements.publicationDate;
import static ru.iteco.fmhandroid.ui.screenElements.CreateNewsElements.publishDateText;
import static ru.iteco.fmhandroid.ui.screenElements.CreateNewsElements.timeText;
import static ru.iteco.fmhandroid.ui.screenElements.CreateNewsElements.title;
import static ru.iteco.fmhandroid.ui.screenElements.CreateNewsElements.titleText;

import io.qameta.allure.kotlin.Allure;

public class NewsCreationSteps {
    public void verifyCreateNewsScreenElements() {
        Allure.step("Проверка элементов экрана - Создание новости");
        creating.check(matches(isDisplayed()));
        category.check(matches(isDisplayed()));
        categoryText.check(matches(isDisplayed()));
        titleText.check(matches(isDisplayed()));
        title.check(matches(isDisplayed()));
        publishDateText.check(matches(isDisplayed()));
        publicationDate.check(matches(isDisplayed()));
        timeText.check(matches(isDisplayed()));
        title.check(matches(isDisplayed()));
        descriptionText.check(matches(isDisplayed()));
        description.check(matches(isDisplayed()));
    }

    public void enterNewsCategory(String text) {
        Allure.step("Ввод категории новости");
        categoryText.perform(replaceText(text));
    }

    public void enterNewsTitle(String text) {
        Allure.step("Ввод заголовка новости");
        titleText.perform(replaceText(text));
    }

    public void enterPublicationDate(String text) {
        Allure.step("Ввод даты публикации");
        publishDateText.perform(replaceText(text));
    }

    public void enterPublicationTime(String text) {
        Allure.step("Ввод времени публикации");
        timeText.perform(replaceText(text));
    }

    public void enterNewsDescription(String text) {
        Allure.step("Ввод описания новости");
        descriptionText.perform(replaceText(text));
    }

    public void createNews(String category, String title, String publicationDate,
                           String publicationTime, String description) {
        Allure.step("Создание новости");
        enterNewsCategory(category);
        enterNewsTitle(title);
        enterPublicationDate(publicationDate);
        enterPublicationTime(publicationTime);
        enterNewsDescription(description);
    }
}
