package com.tw1stedrain.carflipper.recyclerViewNeeds;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tw1stedrain.carflipper.R;
import com.tw1stedrain.carflipper.activities.DetailActivity;
import com.tw1stedrain.carflipper.models.Car;

import java.util.List;

public class CarLayoutAdapter extends RecyclerView.Adapter<CarLayoutAdapter.CarHolder> {

    public static class CarHolder extends RecyclerView.ViewHolder {

        public TextView textNickname;
        public TextView textMake;
        public TextView textModel;

        public CarHolder(@NonNull View itemView) {
            super(itemView);

            this.textNickname = itemView.findViewById(R.id.nickname);
            this.textMake = itemView.findViewById(R.id.make);
            this.textModel = itemView.findViewById(R.id.model);
        }

        public void setCar(final Car car) {
            this.textNickname.setText(car.getNickname());
            this.textMake.setText(car.getMake());
            this.textModel.setText(car.getModel());

            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = itemView.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("id", car.getId());
                    context.startActivity(intent);
                }
            });
        }
    }

    private List<Car> cars;

    public CarLayoutAdapter(List<Car> cars) {
        this.cars = cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.car_view, parent, false);

        CarHolder holder = new CarHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CarHolder holder, int position) {
        Car car = cars.get(position);
        holder.setCar(car);
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }


}
