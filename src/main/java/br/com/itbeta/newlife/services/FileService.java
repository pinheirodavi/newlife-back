package br.com.itbeta.newlife.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService {
    private final Path pathRoot;

    FileService(@Value("${file-root-path}") String pathRoot){this.pathRoot= Paths.get(pathRoot);}

    public Path save(MultipartFile file, String path) throws IOException{
        Path moradorPath = this.pathRoot.resolve(path).resolve(UUID.randomUUID()+ file.getOriginalFilename()).toAbsolutePath();
        OutputStream outputStream = Files.newOutputStream(moradorPath);
        outputStream.write(file.getBytes());
        return moradorPath;
    }

    public File emptyFile(String path){
        Path resolve = this.pathRoot.resolve(path).resolve(UUID.randomUUID()+"-linhas-com-erro.xlsx");
        return resolve.toFile();
    }

    public byte[] getFile (String filePath) throws IOException{
        return Files.readAllBytes(Paths.get(filePath));
    }

    public void removeFile(String filePath){
        try{
            Files.delete(Paths.get(filePath));
        }catch(Exception e){

        }
    }
}
