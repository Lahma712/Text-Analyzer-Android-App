package com.example.textanalyzer3;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import static com.example.textanalyzer3.PercF.counting;
import static com.example.textanalyzer3.WordSearchRoutine.WordSearchRoutine;

public class MainActivity extends AppCompatActivity
{
    private EditText Textbox;
    private TextView Textlength;
    private EditText Wordsearch;
    private TextView WordsearchOcc;
    private TextView WordsearchPerc;
    private CheckBox Checkbox;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Textbox = findViewById(R.id.textbox);
        Textlength = findViewById(R.id.TextLength);
        Wordsearch = findViewById(R.id.wordsearch);
        WordsearchOcc = findViewById(R.id.wordsearchOcc);
        WordsearchOcc.setText("Occurences in text: ");
        WordsearchPerc = findViewById(R.id.wordsearchperc);
        WordsearchPerc.setText("Percentage of text: ");
        Checkbox = findViewById(R.id.checkbox);

        final String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String specials = " .,-;:_1234567890<>!§$%&/()=?²³{[]}~+";
        final ImageView[] BarArray = new ImageView[26];
        final TextView[] AlphArray = new TextView[26];

        for (int i = 0; i < 26; i++)
        {
            int img = getResources().getIdentifier("Bar" + (i+1) , "id", getPackageName());
            int charc = getResources().getIdentifier("Xaxis" +(i+1), "id",getPackageName());
            AlphArray[i] = findViewById(charc);
            BarArray[i] = findViewById(img);
            BarArray[i].setVisibility(View.INVISIBLE);
        }

        Textbox.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
                {
                    String WordSearchInput = (Wordsearch.getText().toString()).toLowerCase();
                    String TextboxInput = (Textbox.getText().toString()).toLowerCase();

                    WordSearchRoutine(WordSearchInput, WordsearchOcc, WordsearchPerc, TextboxInput, Checkbox, specials);

                for (int i = 0; i < 26; i++)
                    {
                        BarArray[i].setVisibility(View.INVISIBLE);
                    }


                Textlength.setText("Length: "+TextboxInput.length());
                if(TextboxInput.contentEquals(""))
                    {
                    for (int i = 0; i < 26; i++)
                        {
                            BarArray[i].setVisibility(View.INVISIBLE);
                            AlphArray[i].setTextColor(Color.parseColor("#39030000"));
                            WordsearchPerc.setText("Percentage of text: ");
                            WordsearchOcc.setText("Occurence in text: ");
                        }
                    }
                else
                    {
                    for(int Count2 = 0; Count2 < alph.length(); Count2++)
                        {
                        final float scale = getResources().getDisplayMetrics().density;
                        int Length=TextboxInput.length();

                        for(int x = 0; x<37; x++)
                            {
                                Length = Length - counting(TextboxInput,specials.charAt(x));
                            }
                        float perc= 100.0f * (float) counting(TextboxInput.toUpperCase(), alph.charAt(Count2)) / (float) Length;
                        int dpHeight = (int) ((perc * 3) * scale);
                        if (perc == 0.00)
                            {
                                BarArray[Count2].setVisibility(View.INVISIBLE);
                                AlphArray[Count2].setTextColor(Color.parseColor("#39030000"));
                            }
                        else
                            {

                                AlphArray[Count2].setTextColor(Color.parseColor("#030000"));
                                BarArray[Count2].getLayoutParams().height = dpHeight;
                                BarArray[Count2].requestLayout();
                                BarArray[Count2].setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }
                @Override
            public void afterTextChanged(Editable s){}
        });

        Wordsearch.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String WordSearchInput = (Wordsearch.getText().toString()).toLowerCase();
                String TextboxInput = (Textbox.getText().toString()).toLowerCase();

                WordSearchRoutine(WordSearchInput, WordsearchOcc, WordsearchPerc, TextboxInput, Checkbox, specials);
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        Checkbox.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String WordSearchInput = (Wordsearch.getText().toString()).toLowerCase();
                String TextboxInput = (Textbox.getText().toString()).toLowerCase();

                WordSearchRoutine(WordSearchInput, WordsearchOcc, WordsearchPerc, TextboxInput, Checkbox, specials);
            }
        });
    }
}



