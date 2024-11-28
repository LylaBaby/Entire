<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="资产分类" prop="assetSort">
        <el-select v-model="queryParams.assetSort" placeholder="请选择资产分类" clearable>
          <el-option
            v-for="dict in dict.type.it_asset_sort"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="机器地址" prop="assetAddr">
        <el-select v-model="queryParams.assetAddr" placeholder="请选择机器地址" clearable>
          <el-option
            v-for="dict in dict.type.it_asset_addr"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="厂商" prop="manufacturer">
        <el-input
          v-model="queryParams.manufacturer"
          placeholder="请输入厂商"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="型号" prop="modelNum">
        <el-input
          v-model="queryParams.modelNum"
          placeholder="请输入型号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="SN码" prop="snCode">
        <el-input
          v-model="queryParams.snCode"
          placeholder="请输入SN码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="IP地址" prop="ipAddr">
        <el-input
          v-model="queryParams.ipAddr"
          placeholder="请输入IP地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="虚拟化" prop="isVirt">
        <el-select v-model="queryParams.isVirt" placeholder="请选择虚拟化" clearable>
          <el-option
            v-for="dict in dict.type.vm"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="资产编号" prop="assetCode">
        <el-input
          v-model="queryParams.assetCode"
          placeholder="请输入资产编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="资产状态" prop="assetStatus">
        <el-select v-model="queryParams.assetStatus" placeholder="请选择资产状态" clearable>
          <el-option
            v-for="dict in dict.type.it_asset_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="资产用户" prop="assetUser">
        <el-input
          v-model="queryParams.assetUser"
          placeholder="请输入资产用户"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['asset:itasset:add']"
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
          v-hasPermi="['asset:itasset:edit']"
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
          v-hasPermi="['asset:itasset:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['asset:itasset:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="itassetList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="资产ID" align="center" prop="assetId" />
      <el-table-column label="资产分类" align="center" prop="assetSort">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.it_asset_sort" :value="scope.row.assetSort"/>
        </template>
      </el-table-column>
      <el-table-column label="机器地址" align="center" prop="assetAddr">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.it_asset_addr" :value="scope.row.assetAddr"/>
        </template>
      </el-table-column>
      <el-table-column label="厂商" align="center" prop="manufacturer" />
      <el-table-column label="型号" align="center" prop="modelNum" />
      <el-table-column label="SN码" align="center" prop="snCode" />
      <el-table-column label="IP地址" align="center" prop="ipAddr" />
      <el-table-column label="虚拟化" align="center" prop="isVirt">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.vm" :value="scope.row.isVirt"/>
        </template>
      </el-table-column>
      <el-table-column label="资产编号" align="center" prop="assetCode" />
      <el-table-column label="资产状态" align="center" prop="assetStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.it_asset_status" :value="scope.row.assetStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="资产用户" align="center" prop="assetUser" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['asset:itasset:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['asset:itasset:remove']"
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

    <!-- 添加或修改IT资产对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="资产分类" prop="assetSort">
          <el-radio-group v-model="form.assetSort">
            <el-radio
              v-for="dict in dict.type.it_asset_sort"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="机器地址" prop="assetAddr">
          <el-radio-group v-model="form.assetAddr">
            <el-radio
              v-for="dict in dict.type.it_asset_addr"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="厂商" prop="manufacturer">
          <el-input v-model="form.manufacturer" placeholder="请输入厂商" />
        </el-form-item>
        <el-form-item label="型号" prop="modelNum">
          <el-input v-model="form.modelNum" placeholder="请输入型号" />
        </el-form-item>
        <el-form-item label="SN码" prop="snCode">
          <el-input v-model="form.snCode" placeholder="请输入SN码" />
        </el-form-item>
        <el-form-item label="IP地址" prop="ipAddr">
          <el-input v-model="form.ipAddr" placeholder="请输入IP地址" />
        </el-form-item>
        <el-form-item label="登陆方式" prop="loginMode">
          <el-input v-model="form.loginMode" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="配置" prop="specs">
          <el-input v-model="form.specs" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="虚拟化" prop="isVirt">
          <el-radio-group v-model="form.isVirt">
            <el-radio
              v-for="dict in dict.type.vm"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="资产编号" prop="assetCode">
          <el-input v-model="form.assetCode" placeholder="请输入资产编号" />
        </el-form-item>
        <el-form-item label="资产状态" prop="assetStatus">
          <el-radio-group v-model="form.assetStatus">
            <el-radio
              v-for="dict in dict.type.it_asset_status"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="资产用户" prop="assetUser">
          <el-input v-model="form.assetUser" placeholder="请输入资产用户" />
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
import { listItasset, getItasset, delItasset, addItasset, updateItasset } from "@/api/asset/itasset";

export default {
  name: "Itasset",
  dicts: ['it_asset_addr', 'vm', 'it_asset_status', 'it_asset_sort'],
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
      // IT资产表格数据
      itassetList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        assetSort: null,
        assetAddr: null,
        manufacturer: null,
        modelNum: null,
        snCode: null,
        ipAddr: null,
        loginMode: null,
        isVirt: null,
        assetCode: null,
        assetStatus: null,
        assetUser: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        assetSort: [
          { required: true, message: "资产分类不能为空", trigger: "change" }
        ],
        assetAddr: [
          { required: true, message: "机器地址不能为空", trigger: "change" }
        ],
        manufacturer: [
          { required: true, message: "厂商不能为空", trigger: "blur" }
        ],
        modelNum: [
          { required: true, message: "型号不能为空", trigger: "blur" }
        ],
        specs: [
          { required: true, message: "配置不能为空", trigger: "blur" }
        ],
        isVirt: [
          { required: true, message: "虚拟化不能为空", trigger: "change" }
        ],
        assetCode: [
          { required: true, message: "资产编号不能为空", trigger: "blur" }
        ],
        assetStatus: [
          { required: true, message: "资产状态不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询IT资产列表 */
    getList() {
      this.loading = true;
      listItasset(this.queryParams).then(response => {
        this.itassetList = response.rows;
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
        assetId: null,
        assetSort: null,
        assetAddr: null,
        manufacturer: null,
        modelNum: null,
        snCode: null,
        ipAddr: null,
        loginMode: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        specs: null,
        isVirt: null,
        assetCode: null,
        assetStatus: null,
        assetUser: null
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
      this.ids = selection.map(item => item.assetId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加IT资产";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const assetId = row.assetId || this.ids
      getItasset(assetId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改IT资产";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.assetId != null) {
            updateItasset(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addItasset(this.form).then(response => {
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
      const assetIds = row.assetId || this.ids;
      this.$modal.confirm('是否确认删除IT资产编号为"' + assetIds + '"的数据项？').then(function() {
        return delItasset(assetIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('asset/itasset/export', {
        ...this.queryParams
      }, `itasset_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
