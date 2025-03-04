package org.example.ID;

public class ID
{
    private static ID instance;
    private int currentId = 0;

    public ID() {}

    public static ID getInstance()
    {
        if (instance == null) {
            synchronized (ID.class) {
                if (instance == null) {
                    instance = new ID();
                }
            }
        }
        return instance;
    }

    public int generateId() {
        return ++currentId;
    }

    public int getCurrentId() {
        return currentId;
    }
}
