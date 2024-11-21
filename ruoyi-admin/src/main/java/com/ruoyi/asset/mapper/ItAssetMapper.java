package com.ruoyi.asset.mapper;

import java.util.List;
import com.ruoyi.asset.domain.ItAsset;

/**
 * IT资产Mapper接口
 * 
 * @author Sven
 * @date 2024-11-20
 */
public interface ItAssetMapper 
{
    /**
     * 查询IT资产
     * 
     * @param assetId IT资产主键
     * @return IT资产
     */
    public ItAsset selectItAssetByAssetId(Long assetId);

    /**
     * 查询IT资产列表
     * 
     * @param itAsset IT资产
     * @return IT资产集合
     */
    public List<ItAsset> selectItAssetList(ItAsset itAsset);

    /**
     * 新增IT资产
     * 
     * @param itAsset IT资产
     * @return 结果
     */
    public int insertItAsset(ItAsset itAsset);

    /**
     * 修改IT资产
     * 
     * @param itAsset IT资产
     * @return 结果
     */
    public int updateItAsset(ItAsset itAsset);

    /**
     * 删除IT资产
     * 
     * @param assetId IT资产主键
     * @return 结果
     */
    public int deleteItAssetByAssetId(Long assetId);

    /**
     * 批量删除IT资产
     * 
     * @param assetIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteItAssetByAssetIds(Long[] assetIds);
}
