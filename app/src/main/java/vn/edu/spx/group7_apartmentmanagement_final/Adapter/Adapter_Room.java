package vn.edu.spx.group7_apartmentmanagement_final.Adapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.Calendar;

import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Contract;
import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Room;
import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Tenant;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Contract;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Room;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Tenant;
import vn.edu.spx.group7_apartmentmanagement_final.R;
import vn.edu.spx.group7_apartmentmanagement_final.ViewHolder.ViewHolder_Room;

public class Adapter_Room extends RecyclerView.Adapter<ViewHolder_Room> {
    int cyearup,cmonthup,cdayup;
    DAO_Tenant dao_tenant;
    AdapterSpinnerTenant adapterSpinnerTenant;
    ArrayList<Tenant> listTenant;
    ArrayList<Room> listRoom;
    DAO_Room dao_room;
    Context context;

    public Adapter_Room(DAO_Tenant dao_tenant, AdapterSpinnerTenant adapterSpinnerTenant, ArrayList<Tenant> listTenant, ArrayList<Room> listRoom, DAO_Room dao_room) {
        this.dao_tenant = dao_tenant;
        this.adapterSpinnerTenant = adapterSpinnerTenant;
        this.listTenant = listTenant;
        this.listRoom = listRoom;
        this.dao_room = dao_room;
    }

