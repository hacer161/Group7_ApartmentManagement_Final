package vn.edu.spx.group7_apartmentmanagement_final.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.edu.spx.group7_apartmentmanagement_final.R;

public class ViewHolder_Tenant extends RecyclerView.ViewHolder {
    public TextView tv_tenant_name;
    public TextView tv_tenant_identity;
    public TextView tv_tenant_DOB;
    public ImageView imgUpdateTenant;
    public ImageView imgDeleteTenant;
    public ViewHolder_Tenant(@NonNull View itemView) {
        super(itemView);
        tv_tenant_name = itemView.findViewById(R.id.tv_tenant_name);
        tv_tenant_identity = itemView.findViewById(R.id.tv_identity_tenant);
        tv_tenant_DOB = itemView.findViewById(R.id.tv_tenant_dob);
        imgUpdateTenant = itemView.findViewById(R.id.img_edit_tenant);
        imgDeleteTenant = itemView.findViewById(R.id.img_delete_tenant);
    }
}
