package huyvqph39239.fpoly.pnlib.Model;

public class PhieuMuon {
    private int mapm;
    private int matv;
    private String matt;
    private int maSach;
    private String ngay;
    private int trasach;
    private int tienthue;
    private String tentv;
    private String tentt;
    private String tensach;

    public PhieuMuon(int anInt, int cursorInt, String string, String cursorString, String tentt, int maSach, int i, String ngay, int trasach, int tienthue) {
    }

    public PhieuMuon(int mapm, int matv, String tentv,String matt,String tentt,int maSach,String tensach,String ngay,int trasach, int tienthue) {
        this.mapm = mapm;
        this.matv = matv;
        this.matt = matt;
        this.maSach = maSach;
        this.ngay = ngay;
        this.trasach = trasach;
        this.tienthue = tienthue;
        this.tentv = tentv;
        this.tentt = tentt;
        this.tensach = tensach;
    }

    public PhieuMuon( int matv, String matt, int maSach, String ngay, int trasach, int tienthue) {
        this.matv = matv;
        this.matt = matt;
        this.maSach = maSach;
        this.ngay = ngay;
        this.trasach = trasach;
        this.tienthue = tienthue;
    }

    public int getMapm() {
        return mapm;
    }

    public void setMapm(int mapm) {
        this.mapm = mapm;
    }

    public int getMatv() {
        return matv;
    }

    public void setMatv(int matv) {
        this.matv = matv;
    }

    public String getMatt() {
        return matt;
    }

    public void setMatt(String matt) {
        this.matt = matt;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getTrasach() {
        return trasach;
    }

    public void setTrasach(int trasach) {
        this.trasach = trasach;
    }

    public int getTienthue() {
        return tienthue;
    }

    public void setTienthue(int tienthue) {
        this.tienthue = tienthue;
    }

    public String getTentv() {
        return tentv;
    }

    public void setTentv(String tentv) {
        this.tentv = tentv;
    }

    public String getTentt() {
        return tentt;
    }

    public void setTentt(String tentt) {
        this.tentt = tentt;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }
}
