package pojo;

import java.util.Date;

//统计访问量表
public class Count {
    private int id;
    private Date date;
    private int count;
    private int sumCount;
    public Count(){}

    //添加
    public Count(Date date,int count,int sumCount){
        this.date=date;
        this.count=count;
        this.sumCount=sumCount;
    }
    //修改
    public Count(int id,Date date,int count,int sumCount){
        this.id=id;
        this.date=date;
        this.count=count;
        this.sumCount=sumCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSumCount() {
        return sumCount;
    }

    public void setSumCount(int sumCount) {
        this.sumCount = sumCount;
    }
}
