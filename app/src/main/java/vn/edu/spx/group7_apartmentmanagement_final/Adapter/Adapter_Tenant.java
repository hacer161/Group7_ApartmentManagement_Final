package vn.edu.spx.group7_apartmentmanagement_final.Adapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.Calendar;

import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Tenant;
import vn.edu.spx.group7_apartmentmanagement_final.Fragment.TenantFragment;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Tenant;
import vn.edu.spx.group7_apartmentmanagement_final.R;
import vn.edu.spx.group7_apartmentmanagement_final.ViewHolder.ViewHolder_Tenant;

public class Adapter_Tenant extends RecyclerView.Adapter<ViewHolder_Tenant> {
    DAO_Tenant dao_tenant;
    ArrayList<Tenant> listtenant;
    Context context;
    int cyear,cmonth,cday;
    TenantFragment tenantFragment;

    public Adapter_Tenant(DAO_Tenant dao_tenant, ArrayList<Tenant> listtenant) {
        this.dao_tenant = dao_tenant;
        this.listtenant = listtenant;
    }

    @NonNull
    @Override
    public ViewHolder_Tenant onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View row = layoutInflater.inflate(R.layout.item_tenant, parent, false);
        ViewHolder_Tenant viewHolder_tenant = new ViewHolder_Tenant(row);
        context = parent.getContext();
        dao_tenant = new DAO_Tenant(parent.getContext());
        return viewHolder_tenant;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_Tenant holder, int position) {
        dao_tenant.opend();
        listtenant = dao_tenant.selectAll();
        final int index = position;
        Tenant tenant = listtenant.get(index);
        holder.tv_tenant_name.setText(tenant.getTenantName());
        holder.tv_tenant_identity.setText(tenant.getIdentity());
        holder.tv_tenant_DOB.setText(tenant.getDOB());
        holder.imgUpdateTenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTenant(tenant, index);
            }
        });
        holder.imgDeleteTenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTenant(tenant, index);
            }
        });
    }
    public void insertCategory(Context context){
        Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_add_tenant);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        TextInputLayout input_tenant_name = dialog.findViewById(R.id.input_tenant_name_Add);
        TextInputLayout input_tenant_identity = dialog.findViewById(R.id.input_tenant_identity_Add);
        ImageView img_select_dob = dialog.findViewById(R.id.img_select_dob);
        MaterialTextView tv_select_dob = dialog.findViewById(R.id.tv_select_dob);
        MaterialButton btn_add_tenant = dialog.findViewById(R.id.btn_addTenant);
        img_select_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                cyear = calendar.get(Calendar.YEAR);
                cmonth = calendar.get(Calendar.MONTH);
                cday = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tv_select_dob.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },cyear, cmonth, cday);
                datePickerDialog.getDatePicker().setMinDate(-1576800000000L);
                datePickerDialog.show();
            }
        });
        btn_add_tenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(input_tenant_name.getEditText().getText().toString().trim().isEmpty() ||
                        input_tenant_identity.getEditText().getText().toString().trim().isEmpty() ||
                tv_select_dob.getText().toString().trim().isEmpty())
                    {
                    input_tenant_name.setError("Không được để trống tên người thuê");
                    input_tenant_identity.setError("Không được để trống số CMND");
                    tv_select_dob.setError("Không được để trống ngày sinh");
                }else{
                    Tenant tenant = new Tenant();
                    tenant.setTenantName(input_tenant_name.getEditText().getText().toString().trim());
                    tenant.setIdentity(input_tenant_identity.getEditText().getText().toString().trim());
                    tenant.setDOB(tv_select_dob.getText().toString().trim());
                    if(dao_tenant.insertTenant(tenant)>0){
                        listtenant.clear();
                        listtenant.addAll(dao_tenant.selectAll());
                        notifyDataSetChanged();
                        Toast.makeText(context, "Thêm mới thành công" , Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(context, "Thêm mới thành công" , Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        dialog.show();

    }
    private void pickdatetenant() {

    }

    public void updateTenant(Tenant tenant, int index) {
        Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.update_tenant);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);
        TextInputLayout input_tenant_name_Up = dialog.findViewById(R.id.input_tenant_name_Update);
        TextInputLayout input_tenant_identity_Up = dialog.findViewById(R.id.input_tenant_identity_Update);
        MaterialTextView tv_select_dobUPDATE = dialog.findViewById(R.id.tv_select_dobUPDATE);
        ImageView img_select_dobUPDATE = dialog.findViewById(R.id.img_select_dobUPDATE);
        img_select_dobUPDATE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                cyear = calendar.get(Calendar.YEAR);
                cmonth = calendar.get(Calendar.MONTH);
                cday = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tv_select_dobUPDATE.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },cyear, cmonth, cday);
                datePickerDialog.getDatePicker().setMinDate(-1576800000000L);
                datePickerDialog.show();

            }
        });
        MaterialButton btn_updateCategory = dialog.findViewById(R.id.btn_Update_Tenant);
        input_tenant_name_Up.getEditText().setText(tenant.getTenantName());
        btn_updateCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_tenant_name_Up.getEditText().getText().toString().trim().isEmpty() ||
                        input_tenant_identity_Up.getEditText().getText().toString().trim().isEmpty() ||
                        tv_select_dobUPDATE.getText().toString().trim().isEmpty()) {
                    input_tenant_name_Up.setError("Không được để trống tên người thuê");
                    input_tenant_identity_Up.setError("Không được để trống số CMND");
                    tv_select_dobUPDATE.setError("Không được để trống ngày sinh");
                } else {
                    input_tenant_name_Up.setError("");
                    tenant.setTenantName(input_tenant_name_Up.getEditText().getText().toString().trim());
                    input_tenant_identity_Up.setError("");
                    tenant.setIdentity(input_tenant_identity_Up.getEditText().getText().toString().trim());
                    tv_select_dobUPDATE.setError("");
                    tenant.setDOB(tv_select_dobUPDATE.getText().toString().trim());
                    if (dao_tenant.updateTenant(tenant) > 0) {
                        listtenant.set(index, tenant);
                        notifyItemChanged(index);
                        Toast.makeText(context, "Cập hật thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        dialog.show();
    }

    public void deleteTenant(Tenant tenant, int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("XÓA NGƯỜI THUÊ");
        builder.setMessage("Bạn có muốn xóa người thuê: " + tenant.getTenantName());
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dao_tenant.deleteTenant(tenant)> 0) {
                    listtenant.remove(index);
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

    @Override
    public int getItemCount() {
        return listtenant == null ? 0 : listtenant.size();
    }
}
