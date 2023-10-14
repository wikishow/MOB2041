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

import huyvqph39239.fpoly.pnlib.Adapter.Top10Adapter;
import huyvqph39239.fpoly.pnlib.DAO.ThongkeDAO;
import huyvqph39239.fpoly.pnlib.Model.Sach;
import huyvqph39239.fpoly.pnlib.R;

public class ThongKeTop10Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongketop10,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.rcc_top10);
        ThongkeDAO thongkeDAO = new ThongkeDAO(getContext());
        ArrayList<Sach> list = thongkeDAO.getTop10();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        Top10Adapter adapter = new Top10Adapter(getContext(),list);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
