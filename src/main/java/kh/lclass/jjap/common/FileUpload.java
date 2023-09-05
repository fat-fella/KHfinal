package kh.lclass.jjap.common;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUpload {
	public Map<String, String> savaFile(MultipartFile multi, HttpServletRequest req) throws Exception {
		Map<String, String> result = null;
		String renameFilePath = null;
		String renameByTime = null;
		
		String realPath = req.getSession().getServletContext().getRealPath("");
		String savePath = "resources/upload";
		if(multi != null && !multi.equals("")) {
			result = new HashMap<String, String>();
			
			String orginalFileName = multi.getOriginalFilename();

			File folder = new File(savePath); // 저장할 폴더 생성
			if(!folder.exists()) {
				folder.mkdirs();
			} 
						
			renameByTime = System.currentTimeMillis() + "_"+orginalFileName; // 시간을 활용한 rename
			renameFilePath = savePath + "\\" + renameByTime; // 시간을 활용한 rename
			multi.transferTo(new File(savePath + "\\" + renameByTime)); // 파일을 savePath 위치에 저장
			result.put("original", orginalFileName);
			result.put("rename", renameByTime);
		}
		return result;
	}
}
