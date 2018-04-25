package example.saunak.com.advancedarchitecture.ui;

import dagger.Binds;
import dagger.Module;

// To provide the dependency of Screen Navigation to other activities we are creating a module
@Module
public abstract class NavigationModule {

    //When an object injects screen navigator they will be given default screen navigator.
    @Binds
    abstract ScreenNavigator provideScreenNavigator(DefaultScreenNavigator screenNavigator);

}
