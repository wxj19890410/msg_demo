<template>
	<div style="height:100%;width:100%;">
	    <Sidebar/>
	    <div style="" ref="openWindow" class="component-page"> 
	    	<div style="padding:20px;border-bottom:1px solid #B6B6B6;margin-bottom:50px;">
	    		<el-button type="primary" @click="getZip()" plain>备份当前数据</el-button>
	    	</div>
           <div style="width:100%;margin-left:0%;">
		    	<template>
				    <el-table
				      :data="tableData"
				      style="width: 100%">
				      <el-table-column
				        prop="name"
				        label="文件名"
				        width="180">
				      </el-table-column>
				      <el-table-column
				        prop="createDate"
				        label="日期"
				        width="180">
                <template slot-scope="scope">
                  {{scope.row.createDate | dataFilter}}
                </template>
				      </el-table-column>
				      <el-table-column label="操作">
					      <template slot-scope="scope">
					        <el-button
					          size="mini"
					          @click="handleEdit(scope.$index, scope.row)">下载</el-button>
					        <el-button
					          size="mini"
					          type="danger"
					          @click="handleDelete(scope.$index, scope.row)">删除</el-button>
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
  name: 'backupData',
  data () {
    return {
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
  filters: {
    dataFilter: function(date) {
      let data = ''
      if(date){
        data = date.split('T')[0]
      }
      return data
    }
  },
  created () {
    this.initData()
  },
  methods:{
    initData () {
      const params = {}
      params.start = (this.currentPage - 1) * this.pageSize
      params.length = this.pageSize
      params.relationType = 'zip'
      this.$http.get('/huoli/file/getFileDataGrid', {params: params}).then(({ data }) => {
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
  	getZip() {
      this.$http.get('/huoli/mobile/dataZip', {
        responseType: 'arraybuffer'
      }).then(({ data }) => {
        this.initData()
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
    },
    handleDelete(index, row) {
      const params = {}
      params.fileId = row.id
      params.path = row.path
      this.$http.get('/huoli/file/deleteFile',{params: params}).then(({ data }) => {
          this.initData()
        })

    },
    handleEdit(index, row) {
      location.href = window.GLOBLE.apiUrl + '/huoli/mobile/downLoad?path='+row.path
    	/*const params = {}
    	params.path = row.path
    	this.$http.get('/huoli/mobile/downLoad',{params: params}).then(({ response }) => {
          console.log(response)
      		var blob = new Blob([response.data], { type: 'application/zip' })
          var link = document.createElement('a')
          link.href = window.URL.createObjectURL(blob)
          link.download = '备份数据'
          link.click()
    		})*/
    	}
   }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.component-page{height:300px;width:400px;box-shadow: 0px 0px 10px rgba(0,0,0,0.5); position:absolute;top:80px;left:150px;border-radius: 10px;background-color: #fff;opacity: 0;padding:50px;overflow-y: scroll;}

</style>
