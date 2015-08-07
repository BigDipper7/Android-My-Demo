package com.me.eva.helloworldapplication.activity;

import android.animation.LayoutTransition;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.me.eva.helloworldapplication.R;


public class TransitionTestActivity extends ActionBarActivity implements CompoundButton.OnCheckedChangeListener {

    private Button btnAddBtn;
    private CheckBox cbApp, cbChanApp, cbDisApp, cbChanDisApp;
    private LinearLayout ll;
    private GridLayout btnGroup;
    private int btnId = 0;
    private LayoutTransition lyTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_test);

        init();
    }

    private void init() {
        btnAddBtn = (Button) findViewById(R.id.button);
        cbApp = (CheckBox) findViewById(R.id.checkBox);
        cbChanApp = (CheckBox) findViewById(R.id.checkBox2);
        cbDisApp = (CheckBox) findViewById(R.id.checkBox3);
        cbChanDisApp = (CheckBox) findViewById(R.id.checkBox4);
        ll = (LinearLayout) findViewById(R.id.linearLayout);

        btnGroup = new GridLayout(TransitionTestActivity.this);
        btnGroup.setColumnCount(5);
        ll.addView(btnGroup);

        btnAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Button btnToAdd = new Button(TransitionTestActivity.this);

                btnToAdd.setText(String.format("Btn %d",btnId));
                btnToAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View vi) {

                        btnGroup.removeView(vi);
                    }
                });

                btnGroup.addView(btnToAdd,Math.min(1,btnId++));
            }
        });

        cbApp.setOnCheckedChangeListener(this);
        cbChanApp.setOnCheckedChangeListener(this);
        cbDisApp.setOnCheckedChangeListener(this);
        cbChanDisApp.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        lyTransition = new LayoutTransition();
        lyTransition.setAnimator(LayoutTransition.APPEARING,(cbApp.isChecked()?lyTransition.getAnimator(LayoutTransition.APPEARING):null));
        lyTransition.setAnimator(LayoutTransition.CHANGE_APPEARING,(cbChanApp.isChecked()?lyTransition.getAnimator(LayoutTransition.CHANGE_APPEARING):null));
        lyTransition.setAnimator(LayoutTransition.DISAPPEARING,(cbDisApp.isChecked()?lyTransition.getAnimator(LayoutTransition.DISAPPEARING):null));
        lyTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING,(cbChanDisApp.isChecked()?lyTransition.getAnimator(LayoutTransition.CHANGE_DISAPPEARING):null));
        btnGroup.setLayoutTransition(lyTransition);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_transition_test, menu);
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
