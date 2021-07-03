package com.phoenix.carrot.product.dao;

import java.util.List;

import com.phoenix.carrot.product.dto.ProductDto;



public interface UserProductDao {
	
	String NAMESPACE = "userproduct.";
	//�쑀���긽�뭹由ъ뒪�듃異쒕젰
	public List<ProductDto> userProductList();
	//�쑀���벑濡앹긽�뭹�긽�꽭異쒕젰
	public ProductDto userProductOne(int productSeq);
	//�쑀���긽�뭹�벑濡�
	public int userProductInsert(ProductDto dto);
	//�쑀���긽�뭹�닔�젙
	public int userProductUpdate(ProductDto dto);
	//�쑀���긽�뭹�궘�젣
	public int userProductDelete(int productSeq);
	
	// 주로소 상품 검색
	public List<ProductDto> selectByaddr(String addr);

}
