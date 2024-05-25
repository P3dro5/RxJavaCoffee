package ui;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mvi.java.R;

import java.util.List;

import common.CoffeeDataDTO;
import domain.RetrofitBuilder;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final CoffeeRepoAdapter adapter = new CoffeeRepoAdapter();
    private Subscription subscription;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coffee_activity);

        final Button btn = (Button)findViewById(R.id.coffee_button);
        final TextView introText = (TextView)findViewById(R.id.intro_text);

        final ListView listView = (ListView) findViewById(R.id.list_view_repos);
        listView.setAdapter(adapter);

        btn.setOnClickListener(view -> {
            getStarredRepos();
            btn.setVisibility(GONE);
            introText.setVisibility(VISIBLE);
        });

    }

    @Override protected void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }

    private void getStarredRepos() {
        subscription = RetrofitBuilder.getInstance()
                .getRepos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CoffeeDataDTO>>() {
                    @Override public void onCompleted() {
                        Log.d(TAG, "In onCompleted()");
                    }

                    @Override public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d(TAG, "In onError()");
                    }

                    @Override public void onNext(List<CoffeeDataDTO> coffeeDataDTO) {
                        Log.d(TAG, "In onNext()");
                        adapter.setRxJavaCoffees(coffeeDataDTO);
                    }
                });
    }
}