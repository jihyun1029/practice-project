package org.sp.springapp.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.sp.springapp.domain.Gallery;
import org.sp.springapp.domain.GalleryImg;
import org.sp.springapp.util.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import sp.org.springapp.model.gallery.GalleryDAO;
import sp.org.springapp.model.gallery.GalleryImgDAO;
import sp.org.springapp.model.gallery.GalleryService;

// 갤러리와 관련된 요청을 처리하는 하위 컨트롤러
@Controller
public class GalleryController {
	
	// 컨트롤러가 직접 DAO를 다루게 되면, 트랜잭션 처리까지 부담한다거나,
	// 모델 part 업무를 너무 전문적으로 처리하게 된다..
	// 컨트롤러와 모델의 업무 경계가 모호해 지므로, 즉 코드의 분리가 안되므로 추후 비슷한 업무시
	// 코드를 분리해 놓지 않았기 때문에 코드의 재사용성이 떨어진다..
	@Autowired
	private GalleryService galleryService;
	
	// DI 를 이용하여, 느슨하게 보유해야 한다
	@Autowired
	private GalleryDAO galleryDAO;
	
	@Autowired
	private GalleryImgDAO galleryImgDAO;
	
	@Autowired
	private FileManager fileManager;

	// 게시판 목록 요청 처리
	@RequestMapping(value = "/gallery/list", method = RequestMethod.GET)
	public ModelAndView getList() {
		// 3단계 : 일 시키기

		// 4단계 : 목록저장
		ModelAndView mav = new ModelAndView("gallery/list");

		return mav;
	}

	// 글쓰기 폼 요청
	@RequestMapping(value = "/gallery/registform", method = RequestMethod.GET)
	public String getForm() {

		return "gallery/regist";
	}

	// 글쓰기 요청 처리
	@RequestMapping(value = "/gallery/regist", method = RequestMethod.POST)
	public ModelAndView regist(Gallery gallery, HttpServletRequest request) {
		// 3단계 : 오라클에 글등록 + 파일업로드 +
		//System.out.println("title = " + gallery.getTitle());
		//System.out.println("title = " + gallery.getWriter());
		//System.out.println("title = " + gallery.getContent());

		MultipartFile[] photo = gallery.getPhoto();
		System.out.println("넘겨받은 파일의 수는 " + gallery.getPhoto().length);

		// jsp의 application 내장객체는 서블릿 api에서 ServletContext이다
		// 따라서 이 객체를 얻기 우해 HttpSession을 얻어야 한다
		ServletContext context = request.getSession().getServletContext();
		String path = context.getRealPath("/resoureces/data/");
		System.out.println("파일이 저장될 풀 경로는 " + path);
		
		List<String> imgList = new ArraryList<String>(); // 새롭게 생성한 파일명이 누적될 곳
		
		for (int i = 0; i < photo.length; i++) {
			String filename = photo[i].getOriginalFilename();
			String name = fileManager.save(path, filename, photo[i]);
			
			GalleryImg galleryImg = new GalleryImg(); // empty
			galleryImg.setGallery(gallery); // 이 시점의 gallery DTO에는 아직, gallery_idx는 0인 상태
			galleryImg.setFilename(filename);
			
			imgList.add(galleryImg);
		}
		
		// Gallery DTO에 GalleryImg 들을 생성하여 List로 넣어두기
		gallery.setGalleryImgList(imgList);
		galleryService.regist(gallery); // 글 등록 요청
		
		
		return null;
	}
}
