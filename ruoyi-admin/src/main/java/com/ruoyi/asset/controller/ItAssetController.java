package com.ruoyi.asset.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.asset.domain.ItAsset;
import com.ruoyi.asset.service.IItAssetService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * IT资产Controller
 * 
 * @author Sven
 * @date 2024-11-27
 */
@RestController
@RequestMapping("/asset/itasset")
public class ItAssetController extends BaseController
{
    @Autowired
    private IItAssetService itAssetService;

    /**
     * 查询IT资产列表
     */
    @PreAuthorize("@ss.hasPermi('asset:itasset:list')")
    @GetMapping("/list")
    public TableDataInfo list(ItAsset itAsset)
    {
        startPage();
        List<ItAsset> list = itAssetService.selectItAssetList(itAsset);
        return getDataTable(list);
    }

    /**
     * 导出IT资产列表
     */
    @PreAuthorize("@ss.hasPermi('asset:itasset:export')")
    @Log(title = "IT资产", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ItAsset itAsset)
    {
        List<ItAsset> list = itAssetService.selectItAssetList(itAsset);
        ExcelUtil<ItAsset> util = new ExcelUtil<ItAsset>(ItAsset.class);
        util.exportExcel(response, list, "IT资产数据");
    }

    /**
     * 获取IT资产详细信息
     */
    @PreAuthorize("@ss.hasPermi('asset:itasset:query')")
    @GetMapping(value = "/{assetId}")
    public AjaxResult getInfo(@PathVariable("assetId") Long assetId)
    {
        return success(itAssetService.selectItAssetByAssetId(assetId));
    }

    /**
     * 新增IT资产
     */
    @PreAuthorize("@ss.hasPermi('asset:itasset:add')")
    @Log(title = "IT资产", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ItAsset itAsset)
    {
        return toAjax(itAssetService.insertItAsset(itAsset));
    }

    /**
     * 修改IT资产
     */
    @PreAuthorize("@ss.hasPermi('asset:itasset:edit')")
    @Log(title = "IT资产", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ItAsset itAsset)
    {
        return toAjax(itAssetService.updateItAsset(itAsset));
    }

    /**
     * 删除IT资产
     */
    @PreAuthorize("@ss.hasPermi('asset:itasset:remove')")
    @Log(title = "IT资产", businessType = BusinessType.DELETE)
	@DeleteMapping("/{assetIds}")
    public AjaxResult remove(@PathVariable Long[] assetIds)
    {
        return toAjax(itAssetService.deleteItAssetByAssetIds(assetIds));
    }
}
