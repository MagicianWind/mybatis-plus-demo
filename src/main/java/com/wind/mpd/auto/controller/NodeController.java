package com.wind.mpd.auto.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.wind.mpd.auto.entity.Node;
import com.wind.mpd.auto.service.INodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lxz
 * @since 2021-12-13
 */
@RestController
@RequestMapping("/node")
public class NodeController {

    @Autowired
    private INodeService nodeService;

    @GetMapping("/list/{parentId}")
    public List<Node> getNodeList(@PathVariable Long parentId) {
        List<Node> totalNodes = nodeService.list();
        if (parentId == 0) {
            return totalNodes;
        }
        return getList(parentId, totalNodes);
    }

    @GetMapping("/tree/{parentId}")
    public List<Node> getNodeTree(@PathVariable Long parentId) {
        List<Node> totalNodes = nodeService.list();
        List<Node> list;
        if (parentId == 0) {
            list = totalNodes.stream().filter(e -> parentId.equals(e.getParentId())).collect(Collectors.toList());
        } else {
            list = totalNodes.stream().filter(e -> parentId.equals(e.getId())).collect(Collectors.toList());
        }
        return getTree(list, totalNodes);
    }

    // 根据parentId获取树形结构数据
    public List<Node> getTree(List<Node> list, List<Node> totalNodes) {
        return treeRecursion(list, totalNodes);
    }

    // 递归获取节点ID作为父节点ID进行查询
    public List<Node> treeRecursion(List<Node> parentNodes, List<Node> totalNodes) {
        parentNodes.stream().forEach(e -> {
            List<Node> subList = totalNodes.stream().filter(x -> x.getParentId().equals(e.getId())).collect(Collectors.toList());
            e.setChildNodes(subList);
            if (CollectionUtils.isNotEmpty(subList)) {
                treeRecursion(subList, totalNodes);
            }
        });
        return parentNodes;
    }

    // 根据parentId获取列表结构数据
    public List<Node> getList(Long parentId, List<Node> totalNodes) {
        List<Node> result = new ArrayList<>();
        List<Node> startNode = totalNodes.stream().filter(e -> parentId.equals(e.getId())).collect(Collectors.toList());
        result.addAll(startNode);
        return flatRecursion(result, Collections.singletonList(parentId), totalNodes);
    }

    // 递归获取节点ID作为父节点ID进行查询
    public List<Node> flatRecursion(List<Node> result, List<Long> parentIds, List<Node> totalNodes) {
        List<Node> subList = totalNodes.stream().filter(e -> parentIds.contains(e.getParentId())).collect(Collectors.toList());
        result.addAll(subList);
        List<Long> subIds = subList.stream().map(Node::getId).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(subIds)) {
            flatRecursion(result, subIds, totalNodes);
        }
        return result;
    }
}
