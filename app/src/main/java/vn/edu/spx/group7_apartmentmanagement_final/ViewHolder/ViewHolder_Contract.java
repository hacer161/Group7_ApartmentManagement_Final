package vn.edu.spx.group7_apartmentmanagement_final.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.edu.spx.group7_apartmentmanagement_final.R;

public class ViewHolder_Contract extends RecyclerView.ViewHolder {
    public TextView tv_room_number_contract;
    public TextView tv_roomprice_contract;
    public TextView tv_details_contract;
    public ImageView imgUpdateContract;
    public ImageView imgDeleteContract;
    public ViewHolder_Contract(@NonNull View itemView) {
        super(itemView);
        tv_room_number_contract = itemView.findViewById(R.id.tv_room_number);
        tv_roomprice_contract = itemView.findViewById(R.id.tv_room_price);
        tv_details_contract = itemView.findViewById(R.id.tv_details_contract);
        imgUpdateContract = itemView.findViewById(R.id.img_updateContract);
        imgDeleteContract = itemView.findViewById(R.id.img_deleteContract);
    }
}