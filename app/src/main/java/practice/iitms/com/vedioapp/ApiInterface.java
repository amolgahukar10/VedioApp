package practice.iitms.com.vedioapp;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;

/**
 * @author Anup Khedekar on 4/4/2019.
 */
public interface ApiInterface {
    @GET("/video/{videoId}")
    Call<String> getPrivateVimeoVideo(@HeaderMap Map<String, String>
                                                       headers, @Path("videoId") String videoId);
}
