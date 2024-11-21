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
import com.ruoyi.asset.domain.AppOps;
import com.ruoyi.asset.service.IAppOpsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 系统运维Controller
 * 
 * @author Sven
 * @date 2024-11-20
 */
@RestController
@RequestMapping("/asset/ops")
public class AppOpsController extends BaseController
{
    @Autowired
    private IAppOpsService appOpsService;

    /**
     * 查询系统运维列表
     */
    @PreAuthorize("@ss.hasPermi('asset:ops:list')")
    @GetMapping("/list")
    public TableDataInfo list(AppOps appOps)
    {
        startPage();
        List<AppOps> list = appOpsService.selectAppOpsList(appOps);
        return getDataTable(list);
    }

    /**
     * 导出系统运维列表
     */
    @PreAuthorize("@ss.hasPermi('asset:ops:export')")
    @Log(title = "系统运维", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AppOps appOps)
    {
        List<AppOps> list = appOpsService.selectAppOpsList(appOps);
        ExcelUtil<AppOps> util = new ExcelUtil<AppOps>(AppOps.class);
        util.exportExcel(response, list, "系统运维数据");
    }

    /**
     * 获取系统运维详细信息
     */
    @PreAuthorize("@ss.hasPermi('asset:ops:query')")
    @GetMapping(value = "/{appItems}")
    public AjaxResult getInfo(@PathVariable("appItems") String appItems)
    {
        return success(appOpsService.selectAppOpsByAppItems(appItems));
    }

    /**
     * 新增系统运维
     */
    @PreAuthorize("@ss.hasPermi('asset:ops:add')")
    @Log(title = "系统运维", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AppOps appOps)
    {
        return toAjax(appOpsService.insertAppOps(appOps));
    }

    /**
     * 修改系统运维
     */
    @PreAuthorize("@ss.hasPermi('asset:ops:edit')")
    @Log(title = "系统运维", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AppOps appOps)
    {
        return toAjax(appOpsService.updateAppOps(appOps));
    }

    /**
     * 删除系统运维
     */
    @PreAuthorize("@ss.hasPermi('asset:ops:remove')")
    @Log(title = "系统运维", businessType = BusinessType.DELETE)
	@DeleteMapping("/{appItemss}")
    public AjaxResult remove(@PathVariable String[] appItemss)
    {
        return toAjax(appOpsService.deleteAppOpsByAppItemss(appItemss));
    }
}
