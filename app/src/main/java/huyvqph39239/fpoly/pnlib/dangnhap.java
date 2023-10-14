package huyvqph39239.fpoly.pnlib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import huyvqph39239.fpoly.pnlib.DAO.ThuThuDAO;

public class dangnhap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        EditText edtuser = findViewById(R.id.edtusername);
        EditText edtpass = findViewById(R.id.edtpass);
        Button btndangnhap = findViewById((R.id.btnlogin));
        Button btnTao = findViewById(R.id.btntaotaikhoan);
        ThuThuDAO thuThuDAO = new ThuThuDAO(this);
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtuser.getText().toString();
                String pass = edtpass.getText().toString();
                if (thuThuDAO.checkDangNhap(user,pass)){
                    // luu tai khoan dang nhap
                    SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("matt",user);
                    editor.commit();

                    startActivity(new Intent(dangnhap.this,MainActivity.class));
                }else {
                    Toast.makeText(dangnhap.this, "User name hoac mat khau khong dung", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnTao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dangnhap.this, taotaikhoan.class);
                startActivity(intent);
            }
        });
    }
}