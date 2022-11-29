package vn.edu.spx.group7_apartmentmanagement_final.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.spx.group7_apartmentmanagement_final.Model.Room;
import vn.edu.spx.group7_apartmentmanagement_final.R;

public class AdapterSpinnerServicebill extends BaseAdapter {
    ArrayList<Room> list;
    public AdapterSpinnerServicebill(ArrayList<Room> list){
        this.list=list;
    }
    @Override
    public int getCount() {
        return list==null?0: list.size();
    }

    @Override
    public Object getItem(int position) {
        Room room=list.get(position);
        return room;
    }

    @Override
    public long getItemId(int position) {
        Room room=list.get(position);
        return room.getIdRoom();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        if(convertView==null){
            row =View.inflate(parent.getContext(), R.layout.item_spinner_room, null);
        }else{
            row=convertView;
        }
        Room room=list.get(position);
        TextView id_servicebill=row.findViewById(R.id.tv_id_roomprice);
        TextView tv_servicebill_spinner = row.findViewById(R.id.tv_servicebill_spinner );
        id_servicebill.setText(room.getIdRoom()+""+"|");
        tv_servicebill_spinner.setText(room.getServiceBill1M()+"");
        return row;
    }
}
