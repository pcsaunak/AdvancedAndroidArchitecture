package example.saunak.com.advancedarchitecture.trending;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import example.saunak.com.advancedarchitecture.di.ScreenScope;

@ScreenScope
@Subcomponent
public interface TrendingReposComponent extends AndroidInjector<TrendingReposController>{
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<TrendingReposController>{

    }
}
