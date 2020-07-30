package center.jay.jaycenter.service.impl;

import center.jay.jaycenter.mapper.DiaryImageMapper;
import center.jay.jaycenter.pojo.DiaryImage;
import center.jay.jaycenter.service.DiaryImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryImageServiceImpl implements DiaryImageService {
    @Autowired
    DiaryImageMapper diaryImageMapper;

    @Override
    public void add(DiaryImage diaryImage) {
        diaryImageMapper.add(diaryImage);
    }

    @Override
    public void delete(int id) {
        diaryImageMapper.delete(id);
    }

    @Override
    public DiaryImage get(int id) {
        return diaryImageMapper.get(id);
    }

    @Override
    public List<DiaryImage> listByDid(int did) {
        return diaryImageMapper.listByDid(did);
    }
}
