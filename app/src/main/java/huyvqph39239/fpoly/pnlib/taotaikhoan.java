package huyvqph39239.fpoly.pnlib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import huyvqph39239.fpoly.pnlib.DAO.adminDao;

public class taotaikhoan extends AppCompatActivity {
private adminDao adminDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taotaikhoan);
        EditText edtuser = findViewById(R.id.edtusername);
        EditText edtnhaplai = findViewById(R.id.edtnhaplai);
        EditText edtpass = findViewById((R.id.edtpass));
        Button btnregister = findViewById(R.id.btnregister);
        Button btngoback = findViewById(R.id.btngoback);
        adminDao = new adminDao(this);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user= edtuser.getText().toString();
                String pass = edtpass.getText().toString();
                String nhaplai = edtnhaplai.getText().toString();
                if (!pass.equals(nhaplai)){
                    Toast.makeText(taotaikhoan.this,
                            "2 mat khau khong duoc trung nhau. Kiem tra lai", Toast.LENGTH_SHORT).show();
                }else {
                    boolean check = adminDao.Register(user,pass);
                    if (check){
                        Toast.makeText(taotaikhoan.this, "Dang ky thanh cong",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(taotaikhoan.this, "Dang ky that bai", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }
}