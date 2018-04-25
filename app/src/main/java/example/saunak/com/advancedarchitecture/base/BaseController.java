package example.saunak.com.advancedarchitecture.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import example.saunak.com.advancedarchitecture.di.Injector;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseController extends Controller {

    private boolean injected = false;
    private Unbinder unbinder;
    private CompositeDisposable disposables;

    @Override
    protected void onContextAvailable(@NonNull Context context) {

        //Controller instances are retained through config changes, so we should not waste time injecting
        // an object when it is already inserted.

        if(!injected){
            Injector.inject(this);
            injected = true;
        }
        super.onContextAvailable(context);
    }

    @NonNull
    @Override
    protected final View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflater.inflate(layoutRes(),container,false);
        unbinder = ButterKnife.bind(this,view);
        onViewBound(view);
        disposables.addAll(subscriptions());
        return view;
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        super.onDestroyView(view);
        disposables.clear(); // if we use disposable.dispose we cannot use the CompositeDisposable object again.
        if(unbinder != null){
            unbinder.unbind();
            unbinder = null;
        }
    }

    // This will be called when all views have been bounded
    protected void onViewBound(View view){

    }

    protected Disposable[] subscriptions(){
        return new Disposable[0];
    }

    @LayoutRes
    protected abstract int layoutRes();
}
