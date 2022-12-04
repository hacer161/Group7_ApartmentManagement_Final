package vn.edu.spx.group7_apartmentmanagement_final.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.Calendar;

import vn.edu.spx.group7_apartmentmanagement_final.Adapter.AdapterSpinnerTenant;
import vn.edu.spx.group7_apartmentmanagement_final.Adapter.Adapter_Contract;
import vn.edu.spx.group7_apartmentmanagement_final.Adapter.Adapter_Room;
import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Contract;
import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Room;
import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Tenant;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Contract;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Room;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Tenant;
import vn.edu.spx.group7_apartmentmanagement_final.R;

public class RoomFragment extends Fragment {
    int cyearcon, cmonthcon, cdaycon;
    private RecyclerView rcv_list_room;
    private FloatingActionButton fab_add_room;
    DAO_Tenant dao_tenant;
    AdapterSpinnerTenant adapterSpinnerTenant;
    ArrayList<Tenant> listTenant;
    ArrayList<Room> listRoom;
    ArrayList<Tenant> listTenant1=new ArrayList<>();
    DAO_Room dao_room;
    Adapter_Room adapter_room;
    Context context;
    String TAG = "zzzzzzzzzzzz";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room, container, false);
        context = getContext();
        dao_room = new DAO_Room(context);
        dao_tenant = new DAO_Tenant(context);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv_list_room = view.findViewById(R.id.rcv_list_room);
        dao_room.opend();
        dao_tenant.opend();
        listRoom = dao_room.selectAll();
        listTenant = dao_tenant.selectAll();
        adapterSpinnerTenant = new AdapterSpinnerTenant(listTenant);
        adapter_room = new Adapter_Room(dao_tenant, adapterSpinnerTenant, listTenant, listRoom, dao_room);
        rcv_list_room.setAdapter(adapter_room);
        fab_add_room = view.findViewById(R.id.fab_add_room);
        Log.e(TAG, "dialogAddTenant: " + listTenant.size());
        fab_add_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddContract();
                listRoom = dao_room.selectAll();
                listTenant = dao_tenant.selectAll();
                adapter_room = new Adapter_Room(dao_tenant, adapterSpinnerTenant, listTenant, listRoom, dao_room);
                rcv_list_room.setAdapter(adapter_room);
            }
        });
    }

    private Spinner spinnerTenant;
    private TextInputLayout inputRoomNumberADDROOM;
    private TextInputLayout inputRoomPriceADDROOM;
    private TextInputLayout inputWaterBillADDROOM;
    private TextInputLayout inputElectricBillADDROOM;
    private TextInputLayout inputServiceBillADDROOM;
    private MaterialButton btnADDRoom;

    public void dialogAddContract() {
        final Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_add_room);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        spinnerTenant = dialog.findViewById(R.id.spinner_tenant_nameADDROOM);
        inputRoomNumberADDROOM = dialog.findViewById(R.id.input_room_numberADDROOM);
        inputRoomPriceADDROOM = dialog.findViewById(R.id.input_roompriceADDROOM);
        inputWaterBillADDROOM = dialog.findViewById(R.id.input_waterbill_ADDROOM);
        inputElectricBillADDROOM = dialog.findViewById(R.id.input_electricbill_ADDROOM);
        inputServiceBillADDROOM = dialog.findViewById(R.id.input_roomserviceADDROOM);
        btnADDRoom = dialog.findViewById(R.id.btn_ADDRoom);
        spinnerTenant.setAdapter(adapterSpinnerTenant);
        btnADDRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check() == true) {
                    Room room = new Room();
                    Tenant tenant = (Tenant) spinnerTenant.getSelectedItem();
                    room.setRoomNumber1(inputRoomNumberADDROOM.getEditText().getText().toString().trim());
                        room.setRoomPrice1M(Integer.parseInt(inputRoomPriceADDROOM.getEditText().getText().toString().trim()));
                        room.setWaterBill1M(Double.parseDouble(inputWaterBillADDROOM.getEditText().getText().toString().trim()));
                        room.setElectricBill1M(Double.parseDouble(inputElectricBillADDROOM.getEditText().getText().toString().trim()));
                        room.setServiceBill1M(Double.parseDouble(inputServiceBillADDROOM.getEditText().getText().toString().trim()));
                    room.setIdTenant(tenant.getIdTenant());
                    Log.e(TAG, "onClick: " + tenant.getIdTenant());
                    room.setTenantName(tenant.getTenantName());
                    Log.e(TAG, "onClick: " + tenant.getTenantName());
                    if (dao_room.insertRoom(room) > 0) {
                        listRoom.clear();
                        listRoom.addAll(dao_room.selectAll());
                        adapter_room.notifyDataSetChanged();
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
        if (inputRoomNumberADDROOM.getEditText().getText().toString().trim().isEmpty()) {
            if (inputRoomNumberADDROOM.getEditText().getText().toString().trim().isEmpty()) {
                inputRoomNumberADDROOM.setError("Số phòng không được để trống");
            } else {
                inputRoomNumberADDROOM.setError("");
            }
            return false;
        } else {
            return true;
        }
    }
}

