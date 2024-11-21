<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="应用项目" prop="appItems">
        <el-input
          v-model="queryParams.appItems"
          placeholder="请输入应用项目"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="应用名" prop="appName">
        <el-input
          v-model="queryParams.appName"
          placeholder="请输入应用名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="应用端口" prop="appPort">
        <el-input
          v-model="queryParams.appPort"
          placeholder="请输入应用端口"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="应用状态" prop="appStatus">
        <el-select v-model="queryParams.appStatus" placeholder="请选择应用状态" clearable>
          <el-option
            v-for="dict in dict.type.app_lifecycle"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['asset:ops:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['asset:ops:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['asset:ops:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['asset:ops:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="opsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="应用项目" align="center" prop="appItems" />
      <el-table-column label="应用名" align="center" prop="appName" />
      <el-table-column label="应用端口" align="center" prop="appPort" />
      <el-table-column label="应用状态" align="center" prop="appStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.app_lifecycle" :value="scope.row.appStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['asset:ops:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['asset:ops:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改系统运维对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="应用项目" prop="appItems">
          <el-input v-model="form.appItems" placeholder="请输入应用项目" />
        </el-form-item>
        <el-form-item label="应用名" prop="appName">
          <el-input v-model="form.appName" placeholder="请输入应用名" />
        </el-form-item>
        <el-form-item label="应用路径" prop="appPath">
          <el-input v-model="form.appPath" placeholder="请输入应用路径" />
        </el-form-item>
        <el-form-item label="应用端口" prop="appPort">
          <el-input v-model="form.appPort" placeholder="请输入应用端口" />
        </el-form-item>
        <el-form-item label="应用包名" prop="packageName">
          <el-input v-model="form.packageName" placeholder="请输入应用包名" />
        </el-form-item>
        <el-form-item label="构建命令" prop="buildCommand">
          <el-input v-model="form.buildCommand" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="git地址" prop="gitPath">
          <el-input v-model="form.gitPath" placeholder="请输入git地址" />
        </el-form-item>
        <el-form-item label="应用状态" prop="appStatus">
          <el-select v-model="form.appStatus" placeholder="请选择应用状态">
            <el-option
              v-for="dict in dict.type.app_lifecycle"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="组件配置">
          <editor v-model="form.elementConf" :min-height="192"/>
        </el-form-item>
        <el-form-item label="日志路径" prop="logsPath">
          <el-input v-model="form.logsPath" placeholder="请输入日志路径" />
        </el-form-item>
        <el-form-item label="Jenkins地址" prop="jenkinsAddr">
          <el-input v-model="form.jenkinsAddr" placeholder="请输入Jenkins地址" />
        </el-form-item>
        <el-form-item label="发版命令" prop="releaseCommand">
          <el-input v-model="form.releaseCommand" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listOps, getOps, delOps, addOps, updateOps } from "@/api/asset/ops";

export default {
  name: "Ops",
  dicts: ['app_lifecycle'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 系统运维表格数据
      opsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        appItems: null,
        appName: null,
        appPort: null,
        appStatus: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        appItems: [
          { required: true, message: "应用项目不能为空", trigger: "blur" }
        ],
        appName: [
          { required: true, message: "应用名不能为空", trigger: "blur" }
        ],
        appPath: [
          { required: true, message: "应用路径不能为空", trigger: "blur" }
        ],
        appPort: [
          { required: true, message: "应用端口不能为空", trigger: "blur" }
        ],
        packageName: [
          { required: true, message: "应用包名不能为空", trigger: "blur" }
        ],
        buildCommand: [
          { required: true, message: "构建命令不能为空", trigger: "blur" }
        ],
        gitPath: [
          { required: true, message: "git地址不能为空", trigger: "blur" }
        ],
        appStatus: [
          { required: true, message: "应用状态不能为空", trigger: "change" }
        ],
        elementConf: [
          { required: true, message: "组件配置不能为空", trigger: "blur" }
        ],
        logsPath: [
          { required: true, message: "日志路径不能为空", trigger: "blur" }
        ],
        jenkinsAddr: [
          { required: true, message: "Jenkins地址不能为空", trigger: "blur" }
        ],
        releaseCommand: [
          { required: true, message: "发版命令不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询系统运维列表 */
    getList() {
      this.loading = true;
      listOps(this.queryParams).then(response => {
        this.opsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        appItems: null,
        appName: null,
        appPath: null,
        appPort: null,
        packageName: null,
        buildCommand: null,
        gitPath: null,
        appStatus: null,
        elementConf: null,
        logsPath: null,
        jenkinsAddr: null,
        releaseCommand: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.appItems)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加系统运维";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const appItems = row.appItems || this.ids
      getOps(appItems).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改系统运维";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.appItems != null) {
            updateOps(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOps(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const appItemss = row.appItems || this.ids;
      this.$modal.confirm('是否确认删除系统运维编号为"' + appItemss + '"的数据项？').then(function() {
        return delOps(appItemss);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('asset/ops/export', {
        ...this.queryParams
      }, `ops_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
