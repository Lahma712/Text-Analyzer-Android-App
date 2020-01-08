package com.project.textanalyzer3;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.project.textanalyzer3.PercF.counting;

public class WordSearchRoutine
{

    public static void WordSearchRoutine(String wordsearchinput, TextView wordsearchocc, TextView wordsearchperc, String textboxinput, CheckBox checkbox, String Specials)
    {
     if(wordsearchinput.contentEquals(""))
        {
         wordsearchocc.setText("Occurences in text: ");
         wordsearchperc.setText("Percentage of text: ");
        }

     else
        {
            if(checkbox.isChecked())
            {

            int WordCounter = 0;

            Pattern word = Pattern.compile(wordsearchinput);
            Matcher text = word.matcher(textboxinput);


            while (text.find())
                {
                WordCounter++;

                }
            wordsearchocc.setText("Occurences in text: " + WordCounter);
            float percentage = ((float)(wordsearchinput.length() * WordCounter)/(float)textboxinput.length())*100;
            wordsearchperc.setText("Percentage of text: "+ Math.round(percentage *100)/100.0 +"%");
            }
        else{
            int Length=textboxinput.length();
            for(int x = 0; x<Specials.length(); x++)
                {
                Length = Length - counting(textboxinput,Specials.charAt(x));


                }
            int WordCounter = 0;

            Pattern word = Pattern.compile(wordsearchinput);
            Matcher text = word.matcher(textboxinput);


            while (text.find())
                {
                WordCounter++;

                }
            wordsearchocc.setText("Occurences in text: " + WordCounter);
            float percentage = ((float)(wordsearchinput.length() * WordCounter)/(float)Length)*100;
            wordsearchperc.setText("Percentage of text: "+ Math.round(percentage *100)/100.0 +"%");



            }

        }


     }
}
