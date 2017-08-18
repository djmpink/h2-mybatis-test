package cn.teamstack.entity;

import cn.teamstack.common.core.bean.BaseBean;

/**
 * Created by zhouli on 2017/8/15.
 */
public class LogConfig extends BaseBean {

    public Integer id;//主键id(自增)
    public String logId;//业务Id
    public String IP;//日志服务器ip
    public String name;//配置命名
    public String path;//日志文件路径
    public String type;//日志类型
    public String tags;//标签

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
