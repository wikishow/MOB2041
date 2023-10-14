package huyvqph39239.fpoly.pnlib.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

import huyvqph39239.fpoly.pnlib.DAO.ThongkeDAO;
import huyvqph39239.fpoly.pnlib.R;

public class flThongKeDoanhThu extends Fragment {
      @Nullable
      @Override
      public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          View view = inflater.inflate(R.layout.fl_thongkedoanhthu,container,false);
          EditText edtStart = view.findViewById(R.id.edtStart);
          EditText edtEnd = view.findViewById(R.id.edtEnd);
          Button btnThongke = view.findViewById(R.id.btnThongke);
          TextView txtketqua = view.findViewById(R.id.txtketqua);
          Calendar calendar = Calendar.getInstance();

          edtStart.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                          new DatePickerDialog.OnDateSetListener() {
                              @Override
                              public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                  String ngay = "";
                                  String thang = "";
                                  if (i2< 10){
                                      ngay = "0" + i2;
                                  }else {
                                      ngay = String .valueOf(i2);
                                  }
                                  if ((i1 + 1) <10){
                                      thang = "0" + (i1 + 1);
                                  }else {
                                      thang = String .valueOf((i1 + 1));
                                  }
                                edtStart.setText(i + "/" +thang + "/" + ngay);
                              }
                          },
                          calendar.get(Calendar.YEAR),
                          calendar.get(Calendar.MONTH),
                          calendar.get(Calendar.DAY_OF_MONTH)
                          );
                  datePickerDialog.show();
              }
          });
          edtEnd.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                          new DatePickerDialog.OnDateSetListener() {
                              @Override
                              public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                  String ngay = "";
                                  String thang = "";
                                  if (i2< 10){
                                      ngay = "0" + i2;
                                  }else {
                                      ngay = String .valueOf(i2);
                                  }
                                  if ((i1 + 1) <10){
                                      thang = "0" + (i1 + 1);
                                  }else {
                                      thang = String .valueOf((i1 + 1));
                                  }
                                  edtEnd.setText(i + "/" + thang + "/" + ngay);
                              }
                          },
                          calendar.get(Calendar.YEAR),
                          calendar.get(Calendar.MONTH),
                          calendar.get(Calendar.DAY_OF_MONTH)
                  );
                  datePickerDialog.show();
              }
          });
          btnThongke.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  ThongkeDAO thongkeDAO = new ThongkeDAO(getContext());
                  String ngaybatdau = edtStart.getText().toString();
                  String ngayketthuc = edtEnd .getText().toString();
                  int doanhthu = thongkeDAO.getDoanhthu(ngaybatdau,ngayketthuc);
                  txtketqua.setText(doanhthu +"VND");
              }
          });
         return view;
      }

  }
