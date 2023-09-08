package org.sp.springapp.util;

import java.io.File;
import java.io.IOException;

import org.sp.springapp.exception.FileException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

// scan에 의해 자동 인스턴스 생성..
@Component
public class FileManager {
	
	// 확장자 구하기 432432432.jpg
	public static String getExt(String path) {
		int index = path.lastIndexOf(".");
		path.substring(index + 1, path.length()); // exclusive
		return null;
	}
	
	// 최종적인 파일명 만들기
	public static String createFilename(String filename) {
		long time = System.currentTimeMillis();
		
		return time + "." + getExt(filename);
	}
	
	// 파일 저장
	public String save(String path, String filename, MultipartFile mf) throws FileException {
		
		System.out.println(filename);

		// 파일명 만들기
		String newName = FileManager.createFilename(filename);
		
		File file = new File(path +  newName);
		//File file = new File(path + "/dd/" + newName);

		try {
			mf.transferTo(file);
		} catch (IllegalStateException e) {
			throw new FileException("파일저장 실패", e);
		} catch (IOException e) {
			throw new FileException("파일저장 실패", e);
		}
		
		return newName;
	}
}
