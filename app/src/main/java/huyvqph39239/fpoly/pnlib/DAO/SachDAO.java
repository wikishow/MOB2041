package huyvqph39239.fpoly.pnlib.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import huyvqph39239.fpoly.pnlib.Model.Sach;
import huyvqph39239.fpoly.pnlib.database.DbHelper;

public class SachDAO {
    DbHelper dbHelper;
    public SachDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    public ArrayList<Sach> getDSDS(){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT sc.masach,sc.tensach,sc.giathue,sc.maloai,lo.tenloai FROM SACH sc ,LOAISACH lo WHERE sc.maloai = lo.maloai",null);
        if (cursor.getCount()!= 0){
            cursor.moveToFirst();
            do {
                list.add( new Sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3), cursor.getString(4)));

            }while (cursor.moveToNext());
        }
        return list;
    }

}
