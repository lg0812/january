package com.Jan.may;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Recommend {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	Long recommendGoodsId;
	@ManyToOne
	@JoinColumn(name = "goods_id")
	GoodsInfo goodsInfo;

	public Long getRecommendGoodsId() {
		return recommendGoodsId;
	}

	public void setRecommendGoodsId(Long recommendGoodsId) {
		this.recommendGoodsId = recommendGoodsId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
