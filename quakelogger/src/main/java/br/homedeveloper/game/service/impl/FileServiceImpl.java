package br.homedeveloper.game.service.impl;

import br.homedeveloper.game.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public List<String> readFile(MultipartFile file) throws IOException {
        byte[] fileContent = file.getBytes();
        String logContent = new String(fileContent);
        String[] logLines = logContent.split("\n");
        return Arrays.stream(logLines).collect(Collectors.toList());
    }

}
