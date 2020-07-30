package center.jay.jaycenter.mapper;

import center.jay.jaycenter.pojo.Diary;

import java.util.List;

public interface DiaryMapper {
    void add(Diary diary);
    void delete(int id);
    void update(Diary diary);
    Diary get(int id);
    List<Diary> listByUid(int uid);
    List<Diary> listRecentByUid(int uid, int n);
}
