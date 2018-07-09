package com.example.sherly.medicalagent.service;

import com.example.sherly.medicalagent.model.UpdateModel;
import com.example.sherly.medicalagent.model.agent.AgentModel;
import com.example.sherly.medicalagent.model.agent.DataAgentModel;
import com.example.sherly.medicalagent.model.histori.HistoriModel;
import com.example.sherly.medicalagent.model.login.LoginModel;
import com.example.sherly.medicalagent.model.login.PostLoginModel;
import com.example.sherly.medicalagent.model.order.OrderModel;
import com.example.sherly.medicalagent.model.penilaian.RatingModel;
import com.example.sherly.medicalagent.model.role.RoleModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class ApiService {
    public static String BASE_URL = "https://sherly.jagopesan.com";

    public static PostService service_post = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiService.PostService.class);

    public static GetService service_get = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiService.GetService.class);

    public static PatchService service_patch = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiService.PatchService.class);

    public static DeleteService service_delete = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiService.DeleteService.class);

    public interface GetService {
        @GET("/roles")
        Call<RoleModel> getRole();

        @GET("/ordersoffers/daftarorder")
        Call<OrderModel> getOrders(@Header("Authorization") String token);

        @GET("/agents/{agentId}")
        Call<DataAgentModel> getDetailAgent(@Header("Authorization") String token, @Path("agentId") String agentId);

        @GET("/ordersoffers/kategori/{agentId}")
        Call<HistoriModel> getHistoryByAgent(@Header("Authorization") String token, @Path("agentId") String agentId);

        @GET("/ratings/category/{agentId}")
        Call<RatingModel> getRatingByAgent(@Header("Authorization") String token, @Path("agentId") String agentId);


//        @GET("/roles/{roleId}")
//        Call<DataRole> getDetailRole(@Header("Authorization") String token, @Path("roleId") String roleId);
//
//        @GET("/users/{userId}")
//        Call<UserModel> getDetailUser(@Header("Authorization") String token, @Path("userId") String userId);
//
//        @GET("/patients/category/{userId}")
//        Call<GetPatientModel> getPatientByUser(@Header("Authorization") String token, @Path("userId") String userId);
//
//        @GET("/addresses/category/{userId}")
//        Call<GetAddressModel> getAddressByUser(@Header("Authorization") String token, @Path("userId") String userId);
//
//        @GET("/ordersoffers/category/{userId}")
//        Call<HistoryModel> getHistoryByUser(@Header("Authorization") String token, @Path("userId") String userId);
//
//        @GET("/ordersoffers/{ooId}")
//        Call<DetailHistoryModel> getHistoryByOO (@Header("Authorization") String token, @Path("ooId") String ooId);
//
//        @GET("/shifts/category/{ooId}")
//        Call<ShiftModel> getShiftByOO (@Header("Authorization") String token, @Path("ooId") String ooId);
//
//        @GET("/articles")
//        Call<ArticleModel> getArticle(@Header("Authorization") String token);

    }

    public interface PostService {
        //        @FormUrlEncoded
//        @POST("/users/login")
//        Call<LoginModel>postLogin(@Field("email") String email, @Field("password") String password);
        //@Headers("Content-Type: application/json")
        @POST("/agents/login")
        Call<LoginModel> postLogin(@Body PostLoginModel body);

//        @POST("/users/signup")
//        Call<RegisterModel> postSignup(@Body PostRegisterModel body);
//
//        @POST("/patients")
//        Call<PatientModel> postPatient(@Header("Authorization") String token, @Body PostPatientModel body);
//
//        @POST("/ordersoffers/orders")
//        Call<OrderModel> postOrder(@Header("Authorization") String token, @Body PostOrderModel body);
//
//        @POST("/ordersoffers/offers")
//        Call<OfferModel> postOffer(@Header("Authorization") String token, @Body PostOfferModel body);
    }

    public interface PatchService {
        @PATCH("/agents/{agentId}")
        Call<AgentModel> patchAgent(@Header("Authorization") String token, @Path("agentId") String agentId, @Body UpdateModel body);

        @PATCH("/agents/update/{agentId}")
        Call<AgentModel> patchAddressAgent(@Header("Authorization") String token, @Path("agentId") String agentId, @Body ArrayList<UpdateModel> body);

        @PATCH("/agents/updatelatlng/{agentId}")
        Call<AgentModel> patchLLAgent(@Header("Authorization") String token, @Path("agentId") String agentId, @Body ArrayList<UpdateModel> body);
    }

    public interface DeleteService {

    }
}
