package huyvqph39239.fpoly.pnlib.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import huyvqph39239.fpoly.pnlib.DAO.ThanhVienDAO;
import huyvqph39239.fpoly.pnlib.Model.ThanhVien;
import huyvqph39239.fpoly.pnlib.R;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder> {
   private Context context;
   private ArrayList<ThanhVien> list;
   private ThanhVienDAO thanhVienDAO;

    public ThanhVienAdapter(Context context, ArrayList<ThanhVien> list, ThanhVienDAO thanhVienDAO) {
        this.context = context;
        this.list = list;
        this.thanhVienDAO = thanhVienDAO;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thanhvien,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaTV.setText("Mã TV: " +list.get(position).getMaTV());
        holder.txtHoten.setText("Họ tên: "+list.get(position).getHoTen());
        holder.txtNamsinh.setText("Năm Sinh: " + list.get(position).getNamSinh());
        holder.ivedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCapnhat(list.get(holder.getAdapterPosition()));
            }
        });
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check = thanhVienDAO.xoaThanhVien(list.get(holder.getAdapterPosition()).getMaTV());
                switch (check){
                    case 1:
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    case 0:
                        Toast.makeText(context, "Xoa that bai", Toast.LENGTH_SHORT).show();
                    case -1:
                        Toast.makeText(context, "Thành viên đã có phiều mượn, không được phép xóa", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
          TextView txtMaTV, txtHoten,txtNamsinh;
          ImageView ivedit,ivDel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaTV = itemView.findViewById(R.id.txtMaTV);
            txtHoten = itemView.findViewById(R.id.txtHoten);
            txtNamsinh= itemView.findViewById(R.id.txtNamsinh);
            ivedit = itemView.findViewById(R.id.ivedit);
            ivDel = itemView.findViewById(R.id.ivdelete);

        }
    }
    private void showCapnhat(ThanhVien thanhVien){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_capnhatthanhvien,null );
        builder.setView(view);
        TextView txtMatv = view.findViewById(R.id.txtmatv);
        EditText edthoten = view.findViewById(R.id.edtHoten);
        EditText edtnamsinh  = view.findViewById(R.id.edtnamsinh);
        txtMatv.setText("Mã TV:" + thanhVien.getMaTV());
        edthoten.setText("Họ tên: "+thanhVien.getHoTen());
        edtnamsinh.setText("Năm Sinh: "+thanhVien.getNamSinh());
        builder.setNegativeButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String hoten = edthoten.getText().toString();
                String namsinh = edtnamsinh.getText().toString();
                int id = thanhVien.getMaTV();
                boolean check = thanhVienDAO.capnhatThongTinThanhVien(id,hoten,namsinh);
                if (check){

                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                loaddata();
                }
                else {
                    Toast.makeText(context, "Cap nhat that bai", Toast.LENGTH_SHORT).show();
                }

            }
        });
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
    private void loaddata(){
        list.clear();
        list = thanhVienDAO.getDSThanhVien();
        notifyDataSetChanged();
    }
}
