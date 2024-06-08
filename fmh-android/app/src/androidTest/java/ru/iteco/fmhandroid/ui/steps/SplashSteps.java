package ru.iteco.fmhandroid.ui.steps;

import static ru.iteco.fmhandroid.ui.data.DataHelper.elementWaiting;
import static ru.iteco.fmhandroid.ui.screenElements.SplashElements.getSplashscreenImage;

import io.qameta.allure.kotlin.Allure;

public class SplashSteps {

    public void appDownload() {
        Allure.step("Загрузка приложения");
        elementWaiting(getSplashscreenImage(), 5000);
    }
}
