package org.example.Languages;

public class LanguageContext
{
    LanguageStrategy languageStrategy;

    public LanguageContext(LanguageStrategy languageStrategy) {
        this.languageStrategy = languageStrategy;
    }

    public String getSelectCommand()
    {
        return languageStrategy.getSelectCommand();
    }

    public String getRegisterUser()
    {
        return languageStrategy.getRegisterUser();
    }

    public String getLoginUser()
    {
        return languageStrategy.getLoginUser();
    }

    public String getAddWater()
    {
        return languageStrategy.getAddWater();
    }

    public String getCheckWaterIntake()
    {
        return languageStrategy.getCheckWaterIntake();
    }

    public String getLogoutUser()
    {
        return languageStrategy.getLogoutUser();
    }

    public String getExit()
    {
        return languageStrategy.getExit();
    }

    public String getEnterYourChoice()
    {
        return languageStrategy.getEnterYourChoice();
    }

    public String getInvalidInput()
    {
        return languageStrategy.getInvalidInput();
    }

    public String getExitApplication()
    {
        return languageStrategy.getExitApplication();
    }

    public String getExiting()
    {
        return languageStrategy.getExiting();
    }

    public String getInvalidCommand()
    {
        return languageStrategy.getInvalidCommand();
    }

    public String getEnterUsername()
    {
        return languageStrategy.getEnterUsername();
    }

    public String getEnterPassword()
    {
        return languageStrategy.getEnterPassword();
    }

    public String getInvalidEntryData()
    {
        return languageStrategy.getInvalidEntryData();
    }

    public String getPleaseLoginToContinue()
    {
        return languageStrategy.getPleaseLoginToContinue();
    }

    public String getEnterWaterAmount()
    {
        return languageStrategy.getEnterWaterAmount();
    }

    public String getEnterDate()
    {
        return languageStrategy.getEnterDate();
    }

    public String getLogoutSuccessfully()
    {
        return languageStrategy.getLogoutSuccessfully();
    }
}
