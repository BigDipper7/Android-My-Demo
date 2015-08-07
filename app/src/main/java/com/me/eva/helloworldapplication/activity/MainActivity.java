package com.me.eva.helloworldapplication.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.internal.util.Predicate;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.me.eva.helloworldapplication.R;


public class MainActivity extends ActionBarActivity {

    private Button btnClick, btnViewPager;
    private TextView tv;
    private EditText edTx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        tv = (TextView) findViewById(R.id.textView);
        Techniques[] techs = Techniques.values();
        for(Techniques tec : techs) {
            log(tec.toString());
        }

        edTx = (EditText) findViewById(R.id.editText);

        btnClick = (Button) findViewById(R.id.btn1);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Click....");

                String anomationType = edTx.getText().toString();
                anomationType = anomationType == null ? "" : anomationType;

                if (anomationType.equals(""))
                    YoYo.with(Techniques.Bounce).duration(2000).playOn(tv);
                else {
                    Techniques techniques = Techniques.valueOf(anomationType);
                    YoYo.with(techniques).duration(2000).playOn(tv);
                }


            }
        });


        btnViewPager = (Button) findViewById(R.id.btnViewPager);
        btnViewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Predicate<String> predicate = new Predicate<String>() {
                    @Override
                    public boolean apply(String s) {
                        return false;
                    }
                };

//                Function<String, String> function = null;


                Intent iJMPMainToAnim = new Intent(MainActivity.this,AnimationActivity.class);
                MainActivity.this.startActivity(iJMPMainToAnim);


            }

        });
    }

    private void showToast(String content) {
        content = content==null ? "":content;
        Toast.makeText(MainActivity.this,content,Toast.LENGTH_SHORT).show();
    }

    private void log(String logContent) {
        Log.v("MainActivity", logContent);
    }

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
}
