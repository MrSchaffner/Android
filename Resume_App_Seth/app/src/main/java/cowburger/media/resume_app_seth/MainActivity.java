package cowburger.media.resume_app_seth;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button workHistoryButton;
    Button callButton;
    Button emailButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectUIElements();

    }



    protected void connectUIElements(){
        workHistoryButton = (Button) findViewById(R.id.workHistoryButton);

        workHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToActivity;
                goToActivity = new Intent(getApplicationContext(), WorkHistoryActivity.class);

                startActivity(goToActivity);

            }
        });

        emailButton = (Button) findViewById(R.id.emailButton);

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent;
                emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"SethSchaffner@gmail.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "sent from app");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Intro Text");
                startActivity(emailIntent);

            }
        });

        callButton = (Button) findViewById(R.id.callButton);

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri myNum = Uri.parse("tel:8185689041");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, myNum); //switches to caller
                startActivity(callIntent);


            }
        });

    } //end connect

//    protected void changeActivity(Class myActivity){
//
//        Intent goToActivity;
//        goToActivity = new Intent(getApplicationContext(), myActivity.class);
//
//        startActivity(goToActivity);
//
//
//    }


}
