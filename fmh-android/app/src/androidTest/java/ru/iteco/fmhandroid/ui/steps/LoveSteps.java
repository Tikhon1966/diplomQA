package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.ui.screenElements.LoveElements.getDescriptionText;
import static ru.iteco.fmhandroid.ui.screenElements.LoveElements.missionConstraintLayout;
import static ru.iteco.fmhandroid.ui.screenElements.LoveElements.missionList;
import static ru.iteco.fmhandroid.ui.screenElements.LoveElements.missionName;

import io.qameta.allure.kotlin.Allure;

public class LoveSteps {
    public void verifyMissionElementsAreDisplayed() {
        Allure.step("Элементы экрана - Love is all");
        missionName.check(matches(isDisplayed()));
        missionList.check(matches(isDisplayed()));
    }

    public void toggleQuoteByIndex(int number) {
        Allure.step("Развернуть/свернуть цитату");
        missionConstraintLayout.check(matches(isDisplayed()));
        missionConstraintLayout.perform(actionOnItemAtPosition(number, click()));
    }

    public void verifyQuoteDescriptionIsNotDisplayed(String text) {
        Allure.step("Скрытие цитаты");
        onView(allOf(getDescriptionText(),
                withText(text))).check(matches(not(isDisplayed())));
    }

    public void verifyQuoteDescriptionIsDisplayed(String text) {
        Allure.step("Отображение цитаты");
        onView(allOf(getDescriptionText(),
                withText(text))).check(matches(isDisplayed()));
    }
}
