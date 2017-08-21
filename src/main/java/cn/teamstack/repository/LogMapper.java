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
    LogConfig findById(@Param("id") Integer id);

    void insert(@Param("logConfig") LogConfig logConfig);

    List<LogConfig> find(@Param("configReq") ConfigReq configReq);

    Long count();
}
