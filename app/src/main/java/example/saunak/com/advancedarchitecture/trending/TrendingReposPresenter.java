package example.saunak.com.advancedarchitecture.trending;


import javax.inject.Inject;

import example.saunak.com.advancedarchitecture.data.RepoRequester;
import example.saunak.com.advancedarchitecture.di.ScreenScope;
import example.saunak.com.advancedarchitecture.model.Repo;

@ScreenScope
class TrendingReposPresenter implements RepoAdapter.RepoClickedListener {
    private TrendingReposViewModel reposViewModel;
    private RepoRequester repoRequester;

    @Inject
    TrendingReposPresenter(TrendingReposViewModel reposViewModel, RepoRequester repoRequester){
        this.repoRequester = repoRequester;
        this.reposViewModel = reposViewModel;
        loadRepos();
    }

    private void loadRepos() {
        repoRequester.getTrendingRepos()
                .doOnSubscribe(__ -> reposViewModel.loadingUpdated().accept(true))
                .doOnEvent((d,t) -> reposViewModel.loadingUpdated().accept(false))
                .subscribe(reposViewModel.reposUpdated(),reposViewModel.errorUpdated());
    }

    @Override
    public void onRepoClicked(Repo repo) {

    }
}