    @NonNull
    @Override
    public ViewHolder_Room onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View row = layoutInflater.inflate(R.layout.item_room, parent, false);
        ViewHolder_Room viewHolder_room = new ViewHolder_Room(row);
        context = parent.getContext();
        dao_room = new DAO_Room(context);
        dao_tenant = new DAO_Tenant(context);
        dao_room.opend();
        dao_tenant.opend();
        return viewHolder_room;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_Room holder, int position) {
        final int index = position;
        listRoom = dao_room.selectAll();
        listTenant = dao_tenant.selectAll();
        Room room = listRoom.get(index);
        holder.tv_room_numberIR.setText("Số phòng: " +room.getRoomNumber1());
        if(room.getTenantName().equals("CHƯA CÓ NGƯỜI THUÊ")){
            holder.tv_room_status.setTextColor(Color.parseColor("#74E813"));
            holder.tv_room_status.setText("Trạng thái: Phòng còn trống");
        }else{
            holder.tv_room_status.setText("Trạng thái: Đang được thuê");
        }
        int price=Integer.parseInt(room.getRoomPrice1M()+"");
        double water=Double.parseDouble(room.getWaterBill1M()+"");
        double electric=Double.parseDouble(room.getElectricBill1M()+"");
        double service=Double.parseDouble(room.getServiceBill1M()+"");
        double total=price+water+electric+service;
        holder.tv_room_pricetotalIR.setText("Tổng tiền phòng: "+String.valueOf(total));
 /*       if(inputRoomPriceUPROOM.getEditText().getText().toString().length()==0){
            inputRoomPriceUPROOM.getEditText().setText("0");
        }
        if(inputWaterBillUPROOM.getEditText().getText().toString().length()==0){
            inputWaterBillUPROOM.getEditText().setText("0");
        }
        if(inputElectricBillUPROOM.getEditText().getText().toString().length()==0){
            inputElectricBillUPROOM.getEditText().setText("0");
        }
        if(inputServiceBillUPROOM.getEditText().getText().toString().length()==0){
            inputServiceBillUPROOM.getEditText().setText("0");
        }
        int price=Integer.parseInt(inputRoomPriceUPROOM.getEditText().getText().toString());
        double water=Double.parseDouble(inputWaterBillUPROOM.getEditText().getText().toString())*20000;
        double electric=Double.parseDouble(inputElectricBillUPROOM.getEditText().getText().toString())*3000;
        double service=Double.parseDouble(inputServiceBillUPROOM.getEditText().getText().toString());
        double total=price+water+electric+service;*/

        holder.imgUpdateRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogUpdate(room, index);
            }
        });
        holder.imgDeleteRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDelete(room, index);
            }
        });
        holder.tv_details_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogroomdetails(room);
            }
        });
    }


    private Spinner spinner_tenant_nameUPROOM;
    private TextInputLayout inputRoomNumberUPROOM;
    private TextInputLayout inputRoomPriceUPROOM;
    private TextInputLayout inputWaterBillUPROOM;
    private TextInputLayout inputElectricBillUPROOM;
    private TextInputLayout inputServiceBillUPROOM;
    private MaterialButton btnUPROOM;

    public void dialogUpdate(Room room, int index) {
        Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_update_room);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        spinner_tenant_nameUPROOM = dialog.findViewById(R.id.spinner_tenant_nameUPROOM);
        inputRoomNumberUPROOM = dialog.findViewById(R.id.input_room_numberUPROOM);
        inputRoomPriceUPROOM = dialog.findViewById(R.id.input_roompriceUPROOM);
        inputWaterBillUPROOM = dialog.findViewById(R.id.input_waterbill_UPROOM);
        inputElectricBillUPROOM = dialog.findViewById(R.id.input_electricbill_UPROOM);
        inputServiceBillUPROOM = dialog.findViewById(R.id.input_roomserviceUPROOM);
        btnUPROOM = dialog.findViewById(R.id.btn_UPRoom);

        adapterSpinnerTenant = new AdapterSpinnerTenant(listTenant);
        spinner_tenant_nameUPROOM.setAdapter(adapterSpinnerTenant);
        try {
            for (int i = 0; i < listRoom.size(); i++) {
                if (room.getIdTenant() == listTenant.get(i).getIdTenant()) {
                    spinner_tenant_nameUPROOM.setSelection(i);
                    spinner_tenant_nameUPROOM.setSelected(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        inputRoomNumberUPROOM.getEditText().setText(room.getRoomNumber1());
        inputRoomPriceUPROOM.getEditText().setText(room.getRoomPrice1M() + "");
        inputWaterBillUPROOM.getEditText().setText(room.getWaterBill1M()+"");
        inputElectricBillUPROOM.getEditText().setText(room.getElectricBill1M()+"");
        inputServiceBillUPROOM.getEditText().setText(room.getServiceBill1M()+"");
        btnUPROOM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkErrorUp() == true) {
                    Tenant tenant = (Tenant) spinner_tenant_nameUPROOM.getSelectedItem();
                    room.setTenantName(tenant.getTenantName());
                    room.setIdTenant(tenant.getIdTenant());
                    room.setRoomNumber1(inputRoomNumberUPROOM.getEditText().getText().toString().trim());
                    room.setRoomPrice1M(Integer.parseInt(inputRoomPriceUPROOM.getEditText().getText().toString().trim()));
                    room.setWaterBill1M(Double.parseDouble(inputWaterBillUPROOM.getEditText().getText().toString().trim()));
                    room.setElectricBill1M(Double.parseDouble(inputElectricBillUPROOM.getEditText().getText().toString().trim()));
                    room.setServiceBill1M(Double.parseDouble(inputServiceBillUPROOM.getEditText().getText().toString().trim()));
                    if (dao_room.updateRoom(room) > 0) {
                        listRoom.set(index, room);
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

    public void dialogDelete(Room room, int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("delete");
        builder.setMessage("Bạn có muốn xóa hợp đồng phòng: " + room.getRoomNumber1());
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int res = dao_room.deleteContract(room);
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
        if (inputRoomNumberUPROOM.getEditText().getText().toString().trim().isEmpty()) {
            if (inputRoomNumberUPROOM.getEditText().getText().toString().trim().isEmpty()) {
                inputRoomNumberUPROOM.setError("Số phòng không được để trống");
            } else {
                inputRoomNumberUPROOM.setError("");
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
    private TextView tv_waterbilltotal_ROOM;
    private TextView tv_electricbilltotal_ROOM;
    private TextView tv_moneybilltotal_ROOM;


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
        tv_waterbilltotal_ROOM=dialog.findViewById(R.id.tv_waterbilltotalROOM);
        tv_electricbilltotal_ROOM=dialog.findViewById(R.id.tv_electricbilltotalROOM);
        tv_servicebill_detailsROOM = dialog.findViewById(R.id.tv_servicebillROOM);
        tv_moneybilltotal_ROOM=dialog.findViewById(R.id.tv_moneybilltotalROOM);
        tv_id_room.setText("Mã số phòng: " + obj.getIdRoom());
        tv_room_number_detailsROOM.setText("Số phòng: " + obj.getRoomNumber1());
        tv_tenant_name_detailsROOM.setText("Người Thuê: " + obj.getIdTenant() + "|" + obj.getTenantName());
        tv_room_price_detailsROOM.setText("Tiền phòng 1 tháng: " + obj.getRoomPrice1M());
        tv_waterbill_detailsROOM.setText("Số khối nước đã sử dụng: " + obj.getWaterBill1M());
        tv_electricbill_detailsROOM.setText("Số kWH điện đã sử dụng: " + obj.getElectricBill1M());
        tv_waterbilltotal_ROOM.setText("Số tiền nước: "+obj.getWaterBill1M());
        tv_electricbilltotal_ROOM.setText("Số tiền điện: "+obj.getElectricBill1M());
        tv_servicebill_detailsROOM.setText("Tiền dịch vụ hàng tháng: " + obj.getServiceBill1M());
        dialog.show();
    }

}
