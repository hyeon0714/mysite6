package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.AttachDao;
import com.javaex.vo.AttachVo;

@Service
public class AttachService {
	
	@Autowired
	private AttachDao ad;
	
	public String exeUpload(MultipartFile file) {
		
		System.out.println("file service");
		
		//파일저장 폴더
		String saveDir = "C:\\자바study\\upload";
		/*
		//C:\자바study\ upload\+saveName 자바에서 \특수기호를 사용하려면 \\처럼 2개를 붙여야한다
		*/
		//오리지날 파일명
		String orgName = file.getOriginalFilename();
		System.out.println(orgName);
		
		//확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println(exName);
		System.out.println(orgName.substring(4));			//앞에서부터 해당번호부터의 문자를 출력한다
		System.out.println(orgName.lastIndexOf("."));		//확장자명의 .의 위치를 찾는다
		
		//저장파일명(겹치지않아야한다)
		String saveName = System.currentTimeMillis()+UUID.randomUUID().toString()+exName;
		//System.currentTimeMillis() = 현재의 시간표시
		//UUID.randomUUID().toString() = 중복을 방지하기위해서 random 을 사용
		//확장자명을 가져와서 저장
		System.out.println(saveName);
		
		//파일사이즈
		long fileSize = file.getSize();	//단위는 바이트(바이트단위라서 숫자라 클수 있으므로 long형을 사용한다
		System.out.println(fileSize);
		
		//파일전체경로(저장파일명 포함)
		//C:\자바study\ upload\+saveName\+saveName  ->c드라이브 자바스터디폴더 업로드폴더에 저장
		String filePath = saveDir+"\\"+saveName;
		System.out.println(filePath);
		
		//여기까지 파일정보를 db에 저장
		//vo로 묶어서 db에 저장
		AttachVo atVo = new AttachVo(orgName, saveName, filePath, fileSize);
		System.out.println(atVo.toString());
		
		
		ad.upload(atVo);
		
		//파일을 하드에 저장	=> 파일은 db가 아닌 하드에 저장한다
		//파일저장
		try {
			byte[] fileDate = file.getBytes();
			
			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);//저장속도를 위해 버퍼사용
			bos.write(fileDate);
			bos.close();
			
		} catch (IOException e) {	//output 작업이라서 IOException 사용 
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("에러");
		}
		
		return saveName;
		
	}
}
