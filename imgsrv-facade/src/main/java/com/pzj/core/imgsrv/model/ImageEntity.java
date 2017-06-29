package com.pzj.core.imgsrv.model;

import java.io.Serializable;

public class ImageEntity implements Serializable {

    private static final long serialVersionUID = 6955138268366094373L;

    // id
    private Integer           id;
    // 创建时间
    private String            createTime;
    // 图片字节流
    private byte[]            image;
    // MD5加密名称
    private String            md5name;
    // 名称
    private String            name;
    // 后缀
    private String            extendsion;
    // 相对路径
    private String            path;
    //状态
    private int               status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getMd5name() {
        return md5name;
    }

    public void setMd5name(String md5name) {
        this.md5name = md5name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtendsion() {
        return extendsion;
    }

    public void setExtendsion(String extendsion) {
        this.extendsion = extendsion;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}