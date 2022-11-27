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

import vn.edu.spx.group7_apartmentmanagement_final.Adapter.AdapterSpinnerTenant;
import vn.edu.spx.group7_apartmentmanagement_final.Adapter.Adapter_Contract;
import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Contract;
import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Tenant;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Contract;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Tenant;
import vn.edu.spx.group7_apartmentmanagement_final.R;

public class ContractFragment extends Fragment {
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
    private TextInputLayout inputRentTimeADD;
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
        inputRentTimeADD = dialog.findViewById(R.id.input_timeADD);
        inputRoomPriceADD = dialog.findViewById(R.id.input_roompriceADD);
        inputWaterBillADD = dialog.findViewById(R.id.input_waterbill_ADD);
        inputElectricBillADD = dialog.findViewById(R.id.input_electricbill_ADD);
        inputServiceBillADD = dialog.findViewById(R.id.input_roomserviceADD);
        btnADDContract = dialog.findViewById(R.id.btn_ADDContract);
        spinnerTenant.setAdapter(adapterSpinnerTenant);
        btnADDContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check() == true) {
                    Contract contract = new Contract();
                    Tenant tenant = (Tenant) spinnerTenant.getSelectedItem();
                    contract.setRoomNumber(inputRoomNumberADD.getEditText().getText().toString().trim());
                    contract.setTime(inputRentTimeADD.getEditText().getText().toString().trim());
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
        if (inputRoomNumberADD.getEditText().getText().toString().trim().isEmpty() ||
                inputRentTimeADD.getEditText().getText().toString().trim().isEmpty() ||
                inputRoomPriceADD.getEditText().getText().toString().trim().isEmpty() ||
                inputWaterBillADD.getEditText().getText().toString().trim().isEmpty()||
                inputElectricBillADD.getEditText().getText().toString().trim().isEmpty()||
                inputServiceBillADD.getEditText().getText().toString().trim().isEmpty()) {
            if (inputRoomNumberADD.getEditText().getText().toString().trim().isEmpty()) {
                inputRoomNumberADD.setError("Số phòng không được để trống");
            } else {
                inputRoomNumberADD.setError("");
            }
            if (inputRentTimeADD.getEditText().getText().toString().trim().isEmpty()) {
                inputRentTimeADD.setError("Thời gian thuê không được để trống");
            } else {
                inputRentTimeADD.setError("");
            }
            if (inputRoomPriceADD.getEditText().getText().toString().trim().isEmpty()) {
                inputRoomPriceADD.setError("Tiền phòng không được để trống");
            } else {
                inputRoomPriceADD.setError("");
            }
            if (inputWaterBillADD.getEditText().getText().toString().trim().isEmpty()) {
                inputWaterBillADD.setError("Tiền nước không được để trống");
            } else {
                inputWaterBillADD.setError("");
            }if (inputElectricBillADD.getEditText().getText().toString().trim().isEmpty()) {
                inputElectricBillADD.setError("Tiền điện không được để trống");
            } else {
                inputElectricBillADD.setError("");
            }if (inputServiceBillADD.getEditText().getText().toString().trim().isEmpty()) {
                inputServiceBillADD.setError("Tiền dịch vụ không được để trống");
            } else {
                inputServiceBillADD.setError("");
            }
            return false;
        } else {
            return true;
        }
    }
}
