package br.homedeveloper.game.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    List<String> readFile(MultipartFile file) throws IOException;
}
