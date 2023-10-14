package huyvqph39239.fpoly.pnlib.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import huyvqph39239.fpoly.pnlib.Adapter.PhieuMuonAdapter;
import huyvqph39239.fpoly.pnlib.DAO.PhieuMuonDAO;
import huyvqph39239.fpoly.pnlib.DAO.SachDAO;
import huyvqph39239.fpoly.pnlib.DAO.ThanhVienDAO;
import huyvqph39239.fpoly.pnlib.Model.PhieuMuon;
import huyvqph39239.fpoly.pnlib.Model.Sach;
import huyvqph39239.fpoly.pnlib.Model.ThanhVien;
import huyvqph39239.fpoly.pnlib.R;

public class flPhieuMuon extends Fragment {
    PhieuMuonDAO phieuMuonDAO;
    RecyclerView recyclerView;
    ArrayList<PhieuMuon> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fl_qlphieumuon,container,false);
         recyclerView = view.findViewById(R.id.rccQlphieumuon);
        FloatingActionButton floatAdd = view.findViewById(R.id.floatAdd);
        //data
        loatData();
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();

            }
        });
        return view;
    }
    private void loatData(){
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        list = phieuMuonDAO.getDSPM();
        LinearLayoutManager manager= new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        PhieuMuonAdapter adapter = new PhieuMuonAdapter(list,getContext());
        recyclerView.setAdapter(adapter);
    };
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themphieumuon,null);
        Spinner spnThanhVien = view.findViewById(R.id.spnthanhvien);
        Spinner spnSach = view.findViewById(R.id.spnSach);
        EditText edttien = view.findViewById(R.id.edttien);
        getDaTaThanhVien(spnThanhVien);
        getDaTaSach(spnSach);
        builder.setView(view);
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //lay matv
                HashMap<String,Object> hsTV = (HashMap<String, Object>) spnThanhVien.getSelectedItem();
                int matv = (int) hsTV.get("matv");
                // lay ma sach
                HashMap<String,Object> hsSach = (HashMap<String, Object>) spnSach.getSelectedItem();
                int masach = (int) hsSach.get("masach");
                int tienthue = Integer.parseInt(edttien.getText().toString());
                    themPhieuMuon(matv, masach, tienthue);
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void getDaTaThanhVien( Spinner spnThanhVien){
        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(getContext());
        ArrayList<ThanhVien> list = thanhVienDAO.getDSThanhVien();

        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for(ThanhVien tv:list){
            HashMap<String,Object> hs = new HashMap<>();
            hs.put("matv",tv.getMaTV());
            hs.put("hoten",tv.getHoTen());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),listHM, android.R.layout.simple_list_item_1,new String[]{"hoten"}, new int[]{android.R.id.text1});
        spnThanhVien.setAdapter(simpleAdapter);
    }
    private void getDaTaSach( Spinner spnSach){
        SachDAO sachDAO = new SachDAO(getContext());
        ArrayList<Sach> list = sachDAO.getDSDS();

        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for(Sach sc:list){
            HashMap<String,Object> hs = new HashMap<>();
            hs.put("masach",sc.getMasach());
            hs.put("tensach",sc.getTensach());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),listHM, android.R.layout.simple_list_item_1,new String[]{"tensach"}, new int[]{android.R.id.text1});
        spnSach.setAdapter(simpleAdapter);
    }
    private void themPhieuMuon( int matv, int masach,int tienthue){
        //lay matt
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
        String matt = sharedPreferences.getString("matt","");
        //lay ngay hien tai
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
        String ngay = simpleDateFormat.format(currentTime);
        PhieuMuon phieuMuon = new PhieuMuon(matv,matt,masach,ngay,0,tienthue);
        boolean kiemtra = phieuMuonDAO.themPM(phieuMuon);
        if (kiemtra){
            loatData();
            Toast.makeText(getContext(), "Thêm phiếu mượn thành công", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(), "Them that bai", Toast.LENGTH_SHORT).show();
        }

    }
}
