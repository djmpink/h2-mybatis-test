package cn.teamstack.service;

import cn.teamstack.entity.DemoEntity;
import cn.teamstack.repository.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouli on 2017/8/12.
 */
@Service
public class DemoService {

    @Autowired
    private DemoMapper demoMapper;

    public DemoEntity getById(String id) {
        DemoEntity demoEntity =demoMapper.findById(id);
        return demoEntity;
    }

    public DemoEntity add(DemoEntity demoEntity) {
        demoMapper.insert(demoEntity);
        return demoEntity;
    }
}
