package pl.jwn.resrev.domain.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.jwn.resrev.domain.model.Artefact;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import static pl.jwn.resrev.utils.SQLTablesConstants.UPLOADS_PATH;

@Service
@Transactional
@Slf4j
public class DocumentTransferService {

    public Artefact uploadDocument(MultipartFile mpf, Artefact art){

        byte[] bytes = new byte[0];
        try {
            bytes = mpf.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        art.setFileName(StringUtils.cleanPath(mpf.getOriginalFilename()));
        art.setFiletype(mpf.getContentType());
        art.setFileSize(bytes.length);
        art.setFileSha1(SHAsum(bytes));


        String storageLocation = UPLOADS_PATH +"/"+ art.getFileSha1();
        art.setStoredLocation(storageLocation);

        try {
            Files.write(Paths.get(storageLocation), bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return art;
    }

    private static String SHAsum(byte[] convertme) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return byteArray2Hex(md.digest(convertme));
    }

    private static String byteArray2Hex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }
}
