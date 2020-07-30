package center.jay.jaycenter.util;

public class Page {
    private int start;  // 起始索引
    private int count;  // 偏移，每页显示记录数
    private int total;  // 记录总数

    // 包装好一个参数，直接拼接在URL之后就可以使用，以免在jsp页面里拼接
    // 格式举例："&id=5"
    private String param;

    private static final int defaultCount = 10;  // 每页显示数默认为5

    // 构造器
    public Page() {
        count = defaultCount;
    }

    public Page(int start, int count) {
        this();
        this.start = start;
        this.count = count;
    }

    // 最后一页的起始记录
    public int getLast() {
        int last;
        if (total % count == 0)
            last = total - count;
        else
            last = total - total % count;
        return last < 0 ? 0 : last;
    }
    // 是否有前页
    public boolean isHasPre() {
        if (start == 0)
            return false;
        return true;
    }
    // 是否有后页
    public boolean isHasNext() {
        if (start == getLast())
            return false;
        return true;
    }
    // 总页数
    public int getTotalPage() {
        int totalPage;
        if (total % count == 0) {
            totalPage = total / count;
            if (totalPage == 0)
                totalPage = 1;
        }
        else
            totalPage = total / count + 1;
        return totalPage;
    }

    @Override
    public String toString() {
        return "Page [start=" + start + ", count=" + count + ", total=" + total +
                ", totalPage=" + getTotalPage() + "]";
    }

    // Getter/Setter
    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
