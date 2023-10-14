package huyvqph39239.fpoly.pnlib.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import huyvqph39239.fpoly.pnlib.database.DbHelper;

public class ThuThuDAO {
    DbHelper dbHelper;
    public ThuThuDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    //dang nhap
    public boolean checkDangNhap(String matt, String matkhau){
        SQLiteDatabase database= dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT *FROM THUTHU WHERE matt=? AND matkhau=?",new String[]{matt,matkhau});
        if (cursor.getCount()!= 0){
            return true;
        }else{
            return false;
        }
    }
    public boolean capnhatMatKhau(String username, String oldPass,String newPass) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM THUTHU WHERE matt=? AND matkhau =? ", new String[]{username, oldPass});
        if (cursor.getCount() > 0) {
            ContentValues values = new ContentValues();
            values.put("matkhau", newPass);
            long check = database.update("THUTHU", values, "matt = ?", new String[]{username});
            if (check == -1) {
                return false;
            }
            return true;

        }


        return false;
    }
}
