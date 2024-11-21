<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="应用ID" prop="appId">
        <el-input
          v-model="queryParams.appId"
          placeholder="请输入应用ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目" prop="items">
        <el-input
          v-model="queryParams.items"
          placeholder="请输入项目"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分类" prop="sort">
        <el-select v-model="queryParams.sort" placeholder="请选择分类" clearable>
          <el-option
            v-for="dict in dict.type.app_sort"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="域名" prop="domain">
        <el-input
          v-model="queryParams.domain"
          placeholder="请输入域名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开发语言" prop="pl">
        <el-input
          v-model="queryParams.pl"
          placeholder="请输入开发语言"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="生命周期" prop="lifecycle">
        <el-select v-model="queryParams.lifecycle" placeholder="请选择生命周期" clearable>
          <el-option
            v-for="dict in dict.type.app_lifecycle"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="数据中心" prop="dataCenter">
        <el-select v-model="queryParams.dataCenter" placeholder="请选择数据中心" clearable>
          <el-option
            v-for="dict in dict.type.environment"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="风险评估" prop="riskEval">
        <el-select v-model="queryParams.riskEval" placeholder="请选择风险评估" clearable>
          <el-option
            v-for="dict in dict.type.app_risk_eval"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="风险等级" prop="riskLevel">
        <el-select v-model="queryParams.riskLevel" placeholder="请选择风险等级" clearable>
          <el-option
            v-for="dict in dict.type.app_risk_level"
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
          v-hasPermi="['asset:appasset:add']"
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
          v-hasPermi="['asset:appasset:edit']"
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
          v-hasPermi="['asset:appasset:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['asset:appasset:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="appassetList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="应用ID" align="center" prop="appId" />
      <el-table-column label="项目" align="center" prop="items" />
      <el-table-column label="分类" align="center" prop="sort">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.app_sort" :value="scope.row.sort"/>
        </template>
      </el-table-column>
      <el-table-column label="域名" align="center" prop="domain" />
      <el-table-column label="开发语言" align="center" prop="pl" />
      <el-table-column label="框架" align="center" prop="frame" />
      <el-table-column label="生命周期" align="center" prop="lifecycle">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.app_lifecycle" :value="scope.row.lifecycle"/>
        </template>
      </el-table-column>
      <el-table-column label="数据中心" align="center" prop="dataCenter">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.environment" :value="scope.row.dataCenter"/>
        </template>
      </el-table-column>
      <el-table-column label="风险评估" align="center" prop="riskEval">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.app_risk_eval" :value="scope.row.riskEval"/>
        </template>
      </el-table-column>
      <el-table-column label="风险等级" align="center" prop="riskLevel">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.app_risk_level" :value="scope.row.riskLevel"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['asset:appasset:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['asset:appasset:remove']"
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

    <!-- 添加或修改应用资产对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="应用ID" prop="appId">
          <el-input v-model="form.appId" placeholder="请输入应用ID" />
        </el-form-item>
        <el-form-item label="项目" prop="items">
          <el-input v-model="form.items" placeholder="请输入项目" />
        </el-form-item>
        <el-form-item label="分类" prop="sort">
          <el-select v-model="form.sort" placeholder="请选择分类">
            <el-option
              v-for="dict in dict.type.app_sort"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="服务器" prop="server">
          <el-input v-model="form.server" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="网络">
          <editor v-model="form.network" :min-height="192"/>
        </el-form-item>
        <el-form-item label="域名" prop="domain">
          <el-input v-model="form.domain" placeholder="请输入域名" />
        </el-form-item>
        <el-form-item label="开发语言" prop="pl">
          <el-input v-model="form.pl" placeholder="请输入开发语言" />
        </el-form-item>
        <el-form-item label="框架" prop="frame">
          <el-input v-model="form.frame" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="组件" prop="element">
          <el-input v-model="form.element" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="生命周期" prop="lifecycle">
          <el-select v-model="form.lifecycle" placeholder="请选择生命周期">
            <el-option
              v-for="dict in dict.type.app_lifecycle"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="数据中心" prop="dataCenter">
          <el-select v-model="form.dataCenter" placeholder="请选择数据中心">
            <el-option
              v-for="dict in dict.type.environment"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="风险评估" prop="riskEval">
          <el-select v-model="form.riskEval" placeholder="请选择风险评估">
            <el-option
              v-for="dict in dict.type.app_risk_eval"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="风险等级" prop="riskLevel">
          <el-select v-model="form.riskLevel" placeholder="请选择风险等级">
            <el-option
              v-for="dict in dict.type.app_risk_level"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="访问控制策略" prop="secAcl">
          <el-input v-model="form.secAcl" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="安全控制" prop="secSafety">
          <el-input v-model="form.secSafety" type="textarea" placeholder="请输入内容" />
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
import { listAppasset, getAppasset, delAppasset, addAppasset, updateAppasset } from "@/api/asset/appasset";

export default {
  name: "Appasset",
  dicts: ['app_lifecycle', 'environment', 'app_sort', 'app_risk_level', 'app_risk_eval'],
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
      // 应用资产表格数据
      appassetList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        appId: null,
        items: null,
        sort: null,
        server: null,
        domain: null,
        pl: null,
        frame: null,
        element: null,
        lifecycle: null,
        dataCenter: null,
        riskEval: null,
        riskLevel: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        appId: [
          { required: true, message: "应用ID不能为空", trigger: "blur" }
        ],
        items: [
          { required: true, message: "项目不能为空", trigger: "blur" }
        ],
        sort: [
          { required: true, message: "分类不能为空", trigger: "change" }
        ],
        server: [
          { required: true, message: "服务器不能为空", trigger: "blur" }
        ],
        network: [
          { required: true, message: "网络不能为空", trigger: "blur" }
        ],
        domain: [
          { required: true, message: "域名不能为空", trigger: "blur" }
        ],
        pl: [
          { required: true, message: "开发语言不能为空", trigger: "blur" }
        ],
        frame: [
          { required: true, message: "框架不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        updateBy: [
          { required: true, message: "更新者不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "更新时间不能为空", trigger: "blur" }
        ],
        element: [
          { required: true, message: "组件不能为空", trigger: "blur" }
        ],
        lifecycle: [
          { required: true, message: "生命周期不能为空", trigger: "change" }
        ],
        dataCenter: [
          { required: true, message: "数据中心不能为空", trigger: "change" }
        ],
        riskEval: [
          { required: true, message: "风险评估不能为空", trigger: "change" }
        ],
        riskLevel: [
          { required: true, message: "风险等级不能为空", trigger: "change" }
        ],
        secAcl: [
          { required: true, message: "访问控制策略不能为空", trigger: "blur" }
        ],
        secSafety: [
          { required: true, message: "安全控制不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询应用资产列表 */
    getList() {
      this.loading = true;
      listAppasset(this.queryParams).then(response => {
        this.appassetList = response.rows;
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
        appId: null,
        items: null,
        sort: null,
        server: null,
        network: null,
        domain: null,
        pl: null,
        frame: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        element: null,
        lifecycle: null,
        dataCenter: null,
        riskEval: null,
        riskLevel: null,
        secAcl: null,
        secSafety: null
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
      this.ids = selection.map(item => item.appId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加应用资产";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const appId = row.appId || this.ids
      getAppasset(appId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改应用资产";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.appId != null) {
            updateAppasset(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAppasset(this.form).then(response => {
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
      const appIds = row.appId || this.ids;
      this.$modal.confirm('是否确认删除应用资产编号为"' + appIds + '"的数据项？').then(function() {
        return delAppasset(appIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('asset/appasset/export', {
        ...this.queryParams
      }, `appasset_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
