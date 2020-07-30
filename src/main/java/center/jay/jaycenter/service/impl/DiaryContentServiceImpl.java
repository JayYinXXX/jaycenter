package center.jay.jaycenter.service.impl;

import center.jay.jaycenter.mapper.DiaryContentMapper;
import center.jay.jaycenter.pojo.DiaryContent;
import center.jay.jaycenter.service.DiaryContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryContentServiceImpl implements DiaryContentService {

    @Autowired
    DiaryContentMapper diaryContentMapper;

    @Override
    public void add(DiaryContent diaryContent) {
        diaryContentMapper.add(diaryContent);
    }

    @Override
    public void delete(int id) {
        diaryContentMapper.delete(id);
    }

    @Override
    public void update(DiaryContent diaryContent) {
        diaryContentMapper.update(diaryContent);
    }

    @Override
    public DiaryContent get(int id) {
        return diaryContentMapper.get(id);
    }

    @Override
    public List<DiaryContent> listByDid(int did) {
        return diaryContentMapper.listByDid(did);
    }
}
