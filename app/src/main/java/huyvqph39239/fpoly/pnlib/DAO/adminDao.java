package huyvqph39239.fpoly.pnlib.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import huyvqph39239.fpoly.pnlib.database.DbHelper;

public class adminDao {
    private DbHelper dbHelper;
    public adminDao(Context context){
        dbHelper = new DbHelper(context);
    }
    public boolean Register(String pass, String hoten){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten",hoten);
        values.put("matkhau",pass);
        long check = database.insert("THUTHU",null,values);
        if (check ==-1){
            return false;
        }
        return true;
    }
}
