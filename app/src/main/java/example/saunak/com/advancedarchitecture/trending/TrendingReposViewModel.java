package example.saunak.com.advancedarchitecture.trending;

import com.jakewharton.rxrelay2.BehaviorRelay;

import java.util.List;

import javax.inject.Inject;

import example.saunak.com.advancedarchitecture.R;
import example.saunak.com.advancedarchitecture.di.ScreenScope;
import example.saunak.com.advancedarchitecture.model.Repo;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

@ScreenScope // Need to retain this across the configuration changes. Hence we are using this screenscope.
class TrendingReposViewModel {
    private final BehaviorRelay<List<Repo>> reposRelay = BehaviorRelay.create();
    private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();//
    private final BehaviorRelay<Boolean> loadingRelay = BehaviorRelay.create(); // Tells our view whether its loading or not.
    @Inject
    TrendingReposViewModel(){

    }


    // Our view will subscribe to the below observables to check if there is any change in the data.

    Observable<Boolean> loading(){
        return loadingRelay;
    }

    Observable<List<Repo>> repos(){
        return reposRelay;
    }

    Observable<Integer> error(){
        return errorRelay;
    }

    Consumer<Boolean> loadingUpdated(){
        return loadingRelay;
    }

    Consumer<List<Repo>> reposUpdated(){
        /* As we get the list from the Observer we send it to the consumer
        * Suppose if we get an error the first time in fetching data, error will be sent.
        * However if we try again and we get a new data, in that case we need to clear that error*/
        errorRelay.accept(-1);
        return reposRelay;
    }

    Consumer<Throwable> errorUpdated(){
        return throwable -> {
            /*We will decide here what to send the error relay*/
            errorRelay.accept(R.string.api_error_repos);
            Timber.e(throwable,"Error loading repos !!");
        };
    }
}
