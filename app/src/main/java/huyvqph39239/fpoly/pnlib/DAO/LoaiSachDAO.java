package huyvqph39239.fpoly.pnlib.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import huyvqph39239.fpoly.pnlib.Model.LoaiSach;
import huyvqph39239.fpoly.pnlib.database.DbHelper;

public class LoaiSachDAO {
    DbHelper dbHelper;
    public LoaiSachDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    //lay danh sach loai sach
    public ArrayList<LoaiSach> getDSLoaiSach(){
        ArrayList<LoaiSach> list = new ArrayList<>();
        SQLiteDatabase database= dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT *FROM LOAISACH",null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new LoaiSach(cursor.getInt(0),cursor.getString(1)));
            }while (cursor.moveToNext());

        }
        return list;
    }
    //them loai sach
    public boolean themLoaiSach(String tenloai){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenloai",tenloai);
        long check = database.insert("LOAISACH",null,values);
        if (check == -1){
            return false;
        }
        return true;
    }
    public  int xoaLoaiSach(int id){
       SQLiteDatabase database = dbHelper.getWritableDatabase();
       Cursor cursor = database.rawQuery("SELECT *FROM SACH WHERE maloai = ?", new String[]{String.valueOf(id)});
       if (cursor.getCount() != 0){
           return -1;
       }
       long check = database.delete("LOAISACH", "maloai = ?", new String[]{String.valueOf(id)});
       if (check == -1)
           return 0;
       return 1;
    }
    public boolean thayDOiLoaiSach(LoaiSach loaiSach){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenloai",loaiSach.getTenLoai());
        long check = database.update("LOAISACH",values, "maloai = ?", new String[]{String.valueOf( loaiSach.getId())});
        if (check == -1)
            return false;

        return true;
    }
}
