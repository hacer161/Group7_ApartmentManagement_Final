package vn.edu.spx.group7_apartmentmanagement_final.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.spx.group7_apartmentmanagement_final.Model.Tenant;
import vn.edu.spx.group7_apartmentmanagement_final.R;

public class AdapterSpinnerTenant extends BaseAdapter {
    ArrayList<Tenant> list;

    public AdapterSpinnerTenant(ArrayList<Tenant> list) {
        this.list = list;
    }


    @Override
    public int getCount() {
        return list==null?0: list.size();
    }

    @Override
    public Object getItem(int position) {
        Tenant tenant = list.get(position);
        return tenant;
    }

    @Override
    public long getItemId(int position) {
        Tenant tenant = list.get(position);
        return tenant.getIdTenant();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        if(convertView==null){
            row =View.inflate(parent.getContext(), R.layout.item_spinner_tenant, null);
        }else{
            row = convertView;
        }
        Tenant tenant = list.get(position);
        TextView tv_id_tenant = row.findViewById(R.id.tv_id_tenant);
        TextView tv_tenant_name_spinner = row.findViewById(R.id.tv_name_tenant_spinner);
        tv_id_tenant.setText(tenant.getIdTenant()+"|");
        tv_tenant_name_spinner.setText(tenant.getTenantName());
        return row;
    }
}
