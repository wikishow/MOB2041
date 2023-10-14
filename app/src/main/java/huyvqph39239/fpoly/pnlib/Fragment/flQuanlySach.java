package huyvqph39239.fpoly.pnlib.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import huyvqph39239.fpoly.pnlib.Adapter.SachAdapter;
import huyvqph39239.fpoly.pnlib.DAO.SachDAO;
import huyvqph39239.fpoly.pnlib.Model.Sach;
import huyvqph39239.fpoly.pnlib.R;

public class flQuanlySach extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fl_quanlysach,container,false);
        RecyclerView rccSach = view.findViewById(R.id.rcc_Sach);
        SachDAO sachDAO = new SachDAO( getContext());
        ArrayList<Sach> list = sachDAO.getDSDS();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rccSach.setLayoutManager(manager);
        SachAdapter adapter = new SachAdapter(getContext(),list);
        rccSach.setAdapter(adapter);
        return view;
    }
}
