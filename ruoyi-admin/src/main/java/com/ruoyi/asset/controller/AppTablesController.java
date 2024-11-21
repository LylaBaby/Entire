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
import com.ruoyi.asset.domain.AppTables;
import com.ruoyi.asset.service.IAppTablesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 应用资产Controller
 * 
 * @author Sven
 * @date 2024-11-20
 */
@RestController
@RequestMapping("/asset/appasset")
public class AppTablesController extends BaseController
{
    @Autowired
    private IAppTablesService appTablesService;

    /**
     * 查询应用资产列表
     */
    @PreAuthorize("@ss.hasPermi('asset:appasset:list')")
    @GetMapping("/list")
    public TableDataInfo list(AppTables appTables)
    {
        startPage();
        List<AppTables> list = appTablesService.selectAppTablesList(appTables);
        return getDataTable(list);
    }

    /**
     * 导出应用资产列表
     */
    @PreAuthorize("@ss.hasPermi('asset:appasset:export')")
    @Log(title = "应用资产", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AppTables appTables)
    {
        List<AppTables> list = appTablesService.selectAppTablesList(appTables);
        ExcelUtil<AppTables> util = new ExcelUtil<AppTables>(AppTables.class);
        util.exportExcel(response, list, "应用资产数据");
    }

    /**
     * 获取应用资产详细信息
     */
    @PreAuthorize("@ss.hasPermi('asset:appasset:query')")
    @GetMapping(value = "/{appId}")
    public AjaxResult getInfo(@PathVariable("appId") Long appId)
    {
        return success(appTablesService.selectAppTablesByAppId(appId));
    }

    /**
     * 新增应用资产
     */
    @PreAuthorize("@ss.hasPermi('asset:appasset:add')")
    @Log(title = "应用资产", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AppTables appTables)
    {
        return toAjax(appTablesService.insertAppTables(appTables));
    }

    /**
     * 修改应用资产
     */
    @PreAuthorize("@ss.hasPermi('asset:appasset:edit')")
    @Log(title = "应用资产", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AppTables appTables)
    {
        return toAjax(appTablesService.updateAppTables(appTables));
    }

    /**
     * 删除应用资产
     */
    @PreAuthorize("@ss.hasPermi('asset:appasset:remove')")
    @Log(title = "应用资产", businessType = BusinessType.DELETE)
	@DeleteMapping("/{appIds}")
    public AjaxResult remove(@PathVariable Long[] appIds)
    {
        return toAjax(appTablesService.deleteAppTablesByAppIds(appIds));
    }
}
