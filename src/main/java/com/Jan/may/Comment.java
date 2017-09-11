package com.Jan.may;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	@Column
	private String comment;
	@Column
	private String userName;
	@Transient
	@Column
	private String userLogoName;
	@Column
	private String userLogo;
	@Column
	private Date createDate;
	@OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CloudPath> commentPictures;
	@Column
	private String shopkeeperReply;

	@ManyToOne
	@JoinColumn(name = "goods_id")
	private GoodsInfo goodsInfo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserLogoName() {
		return userLogoName;
	}

	public void setUserLogoName(String userLogoName) {
		this.userLogoName = userLogoName;
	}

	public String getUserLogo() {
		return userLogo;
	}

	public void setUserLogo(String userLogo) {
		this.userLogo = userLogo;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<CloudPath> getCommentPictures() {
		return commentPictures;
	}

	public void setCommentPictures(List<CloudPath> commentPictures) {
		this.commentPictures = commentPictures;
	}

	public String getShopkeeperReply() {
		return shopkeeperReply;
	}

	public void setShopkeeperReply(String shopkeeperReply) {
		this.shopkeeperReply = shopkeeperReply;
	}

}
