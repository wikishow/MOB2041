package huyvqph39239.fpoly.pnlib.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import huyvqph39239.fpoly.pnlib.Model.ThanhVien;
import huyvqph39239.fpoly.pnlib.database.DbHelper;

public class ThanhVienDAO {
    DbHelper dbHelper;
    public ThanhVienDAO(Context context){
        dbHelper= new DbHelper(context);
    }
    public ArrayList<ThanhVien> getDSThanhVien(){
        ArrayList<ThanhVien> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM THANHVIEN",null);
        if (cursor.getCount()!= 0){
            cursor.moveToFirst();
            do {
                list.add(new ThanhVien(cursor.getInt(0),
                        cursor.getString(1),cursor.getString(2)));

            }while (cursor.moveToNext());
        }
        return list;
    }
    public boolean themThanhVien(String hoten, String namSinh){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten",hoten);
        values.put("namsinh",namSinh);
        long check = database.insert("THANHVIEN",null,values);
        if (check ==-1){
            return false;
        }
        return true;
    }
    public boolean capnhatThongTinThanhVien(int matv, String hoten,String namSinh){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten",hoten);
        values.put("namsinh",namSinh);
        long check = database.update("THANHVIEN",values,"matv = ?",new String[]{String.valueOf(matv)});
        if (check ==-1)
            return false;
        return true;
    }
    public int xoaThanhVien(int matv){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT *FROM PHIEUMUON WHERE matv = ?",new String[]{String .valueOf(matv)});
        if (cursor.getCount()!=0){
            return -1;
        }
        long check = database.delete("THANHVIEN", "matv =?", new String[]{String.valueOf(matv)});
        if (check ==-1){
            return 0;
        }
        return 1;
    }
}
