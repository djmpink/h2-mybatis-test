package cn.teamstack.repository;

import cn.teamstack.dto.request.ConfigReq;
import cn.teamstack.dto.response.ConfigResp;
import cn.teamstack.entity.LogConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhouli on 2017/8/12.
 */
@Mapper
public interface LogMapper {
    LogConfig findById(@Param("logId") String logId);

    void insert(LogConfig logConfig);

    List<LogConfig> find(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    Long count();

    void update(LogConfig logConfig);
}
