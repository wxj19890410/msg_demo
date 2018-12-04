<template>
  <div style="height:100%;width:100%;">
    <Sidebar/>
    <div style="" ref="openWindow" class="component-page">
      <div style="display:flex;justify-content:space-between;margin:50px 0 20px 0;">
        <div class="shaixuan">
          <div style="width:80px;margin-rignt:30px;color:#58595D;line-height:35px;">筛选：</div>
          <div style="margin-right:15px;">
            <el-input
              placeholder="请输入姓名"
              v-model="username"
              clearable>
            </el-input>
          </div>
          <div style="margin-right:15px;">
            <el-input
              placeholder="请输入手机号码"
              v-model="mobile"
              clearable>
            </el-input>
          </div>
          <div style="margin-right:15px;">
            <el-input
              placeholder="请输入系统ID"
              v-model="userid"
              clearable>
            </el-input>
          </div>
          <div style="margin-right:15px;">
            <el-select v-model="deptType" filterable placeholder="请选择主部门" >
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </div>
          <div style="margin-right:15px;">
            <el-select v-model="deptId" filterable placeholder="请选择子部门">
              <el-option
                v-for="item in options2"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </div>
          <div style="margin-right:15px;">
            <el-select v-model="tagid" filterable placeholder="请选择班组">
              <el-option
                v-for="item in options3"
                :key="item.tagid"
                :label="item.name"
                :value="item.tagid">
              </el-option>
            </el-select>
          </div>
          <el-date-picker
            v-model="value6"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            style="margin-right:15px;"
            end-placeholder="结束日期">
          </el-date-picker>
          <el-button icon="el-icon-search"  circle @click = "searchData()"></el-button>
          <el-button type="primary" plain @click = "refreshSearch()">重置</el-button>
        
        </div>
      </div>
      <div style="width:100%;margin-left:0%;">
        <template >
          <el-table :data="tableData" stripe border :default-sort = "{prop: 'date', order: 'descending'}" ><el-table-column prop="userid" label="系统id" ></el-table-column><el-table-column prop="name"  label="姓名"></el-table-column><el-table-column prop="mobile"  label="手机号码"></el-table-column><el-table-column prop="avatar"  label="微信头像"><template slot-scope="scope"><img  :src="scope.row.avatar" alt="" style="width: 40px;height: 40px"></template></el-table-column><el-table-column prop="deptType"  label="主部门"></el-table-column><el-table-column prop="deptName"  label="子部门"></el-table-column><el-table-column prop="tagNames"  label="班组"></el-table-column><el-table-column prop="rank" sortable label="总排名"></el-table-column>
          <el-table-column prop="total" sortable label="总指数">
            <template slot-scope="scope">
                {{scope.row.total | division}}
            </template>
          </el-table-column>
          <el-table-column prop="culture" sortable label="企业文化指数">
            <template slot-scope="scope">
                {{scope.row.culture | division}}
            </template>
          </el-table-column>
          <el-table-column prop="study" sortable label="学习成长指数">
            <template slot-scope="scope">
                {{scope.row.study | division}}
            </template>
          </el-table-column>
          <el-table-column prop="improve" sortable label="精益改善指数">
            <template slot-scope="scope">
                {{scope.row.improve | division}}
            </template>
          </el-table-column>
          <el-table-column prop="read" sortable label="读书指数">
            <template slot-scope="scope">
                {{scope.row.read | division}}
            </template>
          </el-table-column>
          <el-table-column prop="hse" sortable label="HSE指数">
            <template slot-scope="scope">
                {{scope.row.hse | division}}
            </template>
          </el-table-column>
          <el-table-column prop="attendance" sortable label="出勤指数">
            <template slot-scope="scope">
                {{scope.row.attendance | division}}
            </template>
          </el-table-column>
            <el-table-column prop="createDate" sortable label="计算更新时间">
              <template slot-scope="scope">
                  {{scope.row.createDate | dataFilter}}
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
  name: 'Test',
  data () {
    return {
      count: 0,
      pageSize: 20,
      currentPage: 1,
      tableData: [],
      value6:'',
      options: [{
          value: '',
          label: '全部'
        },{
          value: '01',
          label: '生产部门'
        }, {
          value: '02',
          label: '非生产部门'
        }],
        options2: [{
          value: '选项1',
          label: '子部门1'
        }, {
          value: '选项2',
          label: '子部门2'
        }],
        username: '',
        mobile: '',
        deptType: '',
        deptId: '',
        userid: '',
        options3: [],
        tagid:''
    }
  },
  filters: {
    division :function(value) {
       return (value / 100).toFixed(2)
    },
    dataFilter: function(date) {
      let data = ''
      if(date){
        data = date.split('T')[0]
      }
      return data
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
    this.getDeptData()
    this.getTagData()
  },
  methods:{
    getTagData () {
      this.$http.get('/huoli/org/findGroupMap').then(({ data }) => {
        if (data) {
          this.options3 = [{tagid: '', name: '全部'}]
          this.options3 = this.options3.concat(data)
        } else {
          this.$message({
            type: 'error',
            message: data.message
          })
        }
      })
    },
    getDeptData () {
      this.$http.get('/huoli/org/findDeptMap').then(({ data }) => {
        if (data) {
          this.options2 = [{id: '', name: '全部'}]
          this.options2 = this.options2.concat(data)
        } else {
          this.$message.error({
            type: 'error',
            message: data.message
          })
        }
      })
    },
    searchData () {
      this.initData()
    },
    refreshSearch () {
      this.deptType = ''
      this.deptId = ''
      this.mobile = ''
      this.username = ''
      this.userid = ''
      this.tagid = ''
      this.username = ''
      this.value6 = ''
      this.initData()
    },
    initData () {
      const params = {}
      params.username = this.username
      params.mobile = this.mobile
      params.deptType = this.deptType
      params.deptId = this.deptId
      params.userid = this.userid
      params.tagid = this.tagid
      if(this.value6 && this.value6.length > 0){
        const date = this.$filter.setMonth(this.value6)
        params.startMonth = date.startMonth
        params.monthNub = date.monthNub
      }
      params.avg = '1'
      params.start = (this.currentPage - 1) * this.pageSize
      params.length = this.pageSize
      this.$http.get('/huoli/user/userDataGrid', {params: params}).then(({ data }) => {
        if (data) {
          this.tableData = data.rows
          this.count = data.count
        } else {
          this.$message.error({
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
.shaixuan{display: flex;width: 100%;justify-content: space-around;}
/deep/ .el-table th>.cell{color:#000;}
/deep/ .el-table{color:#000;}

</style>
