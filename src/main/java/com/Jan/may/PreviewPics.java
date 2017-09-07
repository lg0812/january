package com.Jan.may;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class PreviewPics {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String pictureName;// 图片名称
	String picturePath;// 图片路径
	Boolean status;//

	@ManyToOne
	@JoinColumn(name = "goodsId")
	GoodsInfo goodsInfo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

//	public GoodsInfo getGoodsInfo() {
//		return goodsInfo;
//	}
//
//	public void setGoodsInfo(GoodsInfo goodsInfo) {
//		this.goodsInfo = goodsInfo;
//	}

}
