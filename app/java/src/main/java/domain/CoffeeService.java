package domain;

import java.util.List;

import common.CoffeeData;
import retrofit2.http.GET;
import rx.Observable;

public interface CoffeeService {
    @GET("coffee/hot")
    Observable<List<CoffeeData>> getListOfCoffees();
}
