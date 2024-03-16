package ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mvi.java.R;

import java.util.ArrayList;
import java.util.List;

import common.CoffeeData;
import domain.CoffeeService;

public class CoffeeRepoAdapter extends BaseAdapter {

        private List<CoffeeData> gitHubRepos = new ArrayList<>();

        @Override public int getCount() {
            return gitHubRepos.size();
        }

        @Override public CoffeeData getItem(int position) {
            if (position < 0 || position >= gitHubRepos.size()) {
                return null;
            } else {
                return gitHubRepos.get(position);
            }
        }

        @Override public long getItemId(int position) {
            return position;
        }

        @Override public View getView(int position, View convertView, ViewGroup parent) {
            final View view = (convertView != null ? convertView : createView(parent));
            final CoffeeDataViewHolder viewHolder = (CoffeeDataViewHolder) view.getTag();
            viewHolder.setCoffeeData(getItem(position));
            return view;
        }

        public void setGitHubRepos(@Nullable List<CoffeeData> repos) {
            if (repos == null) {
                return;
            }
            gitHubRepos.clear();
            gitHubRepos.addAll(repos);
            notifyDataSetChanged();
        }

        private View createView(ViewGroup parent) {
            final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            final View view = inflater.inflate(R.layout.coffee_item, parent, false);
            final CoffeeDataViewHolder viewHolder = new CoffeeDataViewHolder(view);
            view.setTag(viewHolder);
            return view;
        }

        private static class CoffeeDataViewHolder {

            private TextView textRepoName;
            private TextView textRepoDescription;
            private TextView textLanguage;
            private TextView textStars;

            public CoffeeDataViewHolder(View view) {

            }

            public void setCoffeeData(CoffeeData coffeeData) {

            }
        }
    }
