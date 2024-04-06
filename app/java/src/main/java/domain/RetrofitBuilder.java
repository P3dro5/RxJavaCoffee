package domain;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import common.CoffeeDataDTO;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class RetrofitBuilder {

    private static final String COFFEE_BASE_URL = "https://api.sampleapis.com/";

    private static RetrofitBuilder instance;
    private final CoffeeService coffeeService;

    private RetrofitBuilder() {
        final Gson gson =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(COFFEE_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        coffeeService = retrofit.create(CoffeeService.class);
    }

    public static RetrofitBuilder getInstance() {
        if (instance == null) {
            instance = new RetrofitBuilder();
        }
        return instance;
    }

    public Observable<List<CoffeeDataDTO>> getRepos() {
        return coffeeService.getListOfCoffees();
    }
}