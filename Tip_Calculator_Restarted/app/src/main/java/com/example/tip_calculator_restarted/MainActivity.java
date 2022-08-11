package com.example.tip_calculator_restarted;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.tip_calculator_restarted.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    TextView tipText;
    TextView TotalText;
    EditText EditTextBill;
    Button twelvePercent;
    Button fifteenPercent;
    Button twentyPercent;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    
    public View.OnClickListener tipListenerFactory(int tipClicked) {
        
        View.OnClickListener myListener = (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                if ((EditTextBill.getText().toString().matches(""))) {
                    System.out.println("edit text was wrong");
                    return;
                }

//// calculate based on input variable;
                double billAmount = new Double(EditTextBill.getText().toString());
                double tipAmount = (billAmount * tipClicked) / 100; // as percent
                double totalAmount = billAmount + tipAmount;
                updateText(tipAmount, totalAmount);
            }
        }
        );
        return myListener;
    }// end tipListenerFactory
    
    public void updateText(Double tip, Double total) {
        final DecimalFormat df = new DecimalFormat("0.00");
        
        tipText.setText("TIP: $" + df.format(tip));
        TotalText.setText("TOTAL: $" + df.format(total));
    }
    
    public void runInitialization() {
        
        tipText = (TextView) findViewById(R.id.tipText);
        tipText.setText("-");
        TotalText = (TextView) findViewById(R.id.TotalText);
        TotalText.setText("-");
        EditTextBill = (EditText) findViewById(R.id.EditTextBill);
        //EditTextBill.setText("Enter Bill Total");
        EditTextBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTextBill.setText("");
            }
        }); //Clears text box
        twelvePercent = (Button) findViewById(R.id.twelvePercent);
        fifteenPercent = (Button) findViewById(R.id.fifteenPercent);
        twentyPercent = (Button) findViewById(R.id.twentyPercent);
        twelvePercent.setOnClickListener(tipListenerFactory(12));
        fifteenPercent.setOnClickListener(tipListenerFactory(15));
        twentyPercent.setOnClickListener(tipListenerFactory(20));
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        setSupportActionBar(binding.toolbar);
        
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        
        runInitialization(); // must run after most of above is done
    } // END onCreate
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}