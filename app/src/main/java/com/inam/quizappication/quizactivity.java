package com.inam.quizappication;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class quizactivity extends AppCompatActivity {
    public int counter;
    TextView tv,t1;
    Button submitbutton, quitbutton;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;
    CountDownTimer countDownTimer;

    String questions[] = {
            "Bluetooth Technology supports",
            "In Bluetooth which of the following device decides the hopping sequence?",
            "Which of the following is/are the advantages of a wireless LAN?",
            "In which of the following Codes with specific characteristics can be applied to the transmission?",
            "Which of the following allow the use of entire bandwidth simultaneously?",
            "The base station covers a specific area that is called a ——",
            "Cellular System or having small cells needs ——–",
            "Which of the following provides packet mode data transfer service over the cellular network system?",
            "Which of the following services/ services are defined by the GSM?",
            "HOW many Gods in islam"
    };
    String answers[] = {"All of the above",
                        "Master",
                        "All of the above",
                        "CDMA",
                        "CDMA",
                        "Cell",
                         "All of the above",
                         "GPRS",
                          "All of the above",
                          "Only one"};
    String opt[] = {
            "Piconet","Ad hoc piconet","Scatter net","All of the above",
            "Master","Parked","standby","slaveؑ",
            "Flexibility","Ease of use","Robustness","All of the above",
            "GSM","GPRS","CDMA","None of above",
            "TDMA","FDMA","CDMA","None of above",
            "Cell","Tessellate","Mobile station","None of above",
            "Handover","Infrastructure","Frequency planning","All of the above",
            "GSM","GPRS","TCP","none",
            "Bearer","Tele","Supplementary","All of the above",
            "Only one","Two","Three","NONE"
    };
    int flag=0;
    public static int marks=0,correct=0,wrong=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        final TextView score = (TextView)findViewById(R.id.textView4);
        TextView textView=(TextView)findViewById(R.id.DispName);
        Intent intent = getIntent();
        String name= intent.getStringExtra("myname");

        if (name.trim().equals(""))
            textView.setText("Hello User");
        else
            textView.setText("Hello " + name);

        submitbutton=(Button)findViewById(R.id.button3);
        quitbutton=(Button)findViewById(R.id.buttonquit);
        tv=(TextView) findViewById(R.id.tvque);
        t1=(TextView) findViewById(R.id.timer);

        radio_g=(RadioGroup)findViewById(R.id.answersgrp);
        rb1=(RadioButton)findViewById(R.id.radioButton);
        rb2=(RadioButton)findViewById(R.id.radioButton2);
        rb3=(RadioButton)findViewById(R.id.radioButton3);
        rb4=(RadioButton)findViewById(R.id.radioButton4);
        tv.setText(questions[flag]);
        rb1.setText(opt[0]);
        rb2.setText(opt[1]);
        rb3.setText(opt[2]);
        rb4.setText(opt[3]);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radio_g.getCheckedRadioButtonId()==-1)
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "choice not selected", Toast.LENGTH_LONG);
//                    toast.setGravity(Gravity.TOP|Gravity.BOTTOM, 0, 0);
                    toast.show();
                    return;
                }
                RadioButton ans = (RadioButton) findViewById(radio_g.getCheckedRadioButtonId());
                String ansText = ans.getText().toString();
                if(ansText.equals(answers[flag])) {
                    correct++;
                    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                }
                else {
                    wrong++;
                    Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                }

                flag++;

                if (score != null)
                    score.setText(""+correct);

                if(flag<questions.length)
                {
                    tv.setText(questions[flag]);
                    rb1.setText(opt[flag*4]);
                    rb2.setText(opt[flag*4 +1]);
                    rb3.setText(opt[flag*4 +2]);
                    rb4.setText(opt[flag*4 +3]);
                }
                else
                {
                    marks=correct;
                    Intent in = new Intent(getApplicationContext(),resultrecord_activity.class);
                    startActivity(in);
                }
                radio_g.clearCheck();
            }
        });

        quitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),resultrecord_activity.class);
                startActivity(intent);
            }
        });
        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                t1.setText(String.valueOf(counter));
                counter++;
            }
            @Override
            public void onFinish() {
                if (score != null)
                    score.setText(""+correct);

                if(flag<questions.length)
                {
                    tv.setText(questions[flag]);
                    rb1.setText(opt[flag*4]);
                    rb2.setText(opt[flag*4 +1]);
                    rb3.setText(opt[flag*4 +2]);
                    rb4.setText(opt[flag*4 +3]);
                }
                else
                {
                    marks=correct;
                    Intent in = new Intent(getApplicationContext(),resultrecord_activity.class);
                    startActivity(in);
                }
                Intent intent=new Intent(getApplicationContext(),resultrecord_activity.class);
                startActivity(intent);
            }
        }.start();
    }

}