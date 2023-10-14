package huyvqph39239.fpoly.pnlib.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import huyvqph39239.fpoly.pnlib.Model.PhieuMuon;
import huyvqph39239.fpoly.pnlib.database.DbHelper;

public class PhieuMuonDAO {
    DbHelper dbHelper;
    public PhieuMuonDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    public ArrayList<PhieuMuon> getDSPM(){
        ArrayList<PhieuMuon> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT pm.mapm,pm.matv,tv.hoten,pm.matt,tt.hoten,pm.masach,sc.tensach,pm.ngay,pm.trasach,pm.tienthue FROM PHIEUMUON pm, THANHVIEN tv, THUTHU tt,SACH sc WHERE pm.matv= tv.matv AND pm.matt = tt.matt AND pm.masach = sc.masach",null);
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
            do {
                list.add(new PhieuMuon(cursor.getInt(0),
                        cursor.getInt(1),cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getInt(8),
                        cursor.getInt(9)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public boolean thaydoitrangthai(int mapm){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("trasach",1);
        long check = database.update("PHIEUMUON",values,"mapm=?", new String[]{String.valueOf(mapm)});
        if (check==-1){
            return false;
        }
        return true;
    }
    public  boolean themPM( PhieuMuon phieuMuon){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put("mapm",phieuMuon.getMapm());
        values.put("matv",phieuMuon.getMatv());
        values.put("matt",phieuMuon.getMatt());
        values.put("masach",phieuMuon.getMaSach());
        values.put("ngay",phieuMuon.getNgay());
        values.put("trasach",phieuMuon.getTrasach());
        values.put("tienthue",phieuMuon.getTienthue());
        long check = database.insert("PHIEUMUON",null,values);
        if (check ==1){
            return false;
        }
        return true;

    }
    public  boolean delete(String id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("PHIEUMUON","mapm=?",new String[]{String.valueOf(id)});
        return (row>0);
    }
}
