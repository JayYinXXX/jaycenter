package center.jay.jaycenter.pojo;

import java.util.Date;
import java.util.List;

public class Diary {
    private Integer id;
    private Integer uid;
    private String name;
    private Date createDate;
    // 包装
    private User user;

    // 非数据库字段
    private List<DiaryImage> dis;  // 配图
    private int dayOfWeek;  // 星期几
    private int flag;  // 0 不活跃，1 活跃，9 今天

    //
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<DiaryImage> getDis() {
        return dis;
    }

    public void setDis(List<DiaryImage> dis) {
        this.dis = dis;
    }
}
