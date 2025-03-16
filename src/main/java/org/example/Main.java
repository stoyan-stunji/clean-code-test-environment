package org.example;

import org.example.Menu.MenuRunner;

public class Main {
    public static void main(String[] args)
    {
        try{
            MenuRunner runner = new MenuRunner();
            runner.run();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
