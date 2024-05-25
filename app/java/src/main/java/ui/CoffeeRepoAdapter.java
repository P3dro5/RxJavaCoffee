package ui;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.mvi.java.R;

import java.util.ArrayList;
import java.util.List;

import common.CoffeeDataDTO;

public class CoffeeRepoAdapter extends BaseAdapter {

        private final List<CoffeeDataDTO> rxCoffeeDto = new ArrayList<>();

        @Override public int getCount() {
            return rxCoffeeDto.size();
        }

        @Override public CoffeeDataDTO getItem(int position) {
            if (position < 0 || position >= rxCoffeeDto.size()) {
                return null;
            } else {
                return rxCoffeeDto.get(position);
            }
        }

        @Override public long getItemId(int position) {
            return position;
        }

        @Override public View getView(int position, View convertView, ViewGroup parent) {
            final View view = (convertView != null ? convertView : createView(parent));
            final CoffeeDataViewHolder viewHolder = (CoffeeDataViewHolder) view.getTag();
            viewHolder.setCoffeeData(getItem(position));
            Glide.with(view)
                    .load(getItem(position).image)
                    .into((ImageView) view.findViewById(R.id.coffee_image));
            return view;
        }

        public void setRxJavaCoffees(@Nullable List<CoffeeDataDTO> repos) {
            if (repos == null) {
                return;
            }
            rxCoffeeDto.clear();
            rxCoffeeDto.addAll(repos);
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

            private final TextView coffeeName;
            private final TextView coffeeDescription;
            private final ImageView coffeeImage;

            public CoffeeDataViewHolder(View view) {
                coffeeName = (TextView) view.findViewById(R.id.text_coffee_name);
                coffeeDescription = (TextView) view.findViewById(R.id.text_coffee_description);
                coffeeImage = (ImageView) view.findViewById(R.id.coffee_image);
            }

            public void setCoffeeData(CoffeeDataDTO coffeeDataDTO) {
                coffeeName.setText(coffeeDataDTO.title);
                coffeeDescription.setText(coffeeDataDTO.description);
                coffeeImage.setImageURI(Uri.parse(coffeeDataDTO.image));
            }
        }
    }
