package example.saunak.com.advancedarchitecture.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import example.saunak.com.advancedarchitecture.data.RepoService;
import retrofit2.Retrofit;

@Module
public abstract class RepoServiceModule {
    @Provides
    @Singleton
    static RepoService provideRepoService(Retrofit retrofit){
        return retrofit.create(RepoService.class);
    }
}
