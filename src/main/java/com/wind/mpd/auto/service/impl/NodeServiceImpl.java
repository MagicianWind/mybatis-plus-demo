package com.wind.mpd.auto.service.impl;

import com.wind.mpd.auto.entity.Node;
import com.wind.mpd.auto.mapper.NodeMapper;
import com.wind.mpd.auto.service.INodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lxz
 * @since 2021-12-13
 */
@Service
public class NodeServiceImpl extends ServiceImpl<NodeMapper, Node> implements INodeService {

}
