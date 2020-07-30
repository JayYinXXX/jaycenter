package center.jay.jaycenter.controller;

import center.jay.jaycenter.pojo.Diary;
import center.jay.jaycenter.pojo.DiaryContent;
import center.jay.jaycenter.pojo.DiaryImage;
import center.jay.jaycenter.pojo.User;
import center.jay.jaycenter.service.DiaryContentService;
import center.jay.jaycenter.service.DiaryImageService;
import center.jay.jaycenter.service.DiaryService;
import center.jay.jaycenter.service.UserService;
import center.jay.jaycenter.util.DateUtil;
import center.jay.jaycenter.util.ImageUtil;
import center.jay.jaycenter.util.Page;
import center.jay.jaycenter.util.UploadedImageFile;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
public class DiaryController {

    @Autowired
    DiaryService diaryService;
    @Autowired
    DiaryContentService diaryContentService;
    @Autowired
    UserService userService;
    @Autowired
    DiaryImageService diaryImageService;


    // Diary管理
    // 每天只能有一个Diary，add的时候要进行检查，看日期是否重复
    @RequestMapping("admin_diary_add")
    public String add(Diary d) {
        // 查询最新最新日记（数量为1）
        List<Diary> ds = diaryService.listRecentByUid(d.getUid(), 1);
        // 没有最新日记，或者最新日记不是今天创建的，才允许新建日记
        if (ds.isEmpty() || DateUtil.getIntervalDays(ds.get(0).getCreateDate()) != 0) {
            d.setCreateDate(new Date());
            diaryService.add(d);
        }

        return "redirect:admin_diary_list?uid=" + d.getUid();
    }

    @RequestMapping("admin_diary_delete")
    public String delete(int id) {
        Diary d = diaryService.get(id);
        // Diary内容为空才能删除
        List<DiaryContent> dcs = diaryContentService.listByDid(id);
        if (dcs.isEmpty()) {
            diaryService.delete(id);
        }

        return "redirect:admin_diary_list?uid=" + d.getUid();
    }

    @RequestMapping("admin_diary_edit")
    public String edit(int id, Model model) {
        Diary d = diaryService.get(id);
        User u = userService.get(d.getUid());
        List<DiaryContent> dcs = diaryContentService.listByDid(id);
        List<DiaryImage> dis = diaryImageService.listByDid(id);

        model.addAttribute("u", u);
        model.addAttribute("d", d);
        model.addAttribute("dcs", dcs);
        model.addAttribute("dis", dis);
        return "admin/editDiary.jsp";
    }

    @RequestMapping("admin_diary_list")
    public String list(int uid, Page page, Model model) {
        User u = userService.get(uid);
        // 分页查询
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Diary> ds = diaryService.listByUid(uid);
        int total = (int) new PageInfo<>(ds).getTotal();
        page.setTotal(total);
        page.setParam("&uid=" + uid);

        model.addAttribute("u", u);
        model.addAttribute("ds", ds);
        model.addAttribute("page", page);
        return "admin/listDiary.jsp";
    }



    // DiaryContent管理
    @RequestMapping("admin_diary_content_add")
    public String addContent(DiaryContent dc) {
        diaryContentService.add(dc);

        return "redirect:admin_diary_edit?id=" + dc.getDid();
    }

//    @RequestMapping("admin_diary_content_edit")
//    public String editContent() {
//
//    }

    @RequestMapping("admin_diary_content_delete")
    public String deleteContent(int id) {
        DiaryContent dc = diaryContentService.get(id);
        diaryContentService.delete(id);

        return "redirect:admin_diary_edit?id=" + dc.getDid();
    }



    // DiaryImage管理
    @RequestMapping("admin_diary_image_add")
    public String addImage(DiaryImage di, UploadedImageFile uif, HttpSession session) throws IOException {
        Diary d = diaryService.get(di.getDid());

        diaryImageService.add(di);
        // 保存图片到本地
        String fileName = di.getId() + ".jpg";
        String folder = session.getServletContext().getRealPath("img/" + d.getUid() + "/diary");
        File file = new File(folder, fileName);
        file.getParentFile().mkdirs();
        uif.getImage().transferTo(file);
        // 转为jpg
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);

        return "redirect:admin_diary_edit?id=" + di.getDid();
    }

    @RequestMapping("admin_diary_image_delete")
    public String deleteImage(int id, HttpSession session) {
        DiaryImage di = diaryImageService.get(id);
        Diary d = diaryService.get(di.getDid());

        // 删除本地文件和数据库记录
        String fileName = di.getId() + ".jpg";
        String folder = session.getServletContext().getRealPath("img/" + d.getUid() + "/diary");
        File file = new File(folder, fileName);
        if (file.delete()) {
            diaryImageService.delete(di.getId());
        }

        return "redirect:admin_diary_edit?id=" + di.getDid();
    }
}
