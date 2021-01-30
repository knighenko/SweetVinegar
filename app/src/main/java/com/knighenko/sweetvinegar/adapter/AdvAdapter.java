package com.knighenko.sweetvinegar.adapter;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.knighenko.sweetvinegar.R;
import com.knighenko.sweetvinegar.entity.Advertisement;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;


public class AdvAdapter extends RecyclerView.Adapter<AdvAdapter.ViewHolder> {

    private ArrayList<Advertisement> advertisementsList = new ArrayList<>();


    @NonNull
    @Override
    public AdvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_advertisement, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdvAdapter.ViewHolder holder, int position) {
        holder.bind(advertisementsList.get(position));
    }

    public void setListAdv(Collection<Advertisement> advertisements) {
        this.advertisementsList.addAll(advertisements);
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return advertisementsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleView;
        private TextView descriptionView;
        private ImageView imageView;

        ViewHolder(View view) {
            super(view);
            titleView = (TextView) view.findViewById(R.id.textViewTitleAdv);
            descriptionView = (TextView) view.findViewById(R.id.textViewDescription);
            imageView = (ImageView) view.findViewById(R.id.bigImageViewAdv);
        }

        public void bind(Advertisement adv) {
            titleView.setText(adv.getTitle());
            titleView.setPaintFlags(titleView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            descriptionView.setText(adv.getDescription());
            String urlImg = adv.getImageSrc();
            paintImg(urlImg);
        }

        private void paintImg(String urlImg) {
            if (urlImg.equals("No Img")) {
                imageView.setImageResource(R.drawable.no_image);
            } else {
                Picasso.get().load(urlImg).resize(200, 200).centerCrop().into(imageView);
            }
        }
    }
}
