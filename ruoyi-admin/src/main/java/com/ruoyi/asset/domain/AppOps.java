package com.ruoyi.asset.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 系统运维对象 app_ops
 * 
 * @author Sven
 * @date 2024-11-27
 */
public class AppOps extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long opsId;

    /** 应用项目 */
    @Excel(name = "应用项目")
    private String projectName;

    /** 应用名 */
    @Excel(name = "应用名")
    private String appName;

    /** 应用路径 */
    private String appUrl;

    /** 应用端口 */
    @Excel(name = "应用端口")
    private Long appPort;

    /** 应用包名 */
    private String packageName;

    /** 构建命令 */
    private String buildCommand;

    /** git地址 */
    private String gitUrl;

    /** 应用状态 */
    @Excel(name = "应用状态")
    private String appStatus;

    /** 组件配置 */
    private String elementConf;

    /** 日志路径 */
    private String logsPath;

    /** Jenkins地址 */
    private String jenkinsUrl;

    /** 发版命令 */
    private String releaseCommand;

    public void setOpsId(Long opsId) 
    {
        this.opsId = opsId;
    }

    public Long getOpsId() 
    {
        return opsId;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setAppName(String appName) 
    {
        this.appName = appName;
    }

    public String getAppName() 
    {
        return appName;
    }
    public void setAppUrl(String appUrl) 
    {
        this.appUrl = appUrl;
    }

    public String getAppUrl() 
    {
        return appUrl;
    }
    public void setAppPort(Long appPort) 
    {
        this.appPort = appPort;
    }

    public Long getAppPort() 
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
    public void setGitUrl(String gitUrl) 
    {
        this.gitUrl = gitUrl;
    }

    public String getGitUrl() 
    {
        return gitUrl;
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
    public void setJenkinsUrl(String jenkinsUrl) 
    {
        this.jenkinsUrl = jenkinsUrl;
    }

    public String getJenkinsUrl() 
    {
        return jenkinsUrl;
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
            .append("opsId", getOpsId())
            .append("projectName", getProjectName())
            .append("appName", getAppName())
            .append("appUrl", getAppUrl())
            .append("appPort", getAppPort())
            .append("packageName", getPackageName())
            .append("buildCommand", getBuildCommand())
            .append("gitUrl", getGitUrl())
            .append("appStatus", getAppStatus())
            .append("elementConf", getElementConf())
            .append("logsPath", getLogsPath())
            .append("jenkinsUrl", getJenkinsUrl())
            .append("releaseCommand", getReleaseCommand())
            .toString();
    }
}
