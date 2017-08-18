package cn.teamstack.common.core.bean;


import java.io.Serializable;
import java.util.Date;

public class BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    public String createUserId;//创建者
    public String modifyUserId;//最后修改者

    public Date createTime;//创建时间
    public Date modifyTime;//修改时间
    public Boolean isValid;//有效标识

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
