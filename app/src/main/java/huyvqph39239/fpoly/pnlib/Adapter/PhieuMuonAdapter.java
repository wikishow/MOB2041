package huyvqph39239.fpoly.pnlib.Adapter;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import huyvqph39239.fpoly.pnlib.DAO.PhieuMuonDAO;
import huyvqph39239.fpoly.pnlib.Model.PhieuMuon;
import huyvqph39239.fpoly.pnlib.R;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.ViewHoder> {
    private ArrayList<PhieuMuon> list;
    private Context context;
    public PhieuMuonAdapter(ArrayList<PhieuMuon> list, Context context){
        this.list=list;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_phieumuon,parent,false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        holder.txtMapm.setText("Mã PM: "+ list.get(position).getMapm());
        holder.txtMatv.setText("Mã TV: "+ list.get(position).getMatv());
        holder.txttentv.setText("Tên TV: "+ list.get(position).getTentv());
        holder.txtTentt.setText("Tên thủ thư: "+ list.get(position).getTentt());
        holder.txtmatt.setText("Ma TT: "+ list.get(position).getMatt());
        holder.txtmasach.setText("Mã sách: "+ list.get(position).getMaSach());
        holder.txttensach.setText("Tên sách: "+ list.get(position).getTensach());
        holder.txtngay.setText("Ngày: "+ list.get(position).getNgay());
        String trangthai="";
        if (list.get(position).getTrasach()==1){
            trangthai="đã trả sách";
            holder.btntrasach.setVisibility(View.GONE);
        }else {
            trangthai= "chưa trả sách";
            holder.btntrasach.setVisibility(View.VISIBLE);
        }
        holder.txttrangthai.setText("Trạng thái: "+ trangthai);
        holder.txttienthue.setText("Tiền thuê: "+ list.get(position).getTienthue());
        PhieuMuon pm = list.get(position);
        holder.btntrasach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(context);
                boolean kiemtra = phieuMuonDAO.thaydoitrangthai(list.get(holder.getAdapterPosition()).getMapm());
                if (kiemtra){
                    list.clear();
                    list= phieuMuonDAO.getDSPM();
                    notifyDataSetChanged();
                }else{
                    Toast.makeText(context, "thay doi trang thai that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                list.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "đã xóa", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder{
        TextView txtMapm,txtMatv,txttentv,txtmatt,txtTentt,txtmasach,txttensach,txtngay,txttrangthai,txttienthue;
        Button btntrasach;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            txtMapm = itemView.findViewById(R.id.txtmapm);
            txtMatv = itemView.findViewById(R.id.txtmatv);
            txttentv = itemView.findViewById(R.id.txtTenTV);
            txtTentt= itemView.findViewById(R.id.txtTenTT);
            txtmatt= itemView.findViewById(R.id.txtMatt);
            txtmasach = itemView.findViewById(R.id.txtmasach);
            txttensach = itemView.findViewById(R.id.txtTenSach);
            txtngay = itemView.findViewById(R.id.txtNgay);
            txttrangthai = itemView.findViewById(R.id.txtTRangThai);
            txttienthue = itemView.findViewById(R.id.txttienthue);
            btntrasach = itemView.findViewById(R.id.trasach);


        }
    }
}
