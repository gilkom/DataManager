package gilko.marcin.datamanager;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException{
		Path uploadPath = Paths.get(uploadDir);
		System.out.println("1");
		if(!Files.deleteIfExists(uploadPath)) {
			System.out.println("2");
			Files.createDirectories(uploadPath);
		}
		System.out.println("3");
		try(InputStream inputStream = multipartFile.getInputStream()){
			System.out.println("4");
			Path filePath = uploadPath.resolve(fileName);
			System.out.println("5");
			Files.copy(inputStream,  filePath, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("6");
		} catch(IOException ioe) {
			throw new IOException("Could not save image file: " + fileName, ioe);
		}
	}

}
