package vn.edu.spx.group7_apartmentmanagement_final;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Login;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Login;

public class changePasswordActivity  extends AppCompatActivity {
    private LinearLayout layoutForGotPass;
    private ImageView imgBackAc;
    private TextInputLayout inputUserUp;
    private TextInputLayout inputPassUp;
    private TextInputLayout inputCofPassUp;
    private MaterialButton btnUpPass;
    DAO_Login dao_admin;
    ArrayList<Login> list_ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        dao_admin= new DAO_Login(getApplicationContext());
        dao_admin.opend();
        list_ad = dao_admin.getAll();
        layoutForGotPass = findViewById(R.id.layout_forGotPass);
        imgBackAc = findViewById(R.id.img_backAc);
        inputUserUp = findViewById(R.id.input_userUp);
        inputPassUp = findViewById(R.id.input_passUp);
        inputCofPassUp = findViewById(R.id.input_cofPassUp);
        btnUpPass = findViewById(R.id.btn_upPass);
        imgBackAc.setOnClickListener(view->{finish();});
        btnUpPass.setOnClickListener(view->{
            if(checkloi()==true){
                if(changePass()==true){
                    Toast.makeText(getApplicationContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Tên tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public boolean changePass(){
        boolean a = false;
        for(int i=0;i<list_ad.size();i++){
            if(inputUserUp.getEditText().getText().toString().equals(list_ad.get(i).getIdAdmin())){
                Login admin = list_ad.get(i);
                admin.setPassword(inputPassUp.getEditText().getText().toString());
                int res = dao_admin.changePass(admin);
                if(res>0){
//                    list_user.set(i,nd);
                    a = true;
                }else{
                    a = false;
                }

            }
        }
        return a;
    }
    public boolean checkloi(){
        if(inputPassUp.getEditText().getText().toString().isEmpty()||inputCofPassUp.getEditText().getText().toString().isEmpty()||inputUserUp.getEditText().getText().toString().isEmpty()){
            if(inputUserUp.getEditText().getText().toString().isEmpty()){
                inputUserUp.setError("Không được để trống trường này");
            }else {
                inputUserUp.setError("");
            }
            if(inputPassUp.getEditText().getText().toString().isEmpty()){
                inputPassUp.setError("Không được để trống trường này");
            }else{
                inputPassUp.setError("");
            }
            if(inputCofPassUp.getEditText().getText().toString().isEmpty()){
                inputCofPassUp.setError("Không được để trống trường này");
            }else{
                inputCofPassUp.setError("");
            }
            return false;
        }else{
            return true;
        }
    }
}