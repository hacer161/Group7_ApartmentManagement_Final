package vn.edu.spx.group7_apartmentmanagement_final.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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

import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Contract;
import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Tenant;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Contract;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Tenant;
import vn.edu.spx.group7_apartmentmanagement_final.R;
import vn.edu.spx.group7_apartmentmanagement_final.ViewHolder.ViewHolder_Contract;

public class Adapter_Contract extends RecyclerView.Adapter<ViewHolder_Contract> {
    DAO_Tenant dao_tenant;
    AdapterSpinnerTenant adapterSpinnerTenant;
    ArrayList<Tenant> listTenant;
    ArrayList<Contract> listContract;
    DAO_Contract dao_contract;
    Context context;

    public Adapter_Contract(DAO_Tenant dao_tenant, AdapterSpinnerTenant adapterSpinnerTenant, ArrayList<Tenant> listTenant, ArrayList<Contract> listContract, DAO_Contract dao_contract) {
        this.dao_tenant = dao_tenant;
        this.adapterSpinnerTenant = adapterSpinnerTenant;
        this.listTenant = listTenant;
        this.listContract = listContract;
        this.dao_contract = dao_contract;
    }

    @NonNull
    @Override
    public ViewHolder_Contract onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View row = layoutInflater.inflate(R.layout.item_contract, parent, false);
        ViewHolder_Contract viewHolderContract = new ViewHolder_Contract(row);
        context = parent.getContext();
        dao_contract = new DAO_Contract(context);
        dao_tenant = new DAO_Tenant(context);
        dao_contract.opend();
        dao_tenant.opend();
        return viewHolderContract;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_Contract holder, int position) {
        final int index = position;
        listContract = dao_contract.selectAll();
        listTenant = dao_tenant.selectAll();
        Contract contract = listContract.get(index);
        holder.tv_room_number_contract.setText(contract.getRoomNumber());
        holder.tv_roomprice_contract.setText("Giá thuê: " + contract.getRoomPrice());
        holder.imgUpdateContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogUpdate(contract, index);
            }
        });
        holder.imgDeleteContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDelete(contract, index);
            }
        });
        holder.tv_details_contract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogcontractdetails(contract);
            }
        });
    }


    private Spinner spinner_tenant_nameUP;
    private TextInputLayout inputRoomNumberUp;
    private TextInputLayout inputRentTimeUp;
    private TextInputLayout inputRoomPriceUp;
    private TextInputLayout inputWaterBillUp;
    private TextInputLayout inputElectricBillUp;
    private TextInputLayout inputServiceBillUp;
    private MaterialButton btnUpContract;

    public void dialogUpdate(Contract contract, int index) {
        Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_update_contract);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        spinner_tenant_nameUP = dialog.findViewById(R.id.spinner_tenant_nameUP);
        inputRoomNumberUp = dialog.findViewById(R.id.input_room_numberUP);
        inputRentTimeUp = dialog.findViewById(R.id.input_timeUP);
        inputRoomPriceUp = dialog.findViewById(R.id.input_roompriceUP);
        inputWaterBillUp = dialog.findViewById(R.id.input_waterbill_UP);
        inputElectricBillUp = dialog.findViewById(R.id.input_electricbill_UP);
        inputServiceBillUp = dialog.findViewById(R.id.input_roomserviceUP);
        btnUpContract = dialog.findViewById(R.id.btn_UpContract);

        adapterSpinnerTenant = new AdapterSpinnerTenant(listTenant);
        spinner_tenant_nameUP.setAdapter(adapterSpinnerTenant);
        try {
            for (int i = 0; i < listContract.size(); i++) {
                if (contract.getIdTenant() == listTenant.get(i).getIdTenant()) {
                    spinner_tenant_nameUP.setSelection(i);
                    spinner_tenant_nameUP.setSelected(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        inputRoomNumberUp.getEditText().setText(contract.getRoomNumber());
        inputRentTimeUp.getEditText().setText(contract.getTime());
        inputRoomPriceUp.getEditText().setText(contract.getRoomPrice() + "");
        inputWaterBillUp.getEditText().setText(contract.getWaterBill()+"");
        inputElectricBillUp.getEditText().setText(contract.getElectricBill()+"");
        inputServiceBillUp.getEditText().setText(contract.getServiceBill()+"");
        btnUpContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkErrorUp() == true) {
                    Tenant tenant = (Tenant) spinner_tenant_nameUP.getSelectedItem();
                    contract.setTenantName(tenant.getTenantName());
                    contract.setIdTenant(tenant.getIdTenant());
                    contract.setRoomNumber(inputRoomNumberUp.getEditText().getText().toString().trim());
                    contract.setTime(inputRentTimeUp.getEditText().getText().toString().trim());
                    contract.setRoomPrice(Integer.parseInt(inputRoomPriceUp.getEditText().getText().toString().trim()));
                    contract.setWaterBill(Integer.parseInt(inputWaterBillUp.getEditText().getText().toString().trim()));
                    contract.setElectricBill(Integer.parseInt(inputElectricBillUp.getEditText().getText().toString().trim()));
                    contract.setServiceBill(Integer.parseInt(inputServiceBillUp.getEditText().getText().toString().trim()));
                    if (dao_contract.updateContract(contract) > 0) {
                        listContract.set(index, contract);
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

    public void dialogDelete(Contract contract, int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("delete");
        builder.setMessage("Bạn có muốn xóa hợp đồng phòng: " + contract.getRoomNumber());
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int res = dao_contract.deleteContract(contract);
                if (res > 0) {
                    listContract.remove(index);
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
        if (inputRoomNumberUp.getEditText().getText().toString().trim().isEmpty() ||
                inputRentTimeUp.getEditText().getText().toString().trim().isEmpty() ||
                inputRoomPriceUp.getEditText().getText().toString().trim().isEmpty() ||
                inputWaterBillUp.getEditText().getText().toString().trim().isEmpty() ||
                inputElectricBillUp.getEditText().getText().toString().trim().isEmpty() ||
                inputServiceBillUp.getEditText().getText().toString().trim().isEmpty()) {
            if (inputRoomNumberUp.getEditText().getText().toString().trim().isEmpty()) {
                inputRoomNumberUp.setError("Số phòng không được để trống");
            } else {
                inputRoomNumberUp.setError("");
            }
            if (inputRentTimeUp.getEditText().getText().toString().trim().isEmpty()) {
                inputRentTimeUp.setError("Thời gian thuê không được để trống");
            } else {
                inputRentTimeUp.setError("");
            }
            if (inputRoomPriceUp.getEditText().getText().toString().trim().isEmpty()) {
                inputRoomPriceUp.setError("Tiền phòng không được để trống");
            } else {
                inputRoomPriceUp.setError("");
            }
            if (inputWaterBillUp.getEditText().getText().toString().trim().isEmpty()) {
                inputWaterBillUp.setError("Tiền nước không được để trống");
            } else {
                inputWaterBillUp.setError("");
            }if (inputElectricBillUp.getEditText().getText().toString().trim().isEmpty()) {
                inputElectricBillUp.setError("Tiền điện không được để trống");
            } else {
                inputElectricBillUp.setError("");
            }if (inputServiceBillUp.getEditText().getText().toString().trim().isEmpty()) {
                inputServiceBillUp.setError("Tiền dịch vụ không được để trống");
            } else {
                inputServiceBillUp.setError("");
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int getItemCount() {
        return listContract == null ? 0 : listContract.size();
    }

    private TextView tv_id_contract;
    private TextView tv_room_number_details;
    private TextView tv_tenant_name_details;
    private TextView tv_rent_time_details;
    private TextView tv_room_price_details;
    private TextView tv_waterbill_details;
    private TextView tv_electricbill_details;
    private TextView tv_servicebill_details;


    public void dialogcontractdetails(Contract obj) {
        Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.layout_detail_contract);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        tv_id_contract = dialog.findViewById(R.id.tv_id_contract);
        tv_room_number_details = dialog.findViewById(R.id.tv_room_number_details);
        tv_tenant_name_details = dialog.findViewById(R.id.tv_tenant_details);
        tv_rent_time_details=dialog.findViewById(R.id.tv_rent_time);
        tv_room_price_details = dialog.findViewById(R.id.tv_roomprice_details);
        tv_waterbill_details = dialog.findViewById(R.id.tv_waterbill);
        tv_electricbill_details = dialog.findViewById(R.id.tv_electricbill);
        tv_servicebill_details = dialog.findViewById(R.id.tv_servicebill);
        tv_id_contract.setText("Số hợp đồng: " + obj.getIdContract());
        tv_room_number_details.setText("Số phòng: " + obj.getRoomNumber());
        tv_tenant_name_details.setText("Người Thuê: " + obj.getIdTenant() + "|" + obj.getTenantName());
        tv_rent_time_details.setText("Thời gian thuê: " + obj.getTime());
        tv_room_price_details.setText("Tiền phòng 1 tháng: " + obj.getRoomPrice());
        tv_waterbill_details.setText("Tiền nước 1 khối: " + obj.getWaterBill());
        tv_electricbill_details.setText("Tiền điện 1 kWH: " + obj.getElectricBill());
        tv_servicebill_details.setText("Tiền dịch vụ hàng tháng: " + obj.getServiceBill());
        dialog.show();
    }

}

