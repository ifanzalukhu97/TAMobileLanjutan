package com.androidtutorialshub.tamobilelanjutan;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;


public class GantiPINActivity extends AppCompatActivity implements View.OnClickListener{
    private TextInputEditText edittextnopinlama,edittextnopinbaru,edittextnopinbarulagi;
    private TextInputLayout layoutnopinlama,layoutnopinbaru,layoutnopinbarulagi;
    private AppCompatButton submit;
    public String norekAnda1,passAnda1;
    private NestedScrollView nestedScrollView;
    private InputValidation inputValidation;
    ProgressDialog progress;
    private ImageView back;
    public GantiPINActivity() {
        // Required empty public constructor
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_pin);
        //menyembunyikan action bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.secondlayout_actionbar);
        initViews();
        initListeners();
        initObjects();

    }
    /**
     * This method is to initialize views
     */
    private void initViews() {
        nestedScrollView = (NestedScrollView)findViewById(R.id.nestedScrollView);
        edittextnopinlama=(TextInputEditText)findViewById(R.id.textInputEditTextPINlama);
        layoutnopinlama=(TextInputLayout)findViewById(R.id.textInputLayoutPINlama);
        edittextnopinbaru=(TextInputEditText)findViewById(R.id.textInputEditTextPINbaru);
        layoutnopinbaru=(TextInputLayout)findViewById(R.id.textInputLayoutPINbaru);
        edittextnopinbarulagi=(TextInputEditText)findViewById(R.id.textInputEditTextPINbarulagi);
        layoutnopinbarulagi=(TextInputLayout)findViewById(R.id.textInputLayoutPINbarulagi);
        submit=(AppCompatButton) findViewById(R.id.appCompatButtonSubmit);
        back=(ImageView)findViewById(R.id.actionbar_back);
        back.setOnClickListener(this);
    }
    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        submit.setOnClickListener(this);
    }
    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(this);
        progress = new ProgressDialog(this);
    }
    //mengambil data norekening dan PIN pada Session
    /**
     * This implemented method is to listen the click on view
     *
     */
    public void onClick(View v){
        if(v.getId()==R.id.actionbar_back){
            finish();
        }
        else if(v.getId()==R.id.appCompatButtonSubmit){
            String edittextnopinlama1=edittextnopinlama.getText().toString().trim();
            String edittextnopinbaru1=edittextnopinbaru.getText().toString().trim();
            String edittextnopinbarulagi1=edittextnopinbarulagi.getText().toString().trim();
            if (!inputValidation.isInputEditTextFilled(edittextnopinlama, layoutnopinlama, getString(R.string.error_message_oldpasswordkosong))) {
                return;
            }
            if (!inputValidation.isInputEditTextFilled(edittextnopinbaru, layoutnopinbaru, getString(R.string.error_message_newpasswordkosong))) {
                return;
            }
            if (!inputValidation.isInputEditTextFilled(edittextnopinbarulagi, layoutnopinbarulagi, getString(R.string.error_message_newpasswordkosong))) {
                return;
            }
            if (!inputValidation.isInputEditTextPIN(edittextnopinlama, layoutnopinlama, getString(R.string.error_message_digitpin))) {
                return;
            }
            if (!inputValidation.isInputEditTextPIN(edittextnopinbaru, layoutnopinbaru, getString(R.string.error_message_digitpin))) {
                return;
            }
            if (!inputValidation.isInputEditTextSamePIN(edittextnopinbaru, edittextnopinbarulagi, layoutnopinbarulagi, getString(R.string.error_message_newpin_confirmpin))) {
                return;
            }
            if (!inputValidation.isInputEditTextDifferentPIN(edittextnopinlama,edittextnopinbaru, layoutnopinbaru,getString(R.string.error_message_newpin_oldpin))) {
                return;
            }

            Snackbar.make(nestedScrollView, getString(R.string.success_message_pin), Snackbar.LENGTH_LONG).show();
            progress.setMessage("Loading...");
            progress.show();
            Thread _thread = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(3000);
                        progress.dismiss();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            masuk();
                        }
                    });
                }
            };
            _thread.start();
        }
    }
    private void masuk() {
        emptyInputEditText();
    }
    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        edittextnopinlama.setText(null);
        edittextnopinbaru.setText(null);
        edittextnopinbarulagi.setText(null);
        layoutnopinlama.setError(null);
        layoutnopinbaru.setError(null);
        layoutnopinbarulagi.setError(null);

    }
}
