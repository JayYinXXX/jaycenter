package center.jay.jaycenter.mapper;

import center.jay.jaycenter.pojo.DiaryContent;

import java.util.List;

public interface DiaryContentMapper {
    void add(DiaryContent diaryContent);
    void delete(int id);
    void update(DiaryContent diaryContent);
    DiaryContent get(int id);
    List<DiaryContent> listByDid(int did);

}
