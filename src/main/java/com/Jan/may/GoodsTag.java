package com.Jan.may;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GoodsTag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String tagName;
	@Column
	private Integer seq;
	@Column
	private Boolean mainClass;
	@Column
	private Boolean display;
	// @ManyToOne
	// @JoinColumn(name = "tag_id")
	// private GoodsTag tag;
	//
	// @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
	// private List<GoodsTag> subs;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Boolean getMainClass() {
		return mainClass;
	}

	public void setMainClass(Boolean mainClass) {
		this.mainClass = mainClass;
	}

	public Boolean getDisplay() {
		return display;
	}

	public void setDisplay(Boolean display) {
		this.display = display;
	}

	// public GoodsTag getTag() {
	// return tag;
	// }
	//
	// public void setTag(GoodsTag tag) {
	// this.tag = tag;
	// }
	//
	// public List<GoodsTag> getSubs() {
	// return subs;
	// }
	//
	// public void setSubs(List<GoodsTag> subs) {
	// this.subs = subs;
	// }

}
