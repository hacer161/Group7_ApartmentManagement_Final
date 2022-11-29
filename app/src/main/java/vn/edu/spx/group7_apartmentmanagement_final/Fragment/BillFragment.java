package vn.edu.spx.group7_apartmentmanagement_final.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import vn.edu.spx.group7_apartmentmanagement_final.Adapter.AdapterSpinnerElectricbill;
import vn.edu.spx.group7_apartmentmanagement_final.Adapter.AdapterSpinnerRoom;
import vn.edu.spx.group7_apartmentmanagement_final.Adapter.AdapterSpinnerRoomPrice;
import vn.edu.spx.group7_apartmentmanagement_final.Adapter.AdapterSpinnerServicebill;
import vn.edu.spx.group7_apartmentmanagement_final.Adapter.AdapterSpinnerTenant;
import vn.edu.spx.group7_apartmentmanagement_final.Adapter.AdapterSpinnerWaterBill;
import vn.edu.spx.group7_apartmentmanagement_final.Adapter.Adapter_Bill;
import vn.edu.spx.group7_apartmentmanagement_final.Adapter.Adapter_Room;
import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Bill;
import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Room;
import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Tenant;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Bill;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Room;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Tenant;
import vn.edu.spx.group7_apartmentmanagement_final.R;

public class BillFragment extends Fragment {
    int cyearcon, cmonthcon, cdaycon;
    private RecyclerView rcv_list_bill;
    private FloatingActionButton fab_add_bill;
    DAO_Room dao_room;
    AdapterSpinnerRoom adapterSpinnerRoom;
    AdapterSpinnerRoomPrice adapterSpinnerRoomPrice;
    AdapterSpinnerWaterBill adapterSpinnerWaterBill;
    AdapterSpinnerElectricbill adapterSpinnerElectricbill;
    AdapterSpinnerServicebill adapterSpinnerServicebill;
    ArrayList<Room> listRoom;
    ArrayList<Bill> listBill;
    Adapter_Bill adapter_bill;
    DAO_Bill dao_bill;
    Context context;
    String TAG = "zzzzzzzzzzzz";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bill, container, false);
        context = getContext();
        dao_room = new DAO_Room(context);
        dao_bill = new DAO_Bill(context);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv_list_bill = view.findViewById(R.id.rcv_list_bill);
        dao_room.opend();
        dao_bill.opend();
        listRoom = dao_room.selectAll();
        listBill = dao_bill.selectAll();
        adapterSpinnerRoom = new AdapterSpinnerRoom(listRoom);
        adapterSpinnerRoomPrice = new AdapterSpinnerRoomPrice(listRoom);
        adapterSpinnerWaterBill= new AdapterSpinnerWaterBill(listRoom);
        adapterSpinnerElectricbill = new AdapterSpinnerElectricbill(listRoom);
        adapterSpinnerServicebill = new AdapterSpinnerServicebill(listRoom);
        adapter_bill = new Adapter_Bill(dao_room, adapterSpinnerRoom,adapterSpinnerRoomPrice,adapterSpinnerWaterBill,adapterSpinnerElectricbill,adapterSpinnerServicebill, listRoom,listBill, dao_bill);
        rcv_list_bill.setAdapter(adapter_bill);
        fab_add_bill = view.findViewById(R.id.fab_add_bill);
        Log.e(TAG, "dialogAddTenant: " + listRoom.size());
        fab_add_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddBill();
                listRoom = dao_room.selectAll();
                listBill = dao_bill.selectAll();
                adapter_bill = new Adapter_Bill(dao_room, adapterSpinnerRoom,adapterSpinnerRoomPrice,adapterSpinnerWaterBill,adapterSpinnerElectricbill,adapterSpinnerServicebill, listRoom,listBill, dao_bill);
                rcv_list_bill.setAdapter(adapter_bill);
            }
        });
    }

    private Spinner spinner_roomnumber_ADDBILL;
    private Spinner spinner_roomprice_ADDBILL;
    private Spinner spinner_waterbill_ADDBILL;
    private Spinner spinner_electricbill_ADDBILL;
    private Spinner spinner_servicebill_ADDBILL;
    private MaterialButton btnADDBill;

    public void dialogAddBill() {
        final Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_add_bill);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        spinner_roomnumber_ADDBILL = dialog.findViewById(R.id.spinner_roomnumber_ADDBILL);
        spinner_roomprice_ADDBILL = dialog.findViewById(R.id.spinner_roomprice_ADDBILL);
        spinner_waterbill_ADDBILL = dialog.findViewById(R.id.spinner_waterbill_ADDBILL);
        spinner_electricbill_ADDBILL = dialog.findViewById(R.id.spinner_electricbill_ADDBILL);
        spinner_servicebill_ADDBILL = dialog.findViewById(R.id.spinner_service_ADDBILL);
        btnADDBill = dialog.findViewById(R.id.btn_ADDBILL);
        spinner_roomnumber_ADDBILL.setAdapter(adapterSpinnerRoom);
        spinner_roomprice_ADDBILL.setAdapter(adapterSpinnerRoomPrice);
        spinner_waterbill_ADDBILL.setAdapter(adapterSpinnerWaterBill);
        spinner_electricbill_ADDBILL.setAdapter(adapterSpinnerElectricbill);
        spinner_servicebill_ADDBILL.setAdapter(adapterSpinnerServicebill);
        btnADDBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check() == true) {
                    Bill bill = new Bill();
                    Room Room = (Room) spinner_roomnumber_ADDBILL.getSelectedItem();
                    Room Room1 = (Room) spinner_roomprice_ADDBILL.getSelectedItem();
                    Room Room2 = (Room) spinner_waterbill_ADDBILL.getSelectedItem();
                    Room Room3 = (Room) spinner_electricbill_ADDBILL.getSelectedItem();
                    Room Room4 = (Room) spinner_servicebill_ADDBILL.getSelectedItem();
                    Room.setRoomNumber1(Room.getRoomNumber1());
                    Room.setRoomPrice1M(Room1.getRoomPrice1M());
                    Room.setWaterBill1M(Room2.getWaterBill1M()*20000);
                    Room.setElectricBill1M(Room3.getElectricBill1M()*3000);
                    Room.setServiceBill1M(Room4.getServiceBill1M());
                    bill.setIdRoom(Room.getIdRoom());
                    Log.e(TAG, "onClick: " + Room.getIdRoom());
                    bill.setRoomNumber2(Room.getRoomNumber1());
                    Log.e(TAG, "onClick: " + Room.getRoomNumber1());
                    if (dao_bill.insertBILL(bill) > 0) {
                        listBill.clear();
                        listBill.addAll(dao_bill.selectAll());
                        adapter_bill.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Thêm mới thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Thêm mới thất bại", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        dialog.show();
    }

    public boolean check() {
        if (spinner_roomnumber_ADDBILL.getSelectedItem().toString().trim().isEmpty()) {
            if (spinner_roomnumber_ADDBILL.getSelectedItem().toString().trim().isEmpty()) {
                spinner_roomnumber_ADDBILL.setPrompt("Số phòng không được để trống");
            } else {
                spinner_roomnumber_ADDBILL.setPrompt("");
            }
            return false;
        } else {
            return true;
        }
    }
}


