package worldvisionsoft.com.livedataviewholderdemo.repo.remote;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import worldvisionsoft.com.livedataviewholderdemo.repo.local.entity.Posts;

/**
 * Created by user on 12/17/2017.
 */

public interface WebService {

    @POST("/posts")
    @FormUrlEncoded
    Call<Posts> testLogin(@Field("title") String title,
                          @Field("body") String body,
                          @Field("userId") int userId);
}
