package br.homedeveloper.game.service;

import br.homedeveloper.game.service.impl.FileServiceImpl;
import br.homedeveloper.game.service.impl.PlayerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FileServiceTest {
    @Mock
    private FileServiceImpl fileService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReadFile() throws IOException {
        String fileContent = "line1\nline2\nline3";
        MultipartFile mockFile = new MockMultipartFile("test.log", fileContent.getBytes(StandardCharsets.UTF_8));
        when(fileService.readFile(mockFile)).thenCallRealMethod();
        List<String> result = fileService.readFile(mockFile);
        List<String> expected = Arrays.asList("line1", "line2", "line3");
        assertEquals(expected, result);
    }
}
