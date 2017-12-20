package worldvisionsoft.com.livedataviewholderdemo.repo.remote;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import worldvisionsoft.com.livedataviewholderdemo.repo.remote.model.ApiResponse;

/**
 * Created by user on 12/17/2017.
 */

public interface WebService {

    @POST("/member/login")
    @FormUrlEncoded
    Call<ApiResponse> testLogin(@Field("AccessNo") String AccessNo,
                                @Field("PhoneCountryCode") String PhoneCountryCode,
                                @Field("Phone") String PhoneNo,
                                @Field("UDID") String UDID,
                                @Field("Registrar") String Registrar,
                                @Field("DeviceToken") String DeviceToken,
                                @Field("DeviceModel") String DeviceModel,
                                @Field("DevicePlatform") String DevicePlatform);
}
