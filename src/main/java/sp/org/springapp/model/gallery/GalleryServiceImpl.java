package sp.org.springapp.model.gallery;

import java.util.List;

import org.sp.springapp.domain.Gallery;
import org.sp.springapp.domain.GalleryImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
// 서비스 인터페이스를 구현한 하위 서비스 객체
public class GalleryServiceImpl implements GalleryService {
	
	// DAO는 서비스에서 보유해야 한다..model 영역의 업무이므로,
	// 따라서 두 개 이상의 테이블에 대한 DML 상황에 있어, 트랜잭션 처리 또한 서비스 객체의 몫이다..
	@Autowired
	private GalleryDAO galleryDAO;
	
	@Autowired
	private GalleryImgDAO galleryImgDAO;
	
	@Override
	public void regist(Gallery gallery) {
		// 두개의 dao를 이용하여 글 등록 업무처리..
		galleryDAO.insert(gallery); // mybatis 에 의해 gallery_idx
		
		List<GalleryImg> imgList = gallery.getGalleryImgList();
		
		for(int i=0; i<imgList.size(); i++) {
			GalleryImg galleryImg = imgList.get(i);
			
			galleryImgDAO.insert(galleryImg); // 이미지 테이블에 insert
		}
	}

	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gallery select(int gallery_idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Gallery gallery) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int gallery_idx) {
		// TODO Auto-generated method stub
		
	}
	
}
