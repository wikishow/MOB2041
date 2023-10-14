package huyvqph39239.fpoly.pnlib.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import huyvqph39239.fpoly.pnlib.DAO.LoaiSachDAO;
import huyvqph39239.fpoly.pnlib.Model.ItemClick;
import huyvqph39239.fpoly.pnlib.Model.LoaiSach;
import huyvqph39239.fpoly.pnlib.R;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder> {
    private Context context;
    private ArrayList<LoaiSach> list;
    private ItemClick itemClick;

    public LoaiSachAdapter(Context context, ArrayList<LoaiSach> list, ItemClick itemClick) {
        this.context = context;
        this.list = list;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rccloaisach,parent,false);
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         holder.txtmaloai.setText("Mã loại: " + String.valueOf(list.get(position).getId()));
         holder.txtTenloai.setText("Tên loại: "+ list.get(position).getTenLoai());
         holder.imgView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
                 int check = loaiSachDAO.xoaLoaiSach(list.get(holder.getAdapterPosition()).getId());
                 switch (check){
                     case 1:
                         list.clear();
                         list = loaiSachDAO.getDSLoaiSach();
                         Toast.makeText(context, "Xóa thành công ", Toast.LENGTH_SHORT).show();
                         notifyDataSetChanged();
                         break;
                     case -1:
                         Toast.makeText(context, "Không thể xóa loại sách này vì đã có sách thuộc thể loại này", Toast.LENGTH_SHORT).show();
                         break;
                     case 0:
                         Toast.makeText(context, "Xoa that bai", Toast.LENGTH_SHORT).show();
                         break;
                     default:
                         break;
                 }
             }
         });
         holder.imgUpdate.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 LoaiSach loaiSach = list.get(holder.getAdapterPosition());
                 itemClick.onClickLoaiSach(loaiSach);
             }
         });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtmaloai, txtTenloai;
        ImageView imgView, imgUpdate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmaloai = itemView.findViewById(R.id.txtMaloai);
            txtTenloai = itemView.findViewById(R.id.txttenloai);
            imgView = itemView.findViewById(R.id.ic_delete);
            imgUpdate = itemView.findViewById(R.id.ic_edit);
        }
    }
}
