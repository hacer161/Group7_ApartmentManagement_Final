package vn.edu.spx.group7_apartmentmanagement_final;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import vn.edu.spx.group7_apartmentmanagement_final.DAO.DAO_Login;
import vn.edu.spx.group7_apartmentmanagement_final.Model.Login;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout inputUser;
    private TextInputLayout inputPass;
    private MaterialCheckBox chkRePass;
    private TextView tvUpdatePass;
    private MaterialButton btnDn;
    private TextView tvCreateAccount;
    DAO_Login dao_login;
    ArrayList<Login> list_ad;
    Bundle bundle;
    SharedPreferences sharedPreferences;
    ActivityResultLauncher activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            save();
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dao_login=new DAO_Login(LoginActivity.this);
        dao_login.opend();
        list_ad=dao_login.getAll();
        sharedPreferences=getSharedPreferences("Login",Context.MODE_PRIVATE);
        inputUser = findViewById(R.id.input_user);
        inputPass = findViewById(R.id.input_pass);
        chkRePass = findViewById(R.id.chk_rePass);
        tvUpdatePass = findViewById(R.id.tv_update_pass);
        btnDn = findViewById(R.id.btn_dn);
        tvCreateAccount = findViewById(R.id.tv_create_account);
        bundle=new Bundle();
        btnDn.setOnClickListener(view->{
            if(checkLogin()==true){
                if(login()==true){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    inputPass.setError("");
                    save();
                    intent.putExtra(DAO_Login.TB_NAME,bundle);
                    inputPass.getEditText().setText("");
                    startActivity(intent);
                }else{
                    inputPass.setError("T??n t??i kho???n ho???c m???t kh???u kh??ng ????ng");
                }
            }
        });
        tvCreateAccount.setOnClickListener(view->{
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
        tvUpdatePass.setOnClickListener(view->{
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });
}

    @Override
    protected void onResume() {
        super.onResume();
        list_ad = dao_login.getAll();
        read();
    }

    public boolean login(){
        boolean a=false;
        for(int i=0;i< list_ad.size();i++){
            if( inputUser.getEditText().getText().toString().equals(list_ad.get(i).getIdAdmin()) && inputPass.getEditText().getText().toString().equals(list_ad.get(i).getPassword())){
                a=true;
                bundle.putString(DAO_Login.IDAMIN,list_ad.get(i).getIdAdmin());
                bundle.putString(DAO_Login.FULLNAME,list_ad.get(i).getFullName());
                bundle.putString(DAO_Login.PHONE,list_ad.get(i).getPhone());
                save();
                break;
            }else{
                a= false;
            }
        }
        return a;
    }
    public boolean checkLogin(){
        if(inputUser.getEditText().getText().toString().isEmpty()||inputPass.getEditText().getText().toString().isEmpty()){
            if(inputUser.getEditText().getText().toString().isEmpty()){
                inputUser.setError("T??n t??i kho???n kh???n ???????c ????? tr???ng");
            }else{
                inputUser.setError("");
            }
            if(inputPass.getEditText().getText().toString().isEmpty()){
                inputPass.setError("M???t kh???u kh??ng ???????c ????? tr???ng");
            }else{
                inputPass.setError("");
            }
            return false;
        }else{
            return true;
        }
    }
    public void save() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putString("user", inputUser.getEditText().getText().toString());
        editor.putString("pass", inputPass.getEditText().getText().toString());
        editor.putBoolean("check", chkRePass.isChecked());
        editor.commit();
    }

    public void read() {
        String user = sharedPreferences.getString("user", "");
        String pass = sharedPreferences.getString("pass", "");
        boolean a = sharedPreferences.getBoolean("check", false);
        inputUser.getEditText().setText(user);
        if (a == true) {
            inputPass.getEditText().setText(pass);
        }
    }
}