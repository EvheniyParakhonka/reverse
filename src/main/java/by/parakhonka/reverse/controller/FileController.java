package by.parakhonka.reverse.controller;

import by.parakhonka.reverse.service.IFileService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    private final IFileService mFileService;

    @Autowired
    public FileController(IFileService pFileService) {
        mFileService = pFileService;
    }

    @RequestMapping(value = "/upload-file", method = RequestMethod.POST)
    @ResponseBody
    public void uploadFile(@RequestParam("file") MultipartFile pFile) throws NoSuchAlgorithmException {
        mFileService.uploadFile(pFile);
    }

    @RequestMapping(value = "/download-file", method = RequestMethod.GET)
    @ResponseBody
    public void download(HttpServletResponse pResponse, @RequestParam("id") String pId, @RequestParam("fileName") String pFilename) throws NoSuchAlgorithmException {

        File file = mFileService.getFile(Integer.valueOf(pId), pFilename);

        String fileNameWithOutExt = FilenameUtils.removeExtension(pFilename);
        String fileNameExt = FilenameUtils.getExtension(file.getName());
        pResponse.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");
        pResponse.setHeader("Content-Disposition", "attachment;filename=" + fileNameWithOutExt + "." + fileNameExt);
        BufferedInputStream inStream;
        try {
            inStream = new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream outStream = new BufferedOutputStream(pResponse.getOutputStream());

            byte[] buffer = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            outStream.flush();
            inStream.close();
        } catch (Exception pE) {
            logger.error("upload file error: " + pE.getMessage());
            pE.printStackTrace();
        }
    }
}

