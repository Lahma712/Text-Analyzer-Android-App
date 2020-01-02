package com.example.textanalyzer3;

public class PercF
{
    public static int counting(String input, char charc)
    {
    int Times = 0;
    for(int Count1 = 0; Count1 < input.length(); Count1++)
    {
        if( charc == input.charAt(Count1))
            Times = Times +1;
    }
        return Times;
    }
}
