package com.Jan.may;

import java.util.Date;

import javax.persistence.*;

/**
 * @author LG0812
 * @JoinColumn(name = "goodsId") 中的name 是 表中生成的外键的名字
 */
@Entity
@Table
public class GoodsSpec {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "goodsId")
	private GoodsInfo goodsInfo;
	@Column
	private String barcode;// 所对应的商品编码
	@Column
	private Date created;// 创建者
	// 长宽高 重量
	@Column
	private Double length;
	@Column
	private Double width;
	@Column
	private Double height;
	@Column
	private Double weight;

	@Column
	private String specCode;// 规格编码
	@Column
	private String specName;// 规格名称

	@Column
	private Double validityDays;// 保质期
	@Column
	private Integer stock;// 库存
	@Column
	private Double retailPrice;// 零售价
	@Column
	private Double preferentialPrice;// 优惠价
	@Column
	private Double cost;// 成本
	@Column
	private Integer salesVolume;// 销售量

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// public GoodsInfo getGoodsInfo() {
	// return goodsInfo;
	// }
	//
	// public void setGoodsInfo(GoodsInfo goodsInfo) {
	// this.goodsInfo = goodsInfo;
	// }

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getSpecCode() {
		return specCode;
	}

	public void setSpecCode(String specCode) {
		this.specCode = specCode;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public Double getValidityDays() {
		return validityDays;
	}

	public void setValidityDays(Double validityDays) {
		this.validityDays = validityDays;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public Double getPreferentialPrice() {
		return preferentialPrice;
	}

	public void setPreferentialPrice(Double preferentialPrice) {
		this.preferentialPrice = preferentialPrice;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Integer getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}

}
