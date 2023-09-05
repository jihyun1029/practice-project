package org.sp.springapp.controller;

import org.sp.springapp.domain.Gallery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

// 갤러리와 관련된 요청을 처리하는 하위 컨트롤러
@Controller
public class GalleryController {
	
	// 게시판 목록 요청 처리
	@RequestMapping(value="/gallery/list", method=RequestMethod.GET)
	public ModelAndView getList() {
		// 3단계 : 일 시키기
		
		// 4단계 : 목록저장
		ModelAndView mav = new ModelAndView("gallery/list");
		
		return null;
	}
	
	// 글쓰기 폼 요청
	@RequestMapping(value="/gallery/registform", method=RequestMethod.GET)
	public String getForm() {
		
		return "gallery/regist";
	}
	
	// 글쓰기 요청 처리
	@RequestMapping(value="/gallery/regist", method=RequestMethod.POST)
	public ModelAndView regist(Gallery gallery) {
		// 3단계 : 오라클에 글등록 + 파일업로드 
		System.out.println("title = " + gallery.getTitle());
		System.out.println("writer = " + gallery.getWriter());
		System.out.println("content = " + gallery.getContent());
		
		// 글쓰기 list를 재요청
		
		return null;
	}
}
