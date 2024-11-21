package com.ruoyi.asset.service;

import java.util.List;
import com.ruoyi.asset.domain.AppOps;

/**
 * 系统运维Service接口
 * 
 * @author Sven
 * @date 2024-11-20
 */
public interface IAppOpsService 
{
    /**
     * 查询系统运维
     * 
     * @param appItems 系统运维主键
     * @return 系统运维
     */
    public AppOps selectAppOpsByAppItems(String appItems);

    /**
     * 查询系统运维列表
     * 
     * @param appOps 系统运维
     * @return 系统运维集合
     */
    public List<AppOps> selectAppOpsList(AppOps appOps);

    /**
     * 新增系统运维
     * 
     * @param appOps 系统运维
     * @return 结果
     */
    public int insertAppOps(AppOps appOps);

    /**
     * 修改系统运维
     * 
     * @param appOps 系统运维
     * @return 结果
     */
    public int updateAppOps(AppOps appOps);

    /**
     * 批量删除系统运维
     * 
     * @param appItemss 需要删除的系统运维主键集合
     * @return 结果
     */
    public int deleteAppOpsByAppItemss(String[] appItemss);

    /**
     * 删除系统运维信息
     * 
     * @param appItems 系统运维主键
     * @return 结果
     */
    public int deleteAppOpsByAppItems(String appItems);
}
