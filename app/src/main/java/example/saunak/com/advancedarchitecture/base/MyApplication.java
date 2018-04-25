package example.saunak.com.advancedarchitecture.base;

import android.app.Application;

import javax.inject.Inject;

import example.saunak.com.advancedarchitecture.BuildConfig;
import example.saunak.com.advancedarchitecture.di.ActivityInjector;
import timber.log.Timber;

public class MyApplication extends Application {

    @Inject
    ActivityInjector activityInjector;

    ApplicationComponent component;
    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder().
                applicationModule(new ApplicationModule(this)).build();

        //created later
        component.inject(this);

        //This will create a tree and it will print logs only during debug build.
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
    }

    public ActivityInjector getActivityInjector() {
        return activityInjector;
    }
}
