package huyvqph39239.fpoly.pnlib;

import static huyvqph39239.fpoly.pnlib.R.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import huyvqph39239.fpoly.pnlib.DAO.ThuThuDAO;
import huyvqph39239.fpoly.pnlib.Fragment.ThongKeTop10Fragment;
import huyvqph39239.fpoly.pnlib.Fragment.flLoaiSach;
import huyvqph39239.fpoly.pnlib.Fragment.flPhieuMuon;
import huyvqph39239.fpoly.pnlib.Fragment.flQuanLyThanhVien;
import huyvqph39239.fpoly.pnlib.Fragment.flQuanlySach;
import huyvqph39239.fpoly.pnlib.Fragment.flThongKeDoanhThu;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        Toolbar toolbar = findViewById(id.toolbar);
        FrameLayout frameLayout = findViewById(id.framelayout);
        NavigationView navigationView = findViewById(id.nav_view);
        drawerLayout = findViewById(id.Drawer);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.open,R.string.cloes);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment ;
                if (item.getItemId()== id.mQLPhieuMuon){
                    fragment  = new flPhieuMuon();
                    replaceFrg(fragment);
                }else if (item.getItemId()== id.mQlLoaiSach) {
                    fragment = new flLoaiSach();
                    replaceFrg(fragment);
                } else if (item.getItemId() ==id.mDangXuat) {
                    Intent intent = new Intent(MainActivity.this, dangnhap.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else if (item.getItemId() == id.mtop10) {
                    fragment = new ThongKeTop10Fragment();
                    replaceFrg(fragment);
                }
                else if (item.getItemId() == id.mDoanhThu) {
                    fragment = new flThongKeDoanhThu();
                    replaceFrg(fragment);
                }
                else if (item.getItemId() == id.mQLThanhVien) {
                    fragment = new flQuanLyThanhVien();
                    replaceFrg(fragment);
                }
                else if (item.getItemId() == id.mQLSach) {
                    fragment = new flQuanlySach();
                    replaceFrg(fragment);
                }
                else if (item.getItemId()== id.mDoiMatKhau) {
                    showDialogDoiMatKhau();
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
        replaceFrg(new flPhieuMuon());
        navigationView.getMenu().findItem(id.mQLPhieuMuon).setChecked(true);
    }
    public void replaceFrg(Fragment frg){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(id.framelayout, frg).commit();
    }
    private void showDialogDoiMatKhau(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(layout.dialog_doimatkhau,null);
        EditText edtOldpass = view.findViewById(id.edtPassOld);
        EditText edtNewpass = view.findViewById(id.edtNewpass);
        EditText edtRenewPass = view.findViewById(id.edtReNewpass);
        builder.setView(view);
        builder.setPositiveButton("Huy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("Cap nhat", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String oldpass = edtOldpass.getText().toString();
                String newPass = edtNewpass.getText().toString();
                String reNewPass = edtRenewPass.getText().toString();
                if (newPass.equals(reNewPass)){
                    SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN",MODE_PRIVATE);
                    String matt = sharedPreferences.getString("matt","");
                    ThuThuDAO thuThuDAO = new ThuThuDAO(MainActivity.this);
                    boolean check = thuThuDAO.capnhatMatKhau(matt,oldpass,newPass);
                    if (check){
                        Toast.makeText(MainActivity.this, "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, dangnhap.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this, "Cap nhat mat khau thata bai", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Mat khau khong trung nhau", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog alertDialog =builder.create();
        alertDialog.show();
    }

}