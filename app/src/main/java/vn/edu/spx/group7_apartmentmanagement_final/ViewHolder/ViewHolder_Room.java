package vn.edu.spx.group7_apartmentmanagement_final.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.edu.spx.group7_apartmentmanagement_final.R;

public class ViewHolder_Room extends RecyclerView.ViewHolder {
    public TextView tv_date;
    public TextView tv_room_number;
    public TextView tv_chitiet;
    public ImageView imgUpdateRoom;
    public ImageView imgDeleteRoom;
    public ViewHolder_Room(@NonNull View itemView) {
        super(itemView);
        tv_date = itemView.findViewById(R.id.tv_date);
        tv_room_number = itemView.findViewById(R.id.tv_room_number);
        tv_chitiet = itemView.findViewById(R.id.tv_details);
        imgUpdateRoom = itemView.findViewById(R.id.img_updateRoom);
        imgDeleteRoom = itemView.findViewById(R.id.img_deleteRoom);
    }
}
