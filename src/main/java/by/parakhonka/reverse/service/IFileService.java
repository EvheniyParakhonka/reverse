package by.parakhonka.reverse.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.security.NoSuchAlgorithmException;

public interface IFileService {

    void uploadFile(MultipartFile pFile, boolean saveFile) throws NoSuchAlgorithmException;

    File getFile(int pId, String pFileName) throws NoSuchAlgorithmException;
}
