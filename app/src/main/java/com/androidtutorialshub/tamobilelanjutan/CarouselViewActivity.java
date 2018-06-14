package com.androidtutorialshub.tamobilelanjutan;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.Html;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidtutorialshub.tamobilelanjutan.konfig.UserConfig;
import com.androidtutorialshub.tamobilelanjutan.model.User;
import com.androidtutorialshub.tamobilelanjutan.remote.ApiClient;
import com.androidtutorialshub.tamobilelanjutan.remote.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarouselViewActivity extends AppCompatActivity implements View.OnClickListener {

    public static UserConfig userConfig;

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnSkip, btnNext;
    private TextInputLayout textInputLayoutUsername, textInputLayoutNoHp;
    private TextInputEditText textInputEditTextUsername, textInputEditTextNoHp;
    private AppCompatButton appCompatButtonLogin;
    private EditText username, nohp;
    private Button login;

    public static UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // mengecek lauch activity - sebelum memanggil setContentView()

        userConfig = new UserConfig(this);
        userService = ApiClient.getApiClient().create(UserService.class);

        // membuat transparan notifikasi
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.activity_carousel_view);
        getSupportActionBar().hide();
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnNext = (Button) findViewById(R.id.btn_next);


        // layout xml slide 1 sampai 4
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.slide1,
                R.layout.slide2,
                R.layout.slide3,
                R.layout.slide4};

        // tombol dots (lingkaran kecil perpindahan slide)
        addBottomDots(0);

        // membuat transparan notifikasi
        changeStatusBarColor();

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // mengecek page terakhir (slide 4)
                // jika activity home sudah tampil
                int current = getItem(+1);
                if (current < layouts.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    launchHomeScreen();
                }
            }
        });
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        //startActivity(new Intent(CarouselViewActivity.this, MainActivity.class));
        //finish();
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.login_username);
        //dialog.setTitle("Pilih Aksi");
        dialog.show();

        textInputLayoutUsername = (TextInputLayout) dialog.findViewById(R.id.textInputLayoutUsername);
        textInputEditTextUsername = (TextInputEditText) dialog.findViewById(R.id.textInputEditTextUsername);
        textInputLayoutNoHp = (TextInputLayout) dialog.findViewById(R.id.textInputLayoutNoHp);
        textInputEditTextNoHp = (TextInputEditText) dialog.findViewById(R.id.textInputEditTextNoHp);
        appCompatButtonLogin = (AppCompatButton) dialog.findViewById(R.id.appCompatButtonLogin);
        appCompatButtonLogin.setOnClickListener(this);
        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (Character.isWhitespace(source.charAt(i))) {
                        return "";
                    } else if (textInputEditTextUsername.length() == 6) {
                        return "";
                    }
                }
                return null;
            }

        };
        textInputEditTextUsername.setFilters(new InputFilter[]{filter});

        //final Integer nama=username.getText().toString().length();
        /*
        username.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                //If the keyevent is a key-down event on the "enter" button
                if(username.getText().toString().trim().length()==6) {
                    if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        dialog.dismiss();
                        startActivity(new Intent(CarouselViewActivity.this, HomeActivity.class));
                        finish();
                        return true;
                    }
                }
                return false;
            }

        });
        */
        /*
        final KeyEvent event=new KeyEvent(1,1);
        if(event.getKeyCode()==KeyEvent.KEYCODE_ENTER){
            dialog.dismiss();
            startActivity(new Intent(CarouselViewActivity.this, HomeActivity.class));
            finish();
        }

        if(username.getText().toString().length()==6){
            startActivity(new Intent(CarouselViewActivity.this, HomeActivity.class));
            finish();
        }
        */
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.appCompatButtonLogin) {
            if (textInputEditTextUsername.length() == 6 && textInputEditTextNoHp.length() == 12) {

                // TODO 5 Cek User Login ke API Database

                String username = textInputEditTextUsername.getText().toString();
                String nohp = textInputEditTextNoHp.getText().toString();

                Call<User> call = userService.userLogin(username, nohp);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        String statusMessage = response.body().getMessage();

                        if (statusMessage.equals("ok")) {

                            userConfig.writeNorekening(response.body().getNorekening());
                            userConfig.writeNama(response.body().getNama());

                            startActivity(new Intent(CarouselViewActivity.this, HomeActivity.class));
                            finish();

                        } else if (statusMessage.equals("failed")) {

                            Toast.makeText(CarouselViewActivity.this, "Username dan Nomor Hp tidak sesuai", Toast.LENGTH_SHORT).show();

                        }


                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });

                textInputEditTextUsername.setText("");
                textInputEditTextNoHp.setText("");


            }
        }
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // mengubah button lanjut 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                btnNext.setText(getString(R.string.start));
                btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

}
