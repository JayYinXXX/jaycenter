package center.jay.jaycenter.service;

import center.jay.jaycenter.pojo.Diary;

import java.util.List;

public interface DiaryService {
    void add(Diary diary);
    void delete(int id);
    void update(Diary diary);
    Diary get(int id);
    List<Diary> listByUid(int uid);

    // 指定User的最近的n个Diary
    List<Diary> listRecentByUid(int uid, int n);
    // 返回包括本周在内的2周的List<Diary>，没有新建Diary的日期使用空Diary
    List<Diary> twoWeeks(int uid);

    void fillImages(Diary diary);
}
