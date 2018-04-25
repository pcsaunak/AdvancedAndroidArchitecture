package example.saunak.com.advancedarchitecture.base;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import example.saunak.com.advancedarchitecture.home.MainActivity;
import example.saunak.com.advancedarchitecture.home.MainActivityComponent;


@Module(subcomponents = {MainActivityComponent.class})

public abstract class ActivityBindingModule {
    /*provide a method that is going to provide an injector for the Main activity*/

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> provideMainActivityInjector(MainActivityComponent.Builder builder);
}
