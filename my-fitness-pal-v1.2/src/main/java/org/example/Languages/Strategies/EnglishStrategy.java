package org.example.Languages.Strategies;

import org.example.Languages.LanguageStrategy;

public class EnglishStrategy implements LanguageStrategy
{
    public String getSelectCommand() {
        return "\nPlease select a command:";
    }

    public String getRegisterUser() {
        return "1. Register User";
    }

    public String getLoginUser() {
        return "2. Login User";
    }

    public String getAddWater() {
        return "3. Add Water";
    }

    public String getCheckWaterIntake() {
        return "4. Check Water Intake";
    }

    public String getLogoutUser() {
        return "5. Logout";
    }

    public String getExit() {
        return ". Exit";
    }

    public String getEnterYourChoice() {
        return "Enter your choice: ";
    }

    public String getInvalidInput() {
        return "Invalid input. Please enter a number between 1 and 6.";
    }

    public String getExitApplication() {
        return "Exiting application...";
    }

    public String getExiting()
    {
        return "Exiting...";
    }

    public String getInvalidCommand()
    {
        return "Invalid command.";
    }

    public String getEnterUsername()
    {
        return "Enter username: ";
    }

    public String getEnterPassword()
    {
        return "Enter password: ";
    }

    public String getInvalidEntryData()
    {
        return "Invalid entry data.";
    }

    public String getPleaseLoginToContinue()
    {
        return "Please login to continue.";
    }

    public String getEnterWaterAmount()
    {
        return "Enter water amount (ml): ";
    }

    public String getEnterDate()
    {
        return "Enter date (YYYY-MM-DD): ";
    }

    public String getLogoutSuccessfully()
    {
        return "Logged out successfully.";
    }
}
