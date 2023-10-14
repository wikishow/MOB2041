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

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder> {
   private Context context;
   private ArrayList<Sach> list;

    public SachAdapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sach,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMsach.setText("Ma sach: "+ list.get(position).getMasach());
        holder.txttensach.setText("Ten sach: "+ list.get(position).getTensach());
        holder.txtMaloai.setText("Ma loai: "+ list.get(position).getMaloai());
        holder.txtgiathue.setText("Gia thue: "+ list.get(position).getGiathue());
        holder.txttenloai.setText("Ten loai: "+ list.get(position).getTenLoai());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
         TextView txtMsach, txttensach, txtgiathue, txttenloai,txtMaloai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMsach = itemView.findViewById(R.id.txtMsach);
            txtgiathue= itemView.findViewById(R.id.txtgiathue);
            txttensach= itemView.findViewById(R.id.txttensach);
            txttenloai = itemView.findViewById(R.id.txttenloai);
            txtMaloai = itemView.findViewById(R.id.txtMaloai);
        }
    }
}
