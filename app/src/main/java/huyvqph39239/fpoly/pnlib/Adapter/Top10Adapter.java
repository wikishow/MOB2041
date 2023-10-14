package huyvqph39239.fpoly.pnlib.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import huyvqph39239.fpoly.pnlib.Model.Sach;
import huyvqph39239.fpoly.pnlib.R;

public class Top10Adapter extends RecyclerView.Adapter<Top10Adapter.ViewHolder> {
    private Context context;
    private ArrayList<Sach> list;

    public Top10Adapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rcctop10,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMasach.setText("Mã sách: "+String.valueOf(list.get(position).getMasach()));
        holder.txtTensach.setText("Tên sách: "+list.get(position).getTensach());
        holder.txtSoluongMuon.setText("Số lần mượn: "+String.valueOf(list.get(position).getSoluongdamuon()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMasach, txtTensach, txtSoluongMuon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMasach = itemView.findViewById(R.id.txtMasach);
            txtTensach = itemView.findViewById(R.id.txtTensach);
            txtSoluongMuon = itemView.findViewById(R.id.txtSoluongMuon);
        }
    }
}
