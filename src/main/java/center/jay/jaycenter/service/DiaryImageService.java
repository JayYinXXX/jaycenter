package center.jay.jaycenter.service;

import center.jay.jaycenter.pojo.DiaryImage;

import java.util.List;

public interface DiaryImageService {
    void add(DiaryImage diaryImage);
    void delete(int id);
    DiaryImage get(int id);
    List<DiaryImage> listByDid(int did);
}
