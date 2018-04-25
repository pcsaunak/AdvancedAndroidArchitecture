package example.saunak.com.advancedarchitecture.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bluelinelabs.conductor.Controller;

import example.saunak.com.advancedarchitecture.R;
import example.saunak.com.advancedarchitecture.base.BaseActivity;
import example.saunak.com.advancedarchitecture.trending.TrendingReposController;

public class MainActivity extends BaseActivity {


    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected Controller initialScreen() {
        return new TrendingReposController();
    }
}
