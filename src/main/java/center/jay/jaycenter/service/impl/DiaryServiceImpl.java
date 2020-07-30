package center.jay.jaycenter.service.impl;

import center.jay.jaycenter.mapper.DiaryImageMapper;
import center.jay.jaycenter.mapper.DiaryMapper;
import center.jay.jaycenter.pojo.Diary;
import center.jay.jaycenter.pojo.DiaryImage;
import center.jay.jaycenter.service.DiaryService;
import center.jay.jaycenter.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    DiaryMapper diaryMapper;
    @Autowired
    DiaryImageMapper diaryImageMapper;

    @Override
    public void add(Diary diary) {
        diaryMapper.add(diary);
    }

    @Override
    public void delete(int id) {
        diaryMapper.delete(id);
    }

    @Override
    public void update(Diary diary) {
        diaryMapper.update(diary);
    }

    @Override
    public Diary get(int id) {
        return diaryMapper.get(id);
    }

    @Override
    public List<Diary> listByUid(int uid) {
        return diaryMapper.listByUid(uid);
    }

    @Override
    public List<Diary> listRecentByUid(int uid, int n) {
        if (n < 0) {
            n = 0;
        }
        return diaryMapper.listRecentByUid(uid, n);
    }

    @Override
    public List<Diary> twoWeeks(int uid) {
        int n = 2;
        List<Diary> ds = listRecentByUid(uid, n*7);
        LinkedList<Diary> result = new LinkedList<>();

        // 本周中剩余的天数，设为空Diary
        int d = 7 - DateUtil.dayOfWeek(new Date());
        int i = d;
        while (i != 0) {
            result.addFirst(new Diary());
            --i;
        }

        i = 0;  // 剩余未处理的索引
        int j = 0;  // 数据库取出的ds的索引
        while (i < 7*n - d) {
            // 日期匹配
            if (j < ds.size() && DateUtil.getIntervalDays(ds.get(j).getCreateDate()) == i) {
                result.addFirst(ds.get(j));
                result.getFirst().setFlag(1);
                ++j;
            }
            // 没有匹配日期，设为空Diary
            else {
                result.addFirst(new Diary());
            }
            // 设置标识为今天
            if (i == 0) {
                result.getFirst().setFlag(9);
            }
            ++i;
        }

        // 设置星期
        i = 0;
        for (Diary x : result) {
            x.setDayOfWeek(i % 7 +1);
            ++i;
        }

        return result;
    }

    @Override
    public void fillImages(Diary diary) {
        diary.setDis(diaryImageMapper.listByDid(diary.getId()));
    }
}
