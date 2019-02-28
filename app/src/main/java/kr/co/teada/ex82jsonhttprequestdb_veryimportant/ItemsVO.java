package kr.co.teada.ex82jsonhttprequestdb_veryimportant;

public class ItemsVO {

    private String num;
    private String name;
    private String msg;
    private String filePath;
    private String date;

    //alt + insert
    public ItemsVO(String num, String name, String msg, String filePath, String date) {
        this.num = num;
        this.name = name;
        this.msg = msg;
        this.filePath = filePath;
        this.date = date;
    }//end of Constructor

    //alt + insert : getter and setter
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}//end of Items
