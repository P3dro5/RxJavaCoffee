package domain;

import java.util.List;

import common.CoffeeData;
import rx.Observable;

public class RetrofitBuilder {

    private static final String GITHUB_BASE_URL = "https://api.sampleapis.com/";

    private static RetrofitBuilder instance;
    private CoffeeService coffeeService;

    private RetrofitBuilder() {

    }

    public static RetrofitBuilder getInstance() {
        if (instance == null) {
            instance = new RetrofitBuilder();
        }
        return instance;
    }

    public Observable<List<CoffeeData>> getRepos() {
        return coffeeService.getListOfCoffees();
    }
}