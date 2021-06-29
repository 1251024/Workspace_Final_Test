package com.phoenix.carrot.product.biz;

import java.util.List;

import com.phoenix.carrot.product.dto.ProductDto;

public interface UserProductBiz {
	
	//유저상품리스트출력
	public List<ProductDto> userProductList();
	//유저등록상품상세출력
	public ProductDto userProductOne(int productSeq);
	//유저상품등록
	public int userProductInsert(ProductDto dto);
	//유저상품수정
	public int userProductUpdate(ProductDto dto);
	//유저상품삭제
	public int userProductDelete(int productSeq);

}
