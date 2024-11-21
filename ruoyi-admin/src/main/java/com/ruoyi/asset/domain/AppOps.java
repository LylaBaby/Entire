package com.ruoyi.asset.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 系统运维对象 app_ops
 * 
 * @author Sven
 * @date 2024-11-20
 */
public class AppOps extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 应用项目 */
    @Excel(name = "应用项目")
    private String appItems;

    /** 应用名 */
    @Excel(name = "应用名")
    private String appName;

    /** 应用路径 */
    private String appPath;

    /** 应用端口 */
    @Excel(name = "应用端口")
    private String appPort;

    /** 应用包名 */
    private String packageName;

    /** 构建命令 */
    private String buildCommand;

    /** git地址 */
    private String gitPath;

    /** 应用状态 */
    @Excel(name = "应用状态")
    private String appStatus;

    /** 组件配置 */
    private String elementConf;

    /** 日志路径 */
    private String logsPath;

    /** Jenkins地址 */
    private String jenkinsAddr;

    /** 发版命令 */
    private String releaseCommand;

    public void setAppItems(String appItems) 
    {
        this.appItems = appItems;
    }

    public String getAppItems() 
    {
        return appItems;
    }
    public void setAppName(String appName) 
    {
        this.appName = appName;
    }

    public String getAppName() 
    {
        return appName;
    }
    public void setAppPath(String appPath) 
    {
        this.appPath = appPath;
    }

    public String getAppPath() 
    {
        return appPath;
    }
    public void setAppPort(String appPort) 
    {
        this.appPort = appPort;
    }

    public String getAppPort() 
    {
        return appPort;
    }
    public void setPackageName(String packageName) 
    {
        this.packageName = packageName;
    }

    public String getPackageName() 
    {
        return packageName;
    }
    public void setBuildCommand(String buildCommand) 
    {
        this.buildCommand = buildCommand;
    }

    public String getBuildCommand() 
    {
        return buildCommand;
    }
    public void setGitPath(String gitPath) 
    {
        this.gitPath = gitPath;
    }

    public String getGitPath() 
    {
        return gitPath;
    }
    public void setAppStatus(String appStatus) 
    {
        this.appStatus = appStatus;
    }

    public String getAppStatus() 
    {
        return appStatus;
    }
    public void setElementConf(String elementConf) 
    {
        this.elementConf = elementConf;
    }

    public String getElementConf() 
    {
        return elementConf;
    }
    public void setLogsPath(String logsPath) 
    {
        this.logsPath = logsPath;
    }

    public String getLogsPath() 
    {
        return logsPath;
    }
    public void setJenkinsAddr(String jenkinsAddr) 
    {
        this.jenkinsAddr = jenkinsAddr;
    }

    public String getJenkinsAddr() 
    {
        return jenkinsAddr;
    }
    public void setReleaseCommand(String releaseCommand) 
    {
        this.releaseCommand = releaseCommand;
    }

    public String getReleaseCommand() 
    {
        return releaseCommand;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("appItems", getAppItems())
            .append("appName", getAppName())
            .append("appPath", getAppPath())
            .append("appPort", getAppPort())
            .append("packageName", getPackageName())
            .append("buildCommand", getBuildCommand())
            .append("gitPath", getGitPath())
            .append("appStatus", getAppStatus())
            .append("elementConf", getElementConf())
            .append("logsPath", getLogsPath())
            .append("jenkinsAddr", getJenkinsAddr())
            .append("releaseCommand", getReleaseCommand())
            .toString();
    }
}
