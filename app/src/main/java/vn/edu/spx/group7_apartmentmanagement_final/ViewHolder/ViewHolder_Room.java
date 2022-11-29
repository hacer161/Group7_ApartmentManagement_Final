package vn.edu.spx.group7_apartmentmanagement_final.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.edu.spx.group7_apartmentmanagement_final.R;

public class ViewHolder_Room extends RecyclerView.ViewHolder {
    public TextView tv_room_status;
    public TextView tv_room_numberIR;
    public TextView tv_room_pricetotalIR;
    public TextView tv_details_room;
    public ImageView imgUpdateRoom;
    public ImageView imgDeleteRoom;
    public ViewHolder_Room(@NonNull View itemView) {
        super(itemView);
        tv_room_numberIR = itemView.findViewById(R.id.tv_room_numberIR);
        tv_room_status = itemView.findViewById(R.id.tv_room_statusIR);
        tv_room_pricetotalIR=itemView.findViewById(R.id.tv_room_pricetotalIR);
        tv_details_room=itemView.findViewById(R.id.tv_details_room);
        imgUpdateRoom = itemView.findViewById(R.id.img_updateRoom);
        imgDeleteRoom = itemView.findViewById(R.id.img_deleteRoom);
    }
}
