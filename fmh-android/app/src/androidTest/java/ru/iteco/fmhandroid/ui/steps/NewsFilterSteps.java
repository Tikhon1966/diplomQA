package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static ru.iteco.fmhandroid.ui.screenElements.FilterNewsElements.categoryText;
import static ru.iteco.fmhandroid.ui.screenElements.FilterNewsElements.dateEnd;
import static ru.iteco.fmhandroid.ui.screenElements.FilterNewsElements.dateStart;
import static ru.iteco.fmhandroid.ui.screenElements.FilterNewsElements.filterActive;
import static ru.iteco.fmhandroid.ui.screenElements.FilterNewsElements.filterButton;
import static ru.iteco.fmhandroid.ui.screenElements.FilterNewsElements.filterNews;
import static ru.iteco.fmhandroid.ui.screenElements.FilterNewsElements.filterNotActive;

import io.qameta.allure.kotlin.Allure;

public class NewsFilterSteps {
    public void verifyFilterScreenElements() {
        Allure.step("Проверка элементов экрана - Фильтр");
        filterNews.check(matches(isDisplayed()));
        categoryText.check(matches(isDisplayed()));
        dateStart.check(matches(isDisplayed()));
        dateEnd.check(matches(isDisplayed()));
        filterButton.check(matches(isClickable()));
    }

    public void applyFilter() {
        Allure.step("Применить фильтр");
        filterButton.perform(click());
    }

    public void enterStartDate(String startDate) {
        Allure.step("Ввод начальной даты");
        dateStart.perform(replaceText(startDate));
    }

    public void enterEndDate(String endDate) {
        Allure.step("Ввод конечной даты");
        dateEnd.perform(replaceText(endDate));
    }

    public void toggleActiveCheckBox() {
        Allure.step("Переключить чекбокс - Active");
        filterActive.perform(click());
    }

    public void toggleNotActiveCheckBox() {
        Allure.step("Переключить чекбокс - Not active");
        filterNotActive.perform(click());
    }

    public void verifyActiveCheckBoxStatus(boolean checked) {
        Allure.step("Проверка состояния чекбокса - Active");
        if (checked) {
            filterActive.check(matches(isChecked()));
        } else {
            filterActive.check(matches(isNotChecked()));
        }
    }

    public void verifyNotActiveCheckBoxStatus(boolean checked) {
        Allure.step("Проверка состояния чекбокса - Not active");
        if (checked) {
            filterNotActive.check(matches(isChecked()));
        } else {
            filterNotActive.check(matches(isNotChecked()));
        }
    }
}
