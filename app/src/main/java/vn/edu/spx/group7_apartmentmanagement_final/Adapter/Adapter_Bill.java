package vn.edu.spx.group7_apartmentmanagement_final.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Bill;
import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Room;
import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Tenant;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Bill;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Room;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Tenant;
import vn.edu.spx.group7_apartmentmanagement_final.R;
import vn.edu.spx.group7_apartmentmanagement_final.ViewHolder.ViewHolder_Bill;

public class Adapter_Bill extends RecyclerView.Adapter<ViewHolder_Bill> {
    int cyearup,cmonthup,cdayup;
    DAO_Room dao_room;
    AdapterSpinnerRoom adapterSpinnerRoom;
    AdapterSpinnerRoomPrice adapterSpinnerRoomPrice;
    AdapterSpinnerWaterBill adapterSpinnerWaterBill;
    AdapterSpinnerElectricbill adapterSpinnerElectricbill;
    AdapterSpinnerServicebill adapterSpinnerServicebill;
    ArrayList<Room> listRoom;
    ArrayList<Bill> listBill;
    DAO_Bill dao_bill;
    Context context;

    public Adapter_Bill(DAO_Room dao_room, AdapterSpinnerRoom adapterSpinnerRoom,AdapterSpinnerRoomPrice adapterSpinnerRoomPrice,AdapterSpinnerWaterBill adapterSpinnerWaterBill, AdapterSpinnerElectricbill adapterSpinnerElectricbill,AdapterSpinnerServicebill adapterSpinnerServicebill, ArrayList<Room> listRoom, ArrayList<Bill> listBill, DAO_Bill dao_bill) {
        this.dao_room = dao_room;
        this.adapterSpinnerRoom = adapterSpinnerRoom;
        this.adapterSpinnerRoomPrice=adapterSpinnerRoomPrice;
        this.adapterSpinnerWaterBill=adapterSpinnerWaterBill;
        this.adapterSpinnerElectricbill=adapterSpinnerElectricbill;
        this.adapterSpinnerServicebill=adapterSpinnerServicebill;
        this.listRoom = listRoom;
        this.listBill = listBill;
        this.dao_bill = dao_bill;
    }

