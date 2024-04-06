package domain;

import java.util.List;

import common.CoffeeDataDTO;
import retrofit2.http.GET;
import rx.Observable;

public interface CoffeeService {
    @GET("coffee/hot")
    Observable<List<CoffeeDataDTO>> getListOfCoffees();
}
