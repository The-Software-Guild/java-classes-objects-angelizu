package com.sg.dvdlibrary.ui;

public interface UserIO {

    String readString(String prompt);

    String checkDate();

    void print(String msg);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);

}
