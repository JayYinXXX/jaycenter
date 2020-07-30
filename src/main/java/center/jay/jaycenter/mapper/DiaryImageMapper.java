package center.jay.jaycenter.mapper;

import center.jay.jaycenter.pojo.DiaryImage;

import java.util.List;

public interface DiaryImageMapper {
    void add(DiaryImage diaryImage);
    void delete(int id);
    DiaryImage get(int id);
    List<DiaryImage> listByDid(int did);
}
