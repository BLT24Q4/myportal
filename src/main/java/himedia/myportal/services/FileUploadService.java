package himedia.myportal.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	private static final String SAVE_PATH = "c:/uploads";
	
	private static final Logger logger = 
			LoggerFactory.getLogger(FileUploadService.class);
	public String store(MultipartFile multipartFile) {
		String saveFilename = "";
		
		try {
			String originalFilename = multipartFile.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
			saveFilename = getSaveFilename(extName);
			
			logger.debug("########## " + saveFilename);
			
			//	멀티파트 파일을 저장
			writeFile(multipartFile, saveFilename);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		
		return saveFilename;	//	새로 생성된 파일 이름
	}
	
	//	멀티파트 파일을 실제 위치로 저장하는 메서드
	private void writeFile(
			MultipartFile multipartFile,
			String saveFilename) throws IOException {
		byte[] fileData = multipartFile.getBytes();
		FileOutputStream fos = 
			new FileOutputStream(SAVE_PATH + "/" + saveFilename);
		fos.write(fileData);
		
		fos.close();
	}
	
	//	중복되지 않은 파일명 부여
	private String getSaveFilename(String ext) {
		//	파일 이름 중복 방지를 위한 파일명 생성
		//	TODO: 규칙 만들어 보기
		Calendar cal = Calendar.getInstance();
		return String.valueOf(cal.getTimeInMillis() / 1000)
				+ ext.toLowerCase();
	}
}
