package worldvisionsoft.com.livedataviewholderdemo.repo.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import worldvisionsoft.com.livedataviewholderdemo.repo.remote.model.ApiResponse;

/**
 * Created by user on 12/17/2017.
 */

public interface WebService {

    @GET("movie/popular")
    Call<ApiResponse> loadMovies();
}
