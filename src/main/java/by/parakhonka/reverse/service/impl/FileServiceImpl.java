package by.parakhonka.reverse.service.impl;

import by.EvheniyParakhonka.JSONObject;
import by.EvheniyParakhonka.XML;
import by.parakhonka.reverse.controller.FileController;
import by.parakhonka.reverse.dao.IHistoryDao;
import by.parakhonka.reverse.entity.History;
import by.parakhonka.reverse.service.IAuthService;
import by.parakhonka.reverse.service.IFileService;
import by.parakhonka.reverse.util.MD5;
import by.parakhonka.reverse.util.StringCount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Objects;
import java.util.TimeZone;

@Service
public class FileServiceImpl implements IFileService {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    private final IHistoryDao mHistoryDao;
    private final IAuthService mAuthService;

    @Autowired
    public FileServiceImpl(IHistoryDao mHistoryDao, IAuthService pAuthService) {
        this.mHistoryDao = mHistoryDao;
        mAuthService = pAuthService;
    }

    /**
     * get upload file and defines what type of files to treat
     *
     * @param pFile upload multipart file
     * @throws NoSuchAlgorithmException we use MD5 for coding file userName
     */
    @Override
    public void uploadFile(MultipartFile pFile, boolean pSaveFile) throws NoSuchAlgorithmException {
        logger.debug("upload File");
        String fileExtentionsJson = ".json";
        String fileExtentionsXml = ".xml";
        String fileName = pFile.getOriginalFilename();
        logger.debug("original userName of file: " + pFile.getOriginalFilename());
        assert fileName != null;
        int lastIndex = fileName.lastIndexOf('.');
        String substring = fileName.substring(lastIndex, fileName.length());

        if (fileExtentionsJson.contains(substring)) {
            jsonFile(pFile, pSaveFile);
        } else if (fileExtentionsXml.contains(substring)) {
            xmlFile(pFile, pSaveFile);
        }
    }

    /**
     * reformat xml file to string value, then write file
     *
     * @param pFile multipart xml file
     * @throws NoSuchAlgorithmException MD5
     */
    private void xmlFile(MultipartFile pFile, boolean pSaveFile) throws NoSuchAlgorithmException {
        logger.debug("xml file methods");
        String xmlString = fileToString(pFile);
        JSONObject xmlJSONObj = XML.toJSONObject(xmlString);
        String jsonString = xmlJSONObj.toString(4);
        String[] tokens = Objects.requireNonNull(pFile.getOriginalFilename()).split("\\.(?=[^\\.]+$)");
        if (pSaveFile) {
            if (StringCount.countLines(jsonString) > 50) {
                //        save to db full userName file
                saveToDB(pFile.getOriginalFilename(), tokens[0] + ".json", true);
            } else {
                saveToDB(jsonString, xmlString, false);
            }
        }
        String idFile = String.valueOf(mHistoryDao.getLastHistory(mAuthService.getUserName()).getId());
        String jsonDir = System.getProperty("user.dir") + "\\"
                + MD5.getMD5(idFile)
                + ".json";
        fileWriter(jsonDir, jsonString);
        String xmlDir = System.getProperty("user.dir") + "\\"
                + MD5.getMD5(idFile)
                + ".xml";
        fileWriter(xmlDir, xmlString);

    }

    /**
     * reformat json file to string, then write file to storage. Save file userName or file value to db
     * by criterion of lines in the file
     *
     * @param pFile json file
     * @throws NoSuchAlgorithmException MD5
     */
    private void jsonFile(MultipartFile pFile, boolean pSaveFile) throws NoSuchAlgorithmException {
        logger.debug("json file methods");
//        Multipart file to string
        String jsonString = fileToString(pFile);
//        create new json object
        JSONObject jsonArray = new JSONObject(jsonString);
//        reformat json object to xml string
        String xmlString = XML.toString(jsonArray);
//        split original userName to two part 1(userName), 2(.json or .xml)
        String[] tokens = Objects.requireNonNull(pFile.getOriginalFilename()).split("\\.(?=[^\\.]+$)");
        if (pSaveFile) {
            if (StringCount.countLines(jsonString) > 50) {
                //        save to db full userName file
                saveToDB(pFile.getOriginalFilename(), tokens[0] + ".xml", true);
            } else {
                saveToDB(jsonString, xmlString, false);
            }
        }
        String idFile = String.valueOf(mHistoryDao.getLastHistory(mAuthService.getUserName()).getId());
//        path to xml file where id encode in md5
        String xmlDir = System.getProperty("user.dir") + "\\"
                + MD5.getMD5(idFile)
                + ".xml";
//        write xml file to dir with encoding userName
        fileWriter(xmlDir, xmlString);
        String jsonDir = System.getProperty("user.dir") + "\\"
                + MD5.getMD5(idFile)
                + ".json";
        fileWriter(jsonDir, jsonString);
    }

    /**
     * get file with encoding userName
     *
     * @param id    id to encoding file userName
     * @param pFile real userName file
     * @return return file
     * @throws NoSuchAlgorithmException MD5
     */
    public File getFile(int id, String pFile) throws NoSuchAlgorithmException {
        String[] tokens = Objects.requireNonNull(pFile.split("\\.(?=[^\\.]+$)"));
        String expansion;
        if (tokens[1].equals("json")) {
            expansion = ".xml";
        } else {
            expansion = ".json";
        }
        return new File(MD5.getMD5(String.valueOf(id)) + expansion);
    }

    /**
     * write file to storage
     *
     * @param pDir  directory storage
     * @param value value storage
     */
    private void fileWriter(String pDir, String value) {
        logger.debug("directory xml file: " + pDir);
        try {
            FileWriter fileWriter = new FileWriter(pDir);
            fileWriter.write(value);
            fileWriter.close();
        } catch (IOException pE) {
            pE.printStackTrace();
        }
    }

    /**
     * reformat file to string value
     *
     * @param pFile multipart file
     * @return string value
     */
    private String fileToString(MultipartFile pFile) {
        try {
            return new String(pFile.getBytes());
        } catch (IOException pE) {
            pE.printStackTrace();
            return "exception";
        }
    }

    /**
     * save to db
     *
     * @param pJsonName json userName or value
     * @param pXmlName  xml userName or value
     * @param isFile    it is file
     */
    private void saveToDB(String pJsonName, String pXmlName, boolean isFile) {
        logger.debug("save history method");
        History history = new History();
        history.setJson(pJsonName);
        history.setXml(pXmlName);
        logger.debug("set json and xml");
        history.setFile(isFile);
        history.setDate(Calendar.getInstance(TimeZone.getTimeZone("GMT+3")).getTimeInMillis());
        history.setName(mAuthService.getUserName());
        mHistoryDao.save(history);
    }
}
