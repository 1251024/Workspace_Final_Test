package com.mvc.upgrade.model.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.upgrade.model.dao.MYBoardDao;
import com.mvc.upgrade.model.dto.MYBoardDto;


@Service
public class MYBoardBizImpl implements MYBoardBiz {

	@Autowired
	private MYBoardDao dao;
	//프록시객체를 만들때 인터페이스 통해서 만들기때문에 dao인터페이스로 적어줘야한다.
	
	@Override
	public List<MYBoardDto> selectList() {
		return dao.selectList();
	}

	@Override
	public MYBoardDto selectOne(int myno) {
		return dao.selectOne(myno);
	}

	@Override
	public int insert(MYBoardDto dto) {
		return dao.insert(dto);
	}

	@Override
	public int update(MYBoardDto dto) {
		return dao.update(dto);
	}

	@Override
	public int delete(int myno) {
		return dao.delete(myno);
	}
	
	//biz에서 하나의 기능을 하기위해 2개의 dao 호출해서 하나의 작업으로 만들려고 함.
	//@Transactional은 순차적으로 실행할때 위에거 성공하고 아래거 실패시 되돌아간다./ 클래스에도 걸 수 있다.
	
	//두개의 작업은 하나의 작업이라고 묶어준것
	@Transactional
	@Override
	public String test() {
		//성공하면 저장되는데,
		dao.insert(new MYBoardDto(0, "test", "transaction test", "transaction이 뭐였는지?", null));
		
		//여기서 실패하면 롤백
		String test = dao.test();
		test.length();
		
		return test;
	}

}
