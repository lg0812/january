package com.Jan.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class UserToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long accessTokenId;
    @Column
    public String accessToken = "";
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    public Date updateTime = new Date();

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getAccessTokenId() {
        return accessTokenId;
    }

    public void setAccessTokenId(long accessTokenId) {
        this.accessTokenId = accessTokenId;
    }
}
