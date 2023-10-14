package huyvqph39239.fpoly.pnlib.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import huyvqph39239.fpoly.pnlib.Adapter.ThanhVienAdapter;
import huyvqph39239.fpoly.pnlib.DAO.ThanhVienDAO;
import huyvqph39239.fpoly.pnlib.Model.ThanhVien;
import huyvqph39239.fpoly.pnlib.R;


public class flQuanLyThanhVien extends Fragment {
    ThanhVienDAO thanhVienDAO;
    RecyclerView rccThanhVien;
    FloatingActionButton floatAdd;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fl_quan_ly_thanh_vien, container, false);
         rccThanhVien = view.findViewById(R.id.rcc_ThanhVien);
         floatAdd = view.findViewById(R.id.floatAdd);
         thanhVienDAO = new ThanhVienDAO(getContext());
         loaddata();

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();
            }
        });
        return view;
    }
    private void showdialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themthanhvien,null);
        builder.setView(view);
        EditText edtHoten = view.findViewById(R.id.edtHoten);
        EditText edtnamsinh = view.findViewById(R.id.edtnamsinh);
        builder.setNegativeButton("Them", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String hoten = edtHoten.getText().toString();
                String namsinh = edtnamsinh.getText().toString();
                if (TextUtils.isEmpty(hoten) || TextUtils.isEmpty(namsinh)){
                    Toast.makeText(getActivity(), "Khong duoc de trong thong tin", Toast.LENGTH_SHORT).show();
                }else {
                    boolean check = thanhVienDAO.themThanhVien(hoten,namsinh);
                    if (check){
                        Toast.makeText(getContext(), "Them thanh cong", Toast.LENGTH_SHORT).show();
                        loaddata();
                    }else {
                        Toast.makeText(getContext(), "Them that bai", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
        builder.setPositiveButton("Huy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();


    }
    private void loaddata(){
        ArrayList<ThanhVien> list = thanhVienDAO.getDSThanhVien();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rccThanhVien.setLayoutManager(manager);
        ThanhVienAdapter adapter = new ThanhVienAdapter(getContext(),list, thanhVienDAO);
        rccThanhVien.setAdapter(adapter);
    }
}