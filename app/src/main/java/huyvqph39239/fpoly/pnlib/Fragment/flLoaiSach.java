package huyvqph39239.fpoly.pnlib.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import huyvqph39239.fpoly.pnlib.Adapter.LoaiSachAdapter;
import huyvqph39239.fpoly.pnlib.DAO.LoaiSachDAO;
import huyvqph39239.fpoly.pnlib.Model.ItemClick;
import huyvqph39239.fpoly.pnlib.Model.LoaiSach;
import huyvqph39239.fpoly.pnlib.R;

public class flLoaiSach extends Fragment {
    LoaiSachDAO dao;
    RecyclerView rccLoaiSach;
    EditText edtLoaisach;
    int maloai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.flloaisach,container,false);
        rccLoaiSach = view.findViewById(R.id.rcc_loaisach);
         edtLoaisach = view.findViewById(R.id.edtthemloaisach);
        Button btnthemloaisach = view.findViewById(R.id.btnThemLoaisach);
        Button btnsua = view.findViewById(R.id.btnSuaLoaisach);
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenloai = edtLoaisach.getText().toString();
                LoaiSach loaiSach = new LoaiSach(maloai,tenloai);
                if (dao.thayDOiLoaiSach(loaiSach)){
                    loaddata();
                    edtLoaisach.setText("");
                    Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Thay doii thong tin that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dao = new LoaiSachDAO(getContext());
        loaddata();

        btnthemloaisach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenloai = edtLoaisach.getText().toString();
                if (dao.themLoaiSach(tenloai)){
                    // thong bao va load lai danh sach
                    loaddata();
                    edtLoaisach.setText("");
                    Toast.makeText(getContext(), "Thêm loại sách thành công", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getContext(), " them loai sac khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
    private void loaddata(){
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rccLoaiSach.setLayoutManager(manager);
        ArrayList<LoaiSach> list = dao.getDSLoaiSach();
        LoaiSachAdapter adapter = new LoaiSachAdapter(getContext(), list, new ItemClick() {
            @Override
            public void onClickLoaiSach(LoaiSach loaiSach) {
                edtLoaisach.setText(loaiSach.getTenLoai());
                maloai= loaiSach.getId();
            }
        });
        rccLoaiSach.setAdapter(adapter);
    }
}
