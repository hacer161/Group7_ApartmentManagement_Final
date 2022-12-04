package vn.edu.spx.group7_apartmentmanagement_final.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.edu.spx.group7_apartmentmanagement_final.R;

public class ViewHolder_Bill extends RecyclerView.ViewHolder {
    public TextView tv_roomnumber_bill;
    public TextView tv_roomprice_bill;
    public TextView tv_details_bill;
    public ImageView imgUpdateBill;
    public ImageView imgDeleteBill;
    public ViewHolder_Bill(@NonNull View itemView) {
        super(itemView);
        tv_roomnumber_bill=itemView.findViewById(R.id.tv_room_numberIBILL);
        tv_roomprice_bill=itemView.findViewById(R.id.tv_room_priceIBILL);
        tv_details_bill=itemView.findViewById(R.id.tv_details_Bill);
        imgUpdateBill=itemView.findViewById(R.id.img_updateBILL);
        imgDeleteBill=itemView.findViewById(R.id.img_deleteBILL);
    }
}
