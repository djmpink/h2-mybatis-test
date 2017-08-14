package cn.teamstack.repository;

import cn.teamstack.entity.DemoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zhouli on 2017/8/12.
 */
@Mapper
public interface DemoMapper {
    DemoEntity findById(@Param("id") String id);

    void insert(@Param("demo") DemoEntity demoEntity);
}
