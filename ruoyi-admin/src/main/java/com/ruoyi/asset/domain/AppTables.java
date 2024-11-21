package com.ruoyi.asset.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 应用资产对象 app_tables
 * 
 * @author Sven
 * @date 2024-11-20
 */
public class AppTables extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 应用ID */
    @Excel(name = "应用ID")
    private Long appId;

    /** 项目 */
    @Excel(name = "项目")
    private String items;

    /** 分类 */
    @Excel(name = "分类")
    private String sort;

    /** 服务器 */
    private String server;

    /** 网络 */
    private String network;

    /** 域名 */
    @Excel(name = "域名")
    private String domain;

    /** 开发语言 */
    @Excel(name = "开发语言")
    private String pl;

    /** 框架 */
    @Excel(name = "框架")
    private String frame;

    /** 组件 */
    private String element;

    /** 生命周期 */
    @Excel(name = "生命周期")
    private String lifecycle;

    /** 数据中心 */
    @Excel(name = "数据中心")
    private String dataCenter;

    /** 风险评估 */
    @Excel(name = "风险评估")
    private String riskEval;

    /** 风险等级 */
    @Excel(name = "风险等级")
    private String riskLevel;

    /** 访问控制策略 */
    private String secAcl;

    /** 安全控制 */
    private String secSafety;

    public void setAppId(Long appId) 
    {
        this.appId = appId;
    }

    public Long getAppId() 
    {
        return appId;
    }
    public void setItems(String items) 
    {
        this.items = items;
    }

    public String getItems() 
    {
        return items;
    }
    public void setSort(String sort) 
    {
        this.sort = sort;
    }

    public String getSort() 
    {
        return sort;
    }
    public void setServer(String server) 
    {
        this.server = server;
    }

    public String getServer() 
    {
        return server;
    }
    public void setNetwork(String network) 
    {
        this.network = network;
    }

    public String getNetwork() 
    {
        return network;
    }
    public void setDomain(String domain) 
    {
        this.domain = domain;
    }

    public String getDomain() 
    {
        return domain;
    }
    public void setPl(String pl) 
    {
        this.pl = pl;
    }

    public String getPl() 
    {
        return pl;
    }
    public void setFrame(String frame) 
    {
        this.frame = frame;
    }

    public String getFrame() 
    {
        return frame;
    }
    public void setElement(String element) 
    {
        this.element = element;
    }

    public String getElement() 
    {
        return element;
    }
    public void setLifecycle(String lifecycle) 
    {
        this.lifecycle = lifecycle;
    }

    public String getLifecycle() 
    {
        return lifecycle;
    }
    public void setDataCenter(String dataCenter) 
    {
        this.dataCenter = dataCenter;
    }

    public String getDataCenter() 
    {
        return dataCenter;
    }
    public void setRiskEval(String riskEval) 
    {
        this.riskEval = riskEval;
    }

    public String getRiskEval() 
    {
        return riskEval;
    }
    public void setRiskLevel(String riskLevel) 
    {
        this.riskLevel = riskLevel;
    }

    public String getRiskLevel() 
    {
        return riskLevel;
    }
    public void setSecAcl(String secAcl) 
    {
        this.secAcl = secAcl;
    }

    public String getSecAcl() 
    {
        return secAcl;
    }
    public void setSecSafety(String secSafety) 
    {
        this.secSafety = secSafety;
    }

    public String getSecSafety() 
    {
        return secSafety;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("appId", getAppId())
            .append("items", getItems())
            .append("sort", getSort())
            .append("server", getServer())
            .append("network", getNetwork())
            .append("domain", getDomain())
            .append("pl", getPl())
            .append("frame", getFrame())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("element", getElement())
            .append("lifecycle", getLifecycle())
            .append("dataCenter", getDataCenter())
            .append("riskEval", getRiskEval())
            .append("riskLevel", getRiskLevel())
            .append("secAcl", getSecAcl())
            .append("secSafety", getSecSafety())
            .toString();
    }
}
