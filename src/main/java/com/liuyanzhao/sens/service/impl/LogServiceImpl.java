package com.liuyanzhao.sens.service.impl;

import com.liuyanzhao.sens.entity.Log;
import com.liuyanzhao.sens.mapper.db1.LogMapper;
import com.liuyanzhao.sens.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 言曌
 * @date 2019-08-19 21:51
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public Integer insert(Log log) {
        return logMapper.insert(log);
    }
}
