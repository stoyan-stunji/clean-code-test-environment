package org.example.Languages;

public interface LanguageStrategy
{
    String getSelectCommand();
    String getRegisterUser();
    String getLoginUser();
    String getAddWater();
    String getCheckWaterIntake();
    String getLogoutUser();
    String getExit();
    String getEnterYourChoice();
    String getInvalidInput();
    String getExitApplication();
    String getExiting();
    String getInvalidCommand();
    String getEnterUsername();
    String getEnterPassword();
    String getInvalidEntryData();
    String getPleaseLoginToContinue();
    String getEnterWaterAmount();
    String getEnterDate();
    String getLogoutSuccessfully();
}
