package com.example.WajahaAppTest.http;
import com.example.WajahaAppTest.feature.product_feature.Products;
import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RequestBuilder {
    private static final String Base_URL="https://wajaha-api.beetronix.net/";
    private MainWebInterface mainWebInterface;
    private static RequestBuilder INSTANCE;

    public  RequestBuilder() {

        Retrofit retrofitConnection = new Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mainWebInterface= retrofitConnection.create(MainWebInterface.class);

    }

    public static RequestBuilder getINSTANCE(){

        if (null==INSTANCE){
            INSTANCE=new RequestBuilder();
        }
    return INSTANCE;
    }

    public Call<List<Products>> getProducts(){
        return mainWebInterface.getProducts();
    }

}
