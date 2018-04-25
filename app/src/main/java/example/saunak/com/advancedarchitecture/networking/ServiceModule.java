package example.saunak.com.advancedarchitecture.networking;

import com.squareup.moshi.Moshi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import example.saunak.com.advancedarchitecture.model.AdapterFactory;
import example.saunak.com.advancedarchitecture.model.ZoneDateTimeAdapter;
import okhttp3.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

// Provide depenendencies is to include another module within a module.
// Network module is included only in the service module
@Module(includes = {NetworkModule.class})
public abstract class ServiceModule {

    // The moshi adapters that we created with the models are going to be used with the Moshi
    // object that we are creating down here.
    // AdapterFactory -- is going to add all the model adapters.

    @Provides
    @Singleton
    static Moshi provideMoshi(){
        return new Moshi.Builder()
                .add(AdapterFactory.create())
                .add(new ZoneDateTimeAdapter()).build();
    }

    /* Creating Retrofit dependency.
       The MoshiConvertorFactory -- gives us access to all the json adapters created within Models


     Different Call.Factories will give us the option to mock responses.
     Module needed in different build packages to provide the Call.Factory and the Base URL. */

    @Provides
    @Singleton
    static Retrofit provideRetrofit(Moshi moshi,
                                    Call.Factory callFactory,
                                    @Named("base_url") String baseUrl){
        return new Retrofit.Builder()
                .callFactory(callFactory)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl).build();

    }
}
