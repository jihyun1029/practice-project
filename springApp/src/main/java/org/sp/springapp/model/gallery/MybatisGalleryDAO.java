package org.sp.springapp.model.gallery;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.sp.springapp.domain.Gallery;
import org.sp.springapp.exception.GalleryException;
import org.sp.springapp.mybatis.MybatisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisGalleryDAO implements GalleryDAO {
	
	@Autowired
	private MybatisConfig mybatisConfig;
	
	/*
	public void setMybatisConfig(MybatisConfig mybatisConfig) {
		MybatisConfig = mybatisConfig;
	}
	*/

	@Override
	public void insert(Gallery gallery) throws GalleryException {
		SqlSession sqlSession = mybatisConfig.getSqlSession();
		
		int result = sqlSession.insert("Gallery.insert", gallery);
		sqlSession.commit(); // DML인 경우..
		mybatisConfig.release(sqlSession);
		
		//result = 0; // 일부러 에러 테스트
		
		if(result == 0) {
			// 개발자가 일부러 관련있는 에러를 일으키자
			throw new GalleryException("갤러리 등록을 실패했어요");
		}
	}

	@Override
	public List selectAll() {
		SqlSession sqlSession = mybatisConfig.getSqlSession();
		List list = sqlSession.selectList("Gallery.selectAll");
		mybatisConfig.release(sqlSession);
		
		return list;
	}

	@Override
	public Gallery select(int gallery_idx) {
		SqlSession sqlSession = mybatisConfig.getSqlSession();
		Gallery gallery = sqlSession.selectOne("Gallery.select", gallery_idx);
		mybatisConfig.release(sqlSession);
		return null;
	}

	@Override
	public void update(Gallery allery) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int gallery_idx) {
		// TODO Auto-generated method stub
		
	}

}
