package example.saunak.com.advancedarchitecture.home;

import com.bluelinelabs.conductor.Controller;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import example.saunak.com.advancedarchitecture.di.ControllerKey;
import example.saunak.com.advancedarchitecture.trending.TrendingReposComponent;
import example.saunak.com.advancedarchitecture.trending.TrendingReposController;

@Module(subcomponents = {
        TrendingReposComponent.class
})
public abstract class MainScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(TrendingReposController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindTrendingReposController(TrendingReposComponent.Builder builder);
}
