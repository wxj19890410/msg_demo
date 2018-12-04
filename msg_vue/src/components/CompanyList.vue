<template>
  <div style="height:100%;width:100%;">
    <Sidebar/>
    <div style="" ref="openWindow" class="component-page">
      <div style="display:flex;justify-content:space-between;margin:50px 0 20px 0;">
        <div class="shaixuan">
          <div style="width:100px;margin-rignt:30px;color:#58595D;line-height:35px;">选择部门：</div>
          <el-select v-model="deptType" filterable placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </div>
        <el-button type="primary" style="height:40px;" round @click="refreshDept()">添加企业</el-button>
      </div>
      <div style="width:100%;margin-left:0%;">
        <template >
          <el-table :data="tableData" stripe border :default-sort = "{prop: 'date', order: 'descending'}" >
            <el-table-column prop="deptType" label="状态" >
              <template slot-scope="scope">
                <el-switch
                  @change="switchChange(scope.row)"
                  v-model="scope.row.deptType"
                  active-text="禁用"
                  inactive-text="正常"
                  active-value="02"
                  inactive-value="01">
                </el-switch>
              </template>           
            </el-table-column>
            <el-table-column prop="name" label="企业名称"></el-table-column>
            <el-table-column prop="number" label="签名模板"></el-table-column>
            <el-table-column prop="number" label="余额"></el-table-column>
            <el-table-column prop="createDate" label="创建时间">
              <template slot-scope="scope">
                  {{scope.row.createDate | dataFilter}}
              </template>
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button 
                  size="mini"
                  @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                <el-button 
                  size="mini"
                  @click="handleEdit(scope.$index, scope.row)">充值</el-button>
                <el-button 
                  size="mini"
                  @click="handleEdit(scope.$index, scope.row)">删除</el-button>
              </template>
             </el-table-column>
          </el-table>
        </template>
      </div>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        style="margin-top:20px;"
        :page-size="pageSize"
        layout="total, prev, pager, next, jumper"
        :total="count">
      </el-pagination>
 
    </div>
 
  </div>
</template>

<script>
import $ from 'jquery'

export default {
  name: 'companyList',
  data () {
    return {
      options: [
      {
        value: '',
        label: '全部'
      },{
        value: '01',
        label: '生产部门'
      }, {
        value: '02',
        label: '非生产部门'
      }],
      deptType: '',
      count: 0,
      pageSize: 20,
      currentPage: 1,
      tableData: []
    }
  },
  mounted(){
    this.$nextTick(() => { //使用nextTick为了保证dom元素都已经渲染完毕 
      $(".component-page").width($(window).width()-340)
      $(".component-page").height($(window).height()-220)
      this.animatePage()
    });
  },
  created () {
    this.initData()
  },
  filters: {
    dataFilter: function(date) {
      let data = ''
      if(date){
        data = date.split('T')[0]
      }
      return data
    }
  },
  watch:{
    'deptType':function(){
       this.initData()
    }
  },
  methods:{
    refreshDept () {
      this.$http.get('/huoli/wxData/refreshDept').then(({ data }) => {
        this.initData()
        if (data && data > 0 ) {
          this.$notify.success({
            type: 'success',
            message: '现有部門' + data + '个'
          })
        }else if(data === 0 ){
          this.$notify.success({
            type: 'success',
            message: '没有部門在使用活力指数！'
          })
        } else {
          this.$notify.error({
            type: 'error',
            message: data.message
          })
        }
      })
    },
    initData () {
      const params = {}
      params.deptType = this.deptType
      params.avg = '0'
      params.start = (this.currentPage - 1) * this.pageSize
      params.length = this.pageSize
      this.$http.get('/huoli/org/deptDataGrid', {params: params}).then(({ data }) => {
        if (data) {
          this.tableData = data.rows
          this.count = data.count
        } else {
          this.$message({
            type: 'error',
            message: data.message
          })
        }
      })
    },
    switchChange (data) {
      const params = {}
      params.id = data.id
      params.deptType = data.deptType
      this.$http.get('/huoli/org/setDeptType', {params: params}).then(({ data }) => {
        if (data) {
          this.initData()
        } else {
          this.$message({
            type: 'error',
            message: data.message
          })
        }
      })
    },
    animatePage(){
      $(".component-page").animate({
      　　"opacity":"1",
      　　"left":"220px"
      },500);
    },
    handleSizeChange (val) {
      this.currentPage = val
      this.initData()
    },
    handleCurrentChange (val) {
      this.currentPage = val
      this.initData()
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.shaixuan{display: flex;width: 50%;}
    /deep/ .el-table th>.cell{color:#000;}
/deep/ .el-table{color:#000;}
</style>
