package example.saunak.com.advancedarchitecture.data;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface RepoService {
    // Way to get list of trending repos.

    @GET("search/repositories?q=language:java&order=desc&sort=stars") // Search call to repository
    Single<TrendingReposResponse> getTrendingRepos();

    // RX java terminology used above,
    // Only one Item will be emitted or an error.
}
