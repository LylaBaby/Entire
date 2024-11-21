package com.ruoyi.asset.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * IT资产对象 it_asset
 * 
 * @author Sven
 * @date 2024-11-20
 */
public class ItAsset extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 资产ID */
    @Excel(name = "资产ID")
    private Long assetId;

    /** 资产分类 */
    @Excel(name = "资产分类")
    private String assetSort;

    /** 机器地址 */
    @Excel(name = "机器地址")
    private String assetAddr;

    /** 厂商 */
    @Excel(name = "厂商")
    private String manufacturer;

    /** 型号 */
    @Excel(name = "型号")
    private String modelNum;

    /** SN码 */
    @Excel(name = "SN码")
    private String snCode;

    /** IP地址 */
    @Excel(name = "IP地址")
    private String ipAddr;

    /** 登陆方式 */
    private String loginMode;

    /** 配置 */
    private String specs;

    /** 虚拟化 */
    @Excel(name = "虚拟化")
    private String virtual;

    /** 资产编号 */
    @Excel(name = "资产编号")
    private String assetCode;

    /** 资产状态 */
    @Excel(name = "资产状态")
    private String assetStatus;

    /** 资产用户 */
    @Excel(name = "资产用户")
    private String assetUser;

    public void setAssetId(Long assetId) 
    {
        this.assetId = assetId;
    }

    public Long getAssetId() 
    {
        return assetId;
    }
    public void setAssetSort(String assetSort) 
    {
        this.assetSort = assetSort;
    }

    public String getAssetSort() 
    {
        return assetSort;
    }
    public void setAssetAddr(String assetAddr) 
    {
        this.assetAddr = assetAddr;
    }

    public String getAssetAddr() 
    {
        return assetAddr;
    }
    public void setManufacturer(String manufacturer) 
    {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() 
    {
        return manufacturer;
    }
    public void setModelNum(String modelNum) 
    {
        this.modelNum = modelNum;
    }

    public String getModelNum() 
    {
        return modelNum;
    }
    public void setSnCode(String snCode) 
    {
        this.snCode = snCode;
    }

    public String getSnCode() 
    {
        return snCode;
    }
    public void setIpAddr(String ipAddr) 
    {
        this.ipAddr = ipAddr;
    }

    public String getIpAddr() 
    {
        return ipAddr;
    }
    public void setLoginMode(String loginMode) 
    {
        this.loginMode = loginMode;
    }

    public String getLoginMode() 
    {
        return loginMode;
    }
    public void setSpecs(String specs) 
    {
        this.specs = specs;
    }

    public String getSpecs() 
    {
        return specs;
    }
    public void setVirtual(String virtual) 
    {
        this.virtual = virtual;
    }

    public String getVirtual() 
    {
        return virtual;
    }
    public void setAssetCode(String assetCode) 
    {
        this.assetCode = assetCode;
    }

    public String getAssetCode() 
    {
        return assetCode;
    }
    public void setAssetStatus(String assetStatus) 
    {
        this.assetStatus = assetStatus;
    }

    public String getAssetStatus() 
    {
        return assetStatus;
    }
    public void setAssetUser(String assetUser) 
    {
        this.assetUser = assetUser;
    }

    public String getAssetUser() 
    {
        return assetUser;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("assetId", getAssetId())
            .append("assetSort", getAssetSort())
            .append("assetAddr", getAssetAddr())
            .append("manufacturer", getManufacturer())
            .append("modelNum", getModelNum())
            .append("snCode", getSnCode())
            .append("ipAddr", getIpAddr())
            .append("loginMode", getLoginMode())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("specs", getSpecs())
            .append("virtual", getVirtual())
            .append("assetCode", getAssetCode())
            .append("assetStatus", getAssetStatus())
            .append("assetUser", getAssetUser())
            .toString();
    }
}
