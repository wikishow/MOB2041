package huyvqph39239.fpoly.pnlib.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import huyvqph39239.fpoly.pnlib.Model.Sach;
import huyvqph39239.fpoly.pnlib.database.DbHelper;

public class ThongkeDAO {
    DbHelper dbHelper;
    public ThongkeDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    public ArrayList<Sach> getTop10(){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase database= dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT pm.masach, sc.tensach, count(pm.masach) from PHIEUMUON pm, SACH sc WHERE pm.masach = sc.masach GROUP BY pm.masach,sc.tensach ORDER BY COUNT (pm.masach) DESC LIMIT 10",null);
        if (cursor.getCount()!= 0){
            cursor.moveToFirst();
            do {
                list.add(new Sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));

            }while (cursor.moveToNext());
        }
        return list;
    }
    public int getDoanhthu(String ngaybatdau,String ngayketthuc){
        ngaybatdau = ngaybatdau.replace("/","");
        ngayketthuc = ngayketthuc.replace("/","");
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT SUM(tienthue) " +
                "FROM PHIEUMUON\n" +
                "WHERE \n" +
                "ngay BETWEEN ? AND ? ;", new String[]{ngaybatdau,ngayketthuc});
        if (cursor.getCount()!= 0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }

}

