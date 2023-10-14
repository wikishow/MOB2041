package huyvqph39239.fpoly.pnlib.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context){
        super(context,"DANGKYMONHOC",null,2);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
         String dbThuThu = " CREATE TABLE THUTHU( matt text primary key, hoten text, matkhau text)";
         sqLiteDatabase.execSQL(dbThuThu);
         String dbThanhVien = "CREATE TABLE THANHVIEN(matv integer primary key autoincrement, hoten text,namsinh text) ";
         sqLiteDatabase.execSQL(dbThanhVien);
         String dbLoai = " CREATE TABLE LOAISACH( maloai integer primary key autoincrement,tenloai text)";
         sqLiteDatabase.execSQL(dbLoai);
         String dbSach = "CREATE TABLE SACH(masach integer primary key autoincrement, tensach text, giathue integer,maloai integer references LOAISACH(maloai))";
         sqLiteDatabase.execSQL(dbSach);
         String dbPhieuMuon = "CREATE TABLE PHIEUMUON(mapm integer primary key autoincrement, matv integer references THANHVIEN(matv), matt text references THUTHU(matt), masach integer references SACH(masach),ngay text, trasach integer, tienthue integer)";
         sqLiteDatabase.execSQL(dbPhieuMuon);
         //data mau
        sqLiteDatabase.execSQL("INSERT INTO LOAISACH VALUES(1,'thieu nhi'),(2,'tinh cam'),(3,'giao khoa')");
        sqLiteDatabase.execSQL("INSERT INTO SACH VALUES(1,'Co dau 8 tuoi',2500,1),(2,'thang cuoi',1000,1),(3,'Lap trinh android',3000,3)");
        sqLiteDatabase.execSQL("INSERT INTO THUTHU VALUES('thuthu01','Ngyen Van A','abc123')");
        sqLiteDatabase.execSQL("INSERT INTO PHIEUMUON VALUES(1,1,'thuthu01',1,'19/3/2023',1,2500),(2,2,'thuthu01',2,'22/3/2023',1,2500)");
        sqLiteDatabase.execSQL("INSERT INTO THANHVIEN VALUES(1,'Vu Quang Huy','2001'),(2,'Vu Quang Dao','2002')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i!=i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THUTHU");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THANHVIEN");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LOAISACH");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SACH");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PHIEUMUON");
            onCreate(sqLiteDatabase);
        }
    }
}
