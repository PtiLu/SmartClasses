package pro.ludo.smartclasses;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView myQuestion;
    private EditText myAnswer;
    private Button submitButton;
    private ProgressBar hourglass;
    private Spinner questionsList;

    private Handler myHandler;
    private final int interval = 1000; // 1 Second
    private int progress;
    private List<String> myQuestionsList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Getting all the UI components
        myQuestion = (TextView) findViewById(R.id.txtQuestion);
        myAnswer = (EditText) findViewById(R.id.txtAnswer);
        submitButton = (Button) findViewById(R.id.btnSubmit);
        hourglass = (ProgressBar) findViewById(R.id.progressBar);
        questionsList = (Spinner) findViewById(R.id.listQuestion);

        //Setting the components
        submitButton.setOnClickListener(submitButtonListener);
        myQuestionsList.add("In what environment would you usually find it?");
        myQuestionsList.add("What's its average size?");
        myQuestionsList.add("What's its composition?");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, myQuestionsList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Set up the drop down list
        questionsList.setAdapter(dataAdapter);
        questionsList.setOnItemSelectedListener(questionListItemListener);
        questionsList.setPrompt(getString(R.string.strPrompt));

        //Setting the time progress
        myHandler = new Handler();
        myHandler.postDelayed(runnable, interval);
        progress = 0;
        hourglass.setProgress(progress);

    }

    AdapterView.OnItemSelectedListener questionListItemListener = new AdapterView.OnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            myQuestion.setText(parent.getItemAtPosition(position).toString());
            questionsList.setPrompt(getString(R.string.strPrompt));
            Log.d("TEST","Change Question");
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            //DO NOTHING
        }
    };

    private Runnable runnable = new Runnable(){
        public void run() {
            if(progress == 100) progress = 0;
            else progress++;
            hourglass.setProgress(progress);
            myHandler.postDelayed(this, interval);
        }
    };

    View.OnClickListener submitButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("TEST", "Submit baby!");

        }
    };
}
