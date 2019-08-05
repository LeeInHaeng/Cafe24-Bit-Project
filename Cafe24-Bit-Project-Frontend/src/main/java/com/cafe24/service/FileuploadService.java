package com.cafe24.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class FileuploadService {
	
	// 로컬에 저장될 파일 경로
	private static final String SAVE_PATH = "/cafe24mall-uploads";
	// URL에 생성할 경로
	private static final String URL = "/images";
	
	public Map<String, Object> restore(MultipartHttpServletRequest request) {
		String url = "";
		Map<String, Object> result = new HashMap<String,Object>();
		
		MultipartFile repImage = request.getFile("repImage");
		List<MultipartFile> addImage = request.getFiles("addImage[]");
		
		if(repImage==null && addImage.size()==0) {
			return null;
		}
		
		if(repImage!=null) {
			String repImageOriginalFilename = repImage.getOriginalFilename();
			String repImageExtName = repImageOriginalFilename.substring(repImageOriginalFilename.lastIndexOf('.')+1);
			String repImageSaveFileName = generateSaveFileName(repImageExtName);
			
			try {
				byte[] fileData = repImage.getBytes();
				OutputStream os = new FileOutputStream(SAVE_PATH + "/" + repImageSaveFileName);
				os.write(fileData);
				os.close();
				
				url = URL + "/" + repImageSaveFileName;
				result.put("repImage", url);
			} catch (IOException e) {
				throw new RuntimeException("Fileupload error : " + e);
			}
		}
		
		if(addImage.size()!=0) {
			List<String> addImageUrls = new ArrayList<String>();
			for(MultipartFile addImageFile : addImage) {
				String addImageOriginalFilename = addImageFile.getOriginalFilename();
				String addImageExtName = addImageOriginalFilename.substring(addImageOriginalFilename.lastIndexOf('.')+1);
				String addImageSaveFileName = generateSaveFileName(addImageExtName);
				
				try {
					byte[] fileData = addImageFile.getBytes();
					OutputStream os = new FileOutputStream(SAVE_PATH + "/" + addImageSaveFileName);
					os.write(fileData);
					os.close();
					
					url = URL + "/" + addImageSaveFileName;
					addImageUrls.add(url);
				} catch (IOException e) {
					throw new RuntimeException("Fileupload error : " + e);
				}
			}
			
			result.put("addImage", addImageUrls);
		}
		
		return result;
	}
	
	private String generateSaveFileName(String extName) {
		String filename = "";
		
		Calendar calendar = Calendar.getInstance();
		
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		
		return filename;
	}
}