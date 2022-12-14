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
import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Contract;
import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Tenant;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Contract;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Tenant;
import vn.edu.spx.group7_apartmentmanagement_final.R;

public class ContractFragment extends Fragment {
    int cyearcon, cmonthcon, cdaycon;
    private RecyclerView rcv_list_contract;
    private FloatingActionButton fab_add_contract;
    DAO_Tenant dao_tenant;
    AdapterSpinnerTenant adapterSpinnerTenant;
    ArrayList<Tenant> listTenant;
    ArrayList<Contract> listContract;
    DAO_Contract dao_contract;
    Adapter_Contract adapter_contract;
    Context context;
    String TAG = "zzzzzzzzzzzz";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contract, container, false);
        context = getContext();
        dao_contract = new DAO_Contract(context);
        dao_tenant = new DAO_Tenant(context);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv_list_contract = view.findViewById(R.id.rcv_list_contract);
        dao_contract.opend();
        dao_tenant.opend();
        listContract = dao_contract.selectAll();
        listTenant = dao_tenant.selectAll();
        adapterSpinnerTenant = new AdapterSpinnerTenant(listTenant);
        adapter_contract = new Adapter_Contract(dao_tenant, adapterSpinnerTenant, listTenant, listContract, dao_contract);
        rcv_list_contract.setAdapter(adapter_contract);
        fab_add_contract = view.findViewById(R.id.fab_add_contract);
        Log.e(TAG, "dialogAddTenant: " + listTenant.size());
        fab_add_contract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddContract();
                listContract = dao_contract.selectAll();
                listTenant = dao_tenant.selectAll();
                adapter_contract = new Adapter_Contract(dao_tenant, adapterSpinnerTenant, listTenant, listContract, dao_contract);
                rcv_list_contract.setAdapter(adapter_contract);
            }
        });
    }

    private Spinner spinnerTenant;
    private TextInputLayout inputRoomNumberADD;
    private MaterialTextView tv_select_start_renttime;
    private ImageView img_select_start_renttime;
    private MaterialTextView tv_select_end_renttime;
    private ImageView img_select_end_renttime;
    private TextInputLayout inputRoomPriceADD;
    private TextInputLayout inputWaterBillADD;
    private TextInputLayout inputElectricBillADD;
    private TextInputLayout inputServiceBillADD;
    private MaterialButton btnADDContract;

    public void dialogAddContract() {
        final Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_add_contract);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        spinnerTenant = dialog.findViewById(R.id.spinner_tenant_nameADD);
        inputRoomNumberADD = dialog.findViewById(R.id.input_room_numberADD);
        tv_select_start_renttime = dialog.findViewById(R.id.tv_select_start_renttime);
        tv_select_end_renttime = dialog.findViewById(R.id.tv_select_end_renttime);
        inputRoomPriceADD = dialog.findViewById(R.id.input_roompriceADD);
        inputWaterBillADD = dialog.findViewById(R.id.input_waterbill_ADD);
        inputElectricBillADD = dialog.findViewById(R.id.input_electricbill_ADD);
        inputServiceBillADD = dialog.findViewById(R.id.input_roomserviceADD);
        btnADDContract = dialog.findViewById(R.id.btn_ADDContract);
        spinnerTenant.setAdapter(adapterSpinnerTenant);
        img_select_start_renttime = dialog.findViewById(R.id.img_select_start_renttime);
        img_select_end_renttime = dialog.findViewById(R.id.img_select_end_renttime);
        img_select_start_renttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                cyearcon = calendar.get(Calendar.YEAR);
                cmonthcon = calendar.get(Calendar.MONTH);
                cdaycon = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tv_select_start_renttime.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },cyearcon, cmonthcon, cdaycon);
                datePickerDialog.getDatePicker().setMinDate(-1576800000000L);
                datePickerDialog.show();
            }
        });
        img_select_end_renttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                cyearcon = calendar.get(Calendar.YEAR);
                cmonthcon = calendar.get(Calendar.MONTH);
                cdaycon = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tv_select_end_renttime.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },cyearcon, cmonthcon, cdaycon);
                datePickerDialog.getDatePicker().setMinDate(-1576800000000L);
                datePickerDialog.show();
            }
        });
        btnADDContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check() == true) {
                    Contract contract = new Contract();
                    Tenant tenant = (Tenant) spinnerTenant.getSelectedItem();
                    contract.setRoomNumber(inputRoomNumberADD.getEditText().getText().toString().trim());
                    contract.setStartTime(tv_select_start_renttime.getText().toString().trim());
                    contract.setEndTime(tv_select_end_renttime.getText().toString().trim());
                    contract.setRoomPrice(Integer.parseInt(inputRoomPriceADD.getEditText().getText().toString().trim()));
                    contract.setWaterBill(Integer.parseInt(inputWaterBillADD.getEditText().getText().toString().trim()));
                    contract.setElectricBill(Integer.parseInt(inputElectricBillADD.getEditText().getText().toString().trim()));
                    contract.setServiceBill(Integer.parseInt(inputServiceBillADD.getEditText().getText().toString().trim()));
                    contract.setIdTenant(tenant.getIdTenant());
                    Log.e(TAG, "onClick: " + tenant.getIdTenant());
                    contract.setTenantName(tenant.getTenantName());
                    Log.e(TAG, "onClick: " + tenant.getTenantName());
                    if (dao_contract.insertContract(contract) > 0) {
                        listContract.clear();
                        listContract.addAll(dao_contract.selectAll());
                        adapter_contract.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Th??m m???i th??nh c??ng", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Th??m m???i th???t b???i", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        dialog.show();
    }

    public boolean check() {
        if (inputRoomNumberADD.getEditText().getText().toString().trim().isEmpty() ||
                tv_select_start_renttime.getText().toString().trim().isEmpty() ||
                tv_select_end_renttime.getText().toString().trim().isEmpty() ||
                inputRoomPriceADD.getEditText().getText().toString().trim().isEmpty() ||
                inputWaterBillADD.getEditText().getText().toString().trim().isEmpty()||
                inputElectricBillADD.getEditText().getText().toString().trim().isEmpty()||
                inputServiceBillADD.getEditText().getText().toString().trim().isEmpty()) {
            if (inputRoomNumberADD.getEditText().getText().toString().trim().isEmpty()) {
                inputRoomNumberADD.setError("S??? ph??ng kh??ng ???????c ????? tr???ng");
            } else {
                inputRoomNumberADD.setError("");
            }
            if (tv_select_start_renttime.getText().toString().trim().isEmpty()) {
                tv_select_start_renttime.setError("Th???i gian thu?? kh??ng ???????c ????? tr???ng");
            } else {
                tv_select_start_renttime.setError("");
            }if (tv_select_end_renttime.getText().toString().trim().isEmpty()) {
                tv_select_end_renttime.setError("Th???i gian thu?? kh??ng ???????c ????? tr???ng");
            } else {
                tv_select_end_renttime.setError("");
            }
            if (inputRoomPriceADD.getEditText().getText().toString().trim().isEmpty()) {
                inputRoomPriceADD.setError("Ti???n ph??ng kh??ng ???????c ????? tr???ng");
            } else {
                inputRoomPriceADD.setError("");
            }
            if (inputWaterBillADD.getEditText().getText().toString().trim().isEmpty()) {
                inputWaterBillADD.setError("Ti???n n?????c kh??ng ???????c ????? tr???ng");
            } else {
                inputWaterBillADD.setError("");
            }if (inputElectricBillADD.getEditText().getText().toString().trim().isEmpty()) {
                inputElectricBillADD.setError("Ti???n ??i???n kh??ng ???????c ????? tr???ng");
            } else {
                inputElectricBillADD.setError("");
            }if (inputServiceBillADD.getEditText().getText().toString().trim().isEmpty()) {
                inputServiceBillADD.setError("Ti???n d???ch v??? kh??ng ???????c ????? tr???ng");
            } else {
                inputServiceBillADD.setError("");
            }
            return false;
        } else {
            return true;
        }
    }
}
