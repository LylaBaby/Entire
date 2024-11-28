package com.ruoyi.asset.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.asset.mapper.ItAssetMapper;
import com.ruoyi.asset.domain.ItAsset;
import com.ruoyi.asset.service.IItAssetService;

/**
 * IT资产Service业务层处理
 * 
 * @author Sven
 * @date 2024-11-27
 */
@Service
public class ItAssetServiceImpl implements IItAssetService 
{
    @Autowired
    private ItAssetMapper itAssetMapper;

    /**
     * 查询IT资产
     * 
     * @param assetId IT资产主键
     * @return IT资产
     */
    @Override
    public ItAsset selectItAssetByAssetId(Long assetId)
    {
        return itAssetMapper.selectItAssetByAssetId(assetId);
    }

    /**
     * 查询IT资产列表
     * 
     * @param itAsset IT资产
     * @return IT资产
     */
    @Override
    public List<ItAsset> selectItAssetList(ItAsset itAsset)
    {
        return itAssetMapper.selectItAssetList(itAsset);
    }

    /**
     * 新增IT资产
     * 
     * @param itAsset IT资产
     * @return 结果
     */
    @Override
    public int insertItAsset(ItAsset itAsset)
    {
        itAsset.setCreateTime(DateUtils.getNowDate());
        return itAssetMapper.insertItAsset(itAsset);
    }

    /**
     * 修改IT资产
     * 
     * @param itAsset IT资产
     * @return 结果
     */
    @Override
    public int updateItAsset(ItAsset itAsset)
    {
        itAsset.setUpdateTime(DateUtils.getNowDate());
        return itAssetMapper.updateItAsset(itAsset);
    }

    /**
     * 批量删除IT资产
     * 
     * @param assetIds 需要删除的IT资产主键
     * @return 结果
     */
    @Override
    public int deleteItAssetByAssetIds(Long[] assetIds)
    {
        return itAssetMapper.deleteItAssetByAssetIds(assetIds);
    }

    /**
     * 删除IT资产信息
     * 
     * @param assetId IT资产主键
     * @return 结果
     */
    @Override
    public int deleteItAssetByAssetId(Long assetId)
    {
        return itAssetMapper.deleteItAssetByAssetId(assetId);
    }
}
