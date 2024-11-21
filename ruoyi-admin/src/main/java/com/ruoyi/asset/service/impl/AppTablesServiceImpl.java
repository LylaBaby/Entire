package com.ruoyi.asset.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.asset.mapper.AppTablesMapper;
import com.ruoyi.asset.domain.AppTables;
import com.ruoyi.asset.service.IAppTablesService;

/**
 * 应用资产Service业务层处理
 * 
 * @author Sven
 * @date 2024-11-20
 */
@Service
public class AppTablesServiceImpl implements IAppTablesService 
{
    @Autowired
    private AppTablesMapper appTablesMapper;

    /**
     * 查询应用资产
     * 
     * @param appId 应用资产主键
     * @return 应用资产
     */
    @Override
    public AppTables selectAppTablesByAppId(Long appId)
    {
        return appTablesMapper.selectAppTablesByAppId(appId);
    }

    /**
     * 查询应用资产列表
     * 
     * @param appTables 应用资产
     * @return 应用资产
     */
    @Override
    public List<AppTables> selectAppTablesList(AppTables appTables)
    {
        return appTablesMapper.selectAppTablesList(appTables);
    }

    /**
     * 新增应用资产
     * 
     * @param appTables 应用资产
     * @return 结果
     */
    @Override
    public int insertAppTables(AppTables appTables)
    {
        appTables.setCreateTime(DateUtils.getNowDate());
        return appTablesMapper.insertAppTables(appTables);
    }

    /**
     * 修改应用资产
     * 
     * @param appTables 应用资产
     * @return 结果
     */
    @Override
    public int updateAppTables(AppTables appTables)
    {
        appTables.setUpdateTime(DateUtils.getNowDate());
        return appTablesMapper.updateAppTables(appTables);
    }

    /**
     * 批量删除应用资产
     * 
     * @param appIds 需要删除的应用资产主键
     * @return 结果
     */
    @Override
    public int deleteAppTablesByAppIds(Long[] appIds)
    {
        return appTablesMapper.deleteAppTablesByAppIds(appIds);
    }

    /**
     * 删除应用资产信息
     * 
     * @param appId 应用资产主键
     * @return 结果
     */
    @Override
    public int deleteAppTablesByAppId(Long appId)
    {
        return appTablesMapper.deleteAppTablesByAppId(appId);
    }
}
