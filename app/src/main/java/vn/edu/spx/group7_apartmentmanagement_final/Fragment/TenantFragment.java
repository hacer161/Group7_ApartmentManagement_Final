package vn.edu.spx.group7_apartmentmanagement_final.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import vn.edu.spx.group7_apartmentmanagement_final.Adapter.Adapter_Tenant;
import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Tenant;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Tenant;
import vn.edu.spx.group7_apartmentmanagement_final.R;

public class TenantFragment extends Fragment {
    private RecyclerView rcvlisttenant;
    private FloatingActionButton fabaddtenant;
    Context context;
    DAO_Tenant dao_tenant;
    Adapter_Tenant adapter_tenant;
    ArrayList<Tenant> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tenant,container,false);
        context= getContext();
        dao_tenant=new DAO_Tenant(context);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvlisttenant = view.findViewById(R.id.rcv_list_tenant);
        fabaddtenant = view.findViewById(R.id.fab_add_tenant);
        dao_tenant.opend();
        list = dao_tenant.selectAll();
        adapter_tenant = new Adapter_Tenant(dao_tenant, list);
        rcvlisttenant.setAdapter(adapter_tenant);
        fabaddtenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter_tenant.insertCategory(getContext());
            }
        });

    }
}