    @NonNull
    @Override
    public ViewHolder_Bill onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View row = layoutInflater.inflate(R.layout.item_bill, parent, false);
        ViewHolder_Bill viewHolder_bill = new ViewHolder_Bill(row);
        context = parent.getContext();
        dao_room = new DAO_Room(context);
        dao_bill = new DAO_Bill(context);
        dao_bill.opend();
        dao_room.opend();
        return viewHolder_bill;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_Bill holder, int position) {
        final int index = position;
        listBill = dao_bill.selectAll();
        listRoom = dao_room.selectAll();
        Room room=listRoom.get(index);
        Bill bill=listBill.get(index);
        holder.tv_roomnumber_bill.setText(room.getRoomNumber1());
        holder.imgUpdateBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogUpdate(bill, index);
            }
        });
        holder.imgDeleteBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDelete(bill, index);
            }
        });
        holder.tv_details_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }


    private Spinner spinner_roomnumber_UPBILL;
    private Spinner spinner_roomprice_UPBILL;
    private Spinner spinner_waterbill_UPBILL;
    private Spinner spinner_electricbill_UPBILL;
    private Spinner spinner_servicebill_UPBILL;
    private MaterialButton btnUPBill;

    public void dialogUpdate(Bill bill, int index) {
        Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_update_room);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        spinner_roomnumber_UPBILL = dialog.findViewById(R.id.spinner_roomnumber_UPBILL);
        spinner_roomprice_UPBILL = dialog.findViewById(R.id.spinner_roomprice_UPBILL);
        spinner_waterbill_UPBILL = dialog.findViewById(R.id.spinner_waterbill_UPBILL);
        spinner_electricbill_UPBILL = dialog.findViewById(R.id.spinner_electricbill_UPBILL);
        spinner_servicebill_UPBILL = dialog.findViewById(R.id.spinner_service_UPBILL);
        btnUPBill = dialog.findViewById(R.id.btn_UPBILL);

        adapterSpinnerRoom = new AdapterSpinnerRoom(listRoom);
        adapterSpinnerRoomPrice=new AdapterSpinnerRoomPrice(listRoom);
        adapterSpinnerWaterBill=new AdapterSpinnerWaterBill(listRoom);
        adapterSpinnerElectricbill=new AdapterSpinnerElectricbill(listRoom);
        adapterSpinnerServicebill=new AdapterSpinnerServicebill(listRoom);
        spinner_roomnumber_UPBILL.setAdapter(adapterSpinnerRoom);
        spinner_roomprice_UPBILL.setAdapter(adapterSpinnerRoomPrice);
        spinner_waterbill_UPBILL.setAdapter(adapterSpinnerWaterBill);
        spinner_electricbill_UPBILL.setAdapter(adapterSpinnerElectricbill);
        spinner_servicebill_UPBILL.setAdapter(adapterSpinnerServicebill);
        try {
            for (int i = 0; i < listRoom.size(); i++) {
                if (bill.getIdRoom() == listRoom.get(i).getIdRoom()) {
                    spinner_roomnumber_UPBILL.setSelection(i);
                    spinner_roomnumber_UPBILL.setSelected(true);
                    spinner_roomprice_UPBILL.setSelection(i);
                    spinner_roomprice_UPBILL.setSelected(true);
                    spinner_waterbill_UPBILL.setSelection(i);
                    spinner_waterbill_UPBILL.setSelected(true);
                    spinner_electricbill_UPBILL.setSelection(i);
                    spinner_electricbill_UPBILL.setSelected(true);
                    spinner_servicebill_UPBILL.setSelection(i);
                    spinner_servicebill_UPBILL.setSelected(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        };
        btnUPBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkErrorUp() == true) {
                    Room room = (Room) spinner_roomnumber_UPBILL.getSelectedItem();
                    Room room1 = (Room) spinner_roomprice_UPBILL.getSelectedItem();
                    Room room2 = (Room) spinner_waterbill_UPBILL.getSelectedItem();
                    Room room3 = (Room) spinner_electricbill_UPBILL.getSelectedItem();
                    Room room4 = (Room) spinner_servicebill_UPBILL.getSelectedItem();
                    room.setIdRoom(room.getIdRoom());
                    room1.setIdRoom(room1.getIdRoom());
                    room2.setIdRoom(room2.getIdRoom());
                    room3.setIdRoom(room3.getIdRoom());
                    room4.setIdRoom(room4.getIdRoom());
                    if (dao_bill.updateBill(bill) > 0) {
                        listBill.set(index, bill);
                        notifyItemChanged(index);
                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        dialog.show();
    }

    public void dialogDelete(Bill bill, int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("delete");
        builder.setMessage("Bạn có muốn xóa hóa đơn số: " + bill.getIdBill());
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int res = dao_bill.deleteBill(bill);
                if (res > 0) {
                    listRoom.remove(index);
                    notifyItemRemoved(index);
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public boolean checkErrorUp() {
        if (spinner_roomnumber_UPBILL.getSelectedItem().toString().trim().isEmpty()) {
            if (spinner_roomnumber_UPBILL.getSelectedItem().toString().trim().isEmpty()) {
                spinner_roomnumber_UPBILL.setPrompt("Số phòng không được để trống");
            } else {
                spinner_roomnumber_UPBILL.setPrompt("");
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int getItemCount() {
        return listRoom == null ? 0 : listRoom.size();
    }

    private TextView tv_id_room;
    private TextView tv_room_number_detailsROOM;
    private TextView tv_tenant_name_detailsROOM;
    private TextView tv_room_price_detailsROOM;
    private TextView tv_waterbill_detailsROOM;
    private TextView tv_electricbill_detailsROOM;
    private TextView tv_servicebill_detailsROOM;


    public void dialogroomdetails(Room obj) {
        Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.layout_details_room);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        tv_id_room = dialog.findViewById(R.id.tv_id_room);
        tv_room_number_detailsROOM = dialog.findViewById(R.id.tv_room_number_detailsROOM);
        tv_tenant_name_detailsROOM = dialog.findViewById(R.id.tv_tenant_detailsROOM);
        tv_room_price_detailsROOM = dialog.findViewById(R.id.tv_roomprice_detailsROOM);
        tv_waterbill_detailsROOM = dialog.findViewById(R.id.tv_waterbillROOM);
        tv_electricbill_detailsROOM = dialog.findViewById(R.id.tv_electricbillROOM);
        tv_servicebill_detailsROOM = dialog.findViewById(R.id.tv_servicebillROOM);
        tv_id_room.setText("Mã số phòng: " + obj.getIdRoom());
        tv_room_number_detailsROOM.setText("Số phòng: " + obj.getRoomNumber1());
        tv_tenant_name_detailsROOM.setText("Người Thuê: " + obj.getIdTenant() + "|" + obj.getTenantName());
        tv_room_price_detailsROOM.setText("Tiền phòng 1 tháng: " + obj.getRoomPrice1M());
        tv_waterbill_detailsROOM.setText("Tiền nước 1 khối: " + obj.getWaterBill1M());
        tv_electricbill_detailsROOM.setText("Tiền điện 1 kWH: " + obj.getElectricBill1M());
        tv_servicebill_detailsROOM.setText("Tiền dịch vụ hàng tháng: " + obj.getServiceBill1M());
        dialog.show();
    }

}
