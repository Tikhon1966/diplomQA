package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.core.IsNot.not;
import static ru.iteco.fmhandroid.ui.screenElements.GeneralElements.GeneralCancelButton;
import static ru.iteco.fmhandroid.ui.screenElements.GeneralElements.GeneralSaveButton;
import static ru.iteco.fmhandroid.ui.screenElements.GeneralElements.butterflyNewsListImage;
import static ru.iteco.fmhandroid.ui.screenElements.GeneralElements.emptyToastMatcher;
import static ru.iteco.fmhandroid.ui.screenElements.GeneralElements.errorToastMatcher;
import static ru.iteco.fmhandroid.ui.screenElements.GeneralElements.nothingText;
import static ru.iteco.fmhandroid.ui.screenElements.GeneralElements.oKButton;
import static ru.iteco.fmhandroid.ui.screenElements.GeneralElements.refresh;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.screenElements.GeneralElements;

public class GeneralSteps {

    public void clickSaveButton() {
        Allure.step("Нажать кнопку 'Сохранить'");
        GeneralSaveButton.perform(click());
    }

    public void clickCancelButton() {
        Allure.step("Нажать кнопку 'Отменить'");
        GeneralCancelButton.perform(click());
    }

    public void clickOkButton() {
        Allure.step("Нажать кнопку 'ОК'");
        oKButton.perform(click());
    }

    public void verifyToastMessageVisibility(int id, boolean visible) {
        if (visible) {
            emptyToastMatcher(id).check(matches(isDisplayed()));
        } else {
            emptyToastMatcher(id).check(matches(not(isDisplayed())));
        }
    }

    public void verifyInvalidAuthDataToast() {
        Allure.step("Проверка предупреждения о неверных данных авторизации");
        verifyToastMessageVisibility(GeneralElements.getSomethingError(), true);
    }

    public void verifyEmptyAuthDataToast() {
        Allure.step("Проверка предупреждения о пустых данных авторизации");
        verifyToastMessageVisibility(GeneralElements.getEmptyInfoMatcher(), true);
    }

    public void verifyErrorToast(int id, boolean visible) {
        if (visible) {
            errorToastMatcher(id).check(matches(isDisplayed()));
        } else {
            errorToastMatcher(id).check(matches(not(isDisplayed())));
        }
    }

    public void verifyEmptyFieldError() {
        Allure.step("Проверка отображения ошибки о пустых полях");
        verifyErrorToast(GeneralElements.getEmptyFields(), true);
    }
    public void verifyErrorSavingNewsToast() {
        Allure.step("Проверка отображения ошибки о невозможности сохранения");
        verifyErrorToast(GeneralElements.getErrorSaving(), true);
    }

    public void verifyNewsButterflyImage() {
        Allure.step("Проверка отображения картинки с бабочкой на экране новостей");
        butterflyNewsListImage.check(matches(isDisplayed()));
    }

    public void verifyNothingToShowMessage() {
        Allure.step("Проверка отображения сообщения 'Nothing to show'");
        nothingText.check(matches(isDisplayed()));
        refresh.check(matches(isDisplayed()));
    }
}
