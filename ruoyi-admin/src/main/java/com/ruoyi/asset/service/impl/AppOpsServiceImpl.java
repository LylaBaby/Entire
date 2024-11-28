package com.ruoyi.asset.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.asset.mapper.AppOpsMapper;
import com.ruoyi.asset.domain.AppOps;
import com.ruoyi.asset.service.IAppOpsService;

/**
 * 系统运维Service业务层处理
 * 
 * @author Sven
 * @date 2024-11-27
 */
@Service
public class AppOpsServiceImpl implements IAppOpsService 
{
    @Autowired
    private AppOpsMapper appOpsMapper;

    /**
     * 查询系统运维
     * 
     * @param opsId 系统运维主键
     * @return 系统运维
     */
    @Override
    public AppOps selectAppOpsByOpsId(Long opsId)
    {
        return appOpsMapper.selectAppOpsByOpsId(opsId);
    }

    /**
     * 查询系统运维列表
     * 
     * @param appOps 系统运维
     * @return 系统运维
     */
    @Override
    public List<AppOps> selectAppOpsList(AppOps appOps)
    {
        return appOpsMapper.selectAppOpsList(appOps);
    }

    /**
     * 新增系统运维
     * 
     * @param appOps 系统运维
     * @return 结果
     */
    @Override
    public int insertAppOps(AppOps appOps)
    {
        return appOpsMapper.insertAppOps(appOps);
    }

    /**
     * 修改系统运维
     * 
     * @param appOps 系统运维
     * @return 结果
     */
    @Override
    public int updateAppOps(AppOps appOps)
    {
        return appOpsMapper.updateAppOps(appOps);
    }

    /**
     * 批量删除系统运维
     * 
     * @param opsIds 需要删除的系统运维主键
     * @return 结果
     */
    @Override
    public int deleteAppOpsByOpsIds(Long[] opsIds)
    {
        return appOpsMapper.deleteAppOpsByOpsIds(opsIds);
    }

    /**
     * 删除系统运维信息
     * 
     * @param opsId 系统运维主键
     * @return 结果
     */
    @Override
    public int deleteAppOpsByOpsId(Long opsId)
    {
        return appOpsMapper.deleteAppOpsByOpsId(opsId);
    }
}
