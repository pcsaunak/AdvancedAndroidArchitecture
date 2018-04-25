package example.saunak.com.advancedarchitecture.home;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import example.saunak.com.advancedarchitecture.di.ActivityScope;
import example.saunak.com.advancedarchitecture.ui.NavigationModule;
//Dependencies can be derived from the below mentioned modules.
// Activity component is the dependency for the activies
@Subcomponent(modules = {
        MainScreenBindingModule.class,
        NavigationModule.class,
})
@ActivityScope
public interface MainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{
        @Override
        public void seedInstance(MainActivity instance) {

        }
    }
}
