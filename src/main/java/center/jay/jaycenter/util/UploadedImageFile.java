package center.jay.jaycenter.util;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件处理
 *
 */
public class UploadedImageFile {
    MultipartFile image;

    //
    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
