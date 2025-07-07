<template>
  <div class="common-layout">
    <NavBar/>
    <el-container>
<!--      <el-header>-->
<!--        <el-menu-->
<!--          default-active="5"-->
<!--          class="el-menu-main"-->
<!--          mode="horizontal"-->
<!--          :ellipsis="false"-->
<!--          @select="handleTop"-->
<!--        >-->
<!--          <el-menu-item index="0">-->
<!--            <el-icon><UserFilled /></el-icon>-->
<!--            <el-text class="title-tag" type="primary" size="large">社团联盟后台管理</el-text>-->
<!--          </el-menu-item>-->
<!--          <el-menu-item index="1">首页</el-menu-item>-->
<!--          <el-sub-menu index="2">-->
<!--            <template #title>我的社团</template>-->
<!--            <el-menu-item index="2-1">item one</el-menu-item>-->
<!--            <el-menu-item index="2-2">item two</el-menu-item>-->
<!--            <el-menu-item index="2-3">item three</el-menu-item>-->
<!--          </el-sub-menu>-->
<!--          <el-menu-item index="3">我的活动</el-menu-item>-->
<!--          <el-menu-item index="4">个人中心</el-menu-item>-->
<!--          <el-menu-item index="5">后台管理</el-menu-item>-->
<!--          <el-space wrap :size="'large'">-->
<!--            <el-image style="width: 40px; height: 40px; border-radius:40px; box-shadow:3px 3px 10px 0px dimgray" src="/avatar.jpg"></el-image>-->
<!--            <div class="border">-->
<!--              <div class="border-title">管理员</div>-->
<!--            </div>-->
<!--          </el-space>-->
<!--          <el-dropdown>-->
<!--            <span class="el-dropdown-link">-->
<!--              <el-text class="user-name" size="large">荷塘月色</el-text>-->
<!--              <el-icon class="el-icon&#45;&#45;right">-->
<!--                <arrow-down-bold />-->
<!--              </el-icon>-->
<!--            </span>-->
<!--            <template #dropdown>-->
<!--              <el-dropdown-menu>-->
<!--                <el-dropdown-item>退出登录</el-dropdown-item>-->
<!--              </el-dropdown-menu>-->
<!--            </template>-->
<!--          </el-dropdown>-->
<!--        </el-menu>-->
<!--      </el-header>-->
      <el-container>
        <el-aside width="200px">
          <el-row class="tac">
            <el-col :span="23">
              <h5 class="mb-2"></h5>
              <el-menu
                class="el-menu-vertical"
                @select="handleSide"
                default-active="/review/club"
                router="true"
              >
                <el-menu-item index="/dashboard">
                  <el-icon><Menu /></el-icon>
                  <span>控制台</span>
                </el-menu-item>
                <el-sub-menu>
                  <template #title>
                    <el-icon><location /></el-icon>
                    <span>社团管理</span>
                  </template>
                  <el-menu-item-group title="管理">
                    <el-menu-item index="/manageClub/info">社团信息管理</el-menu-item>
                    <el-menu-item index="/manageClub/member">社团成员管理</el-menu-item>
                  </el-menu-item-group>
                  <el-menu-item-group title="审核">
                    <el-menu-item index="/review/club">社团创建审核</el-menu-item>
                    <el-menu-item index="/review/activity">活动申请审核</el-menu-item>
                  </el-menu-item-group>
                </el-sub-menu>
                <el-sub-menu index="2">
                  <template #title>
                    <el-icon><Menu /></el-icon>
                    <span>用户管理</span>
                  </template>
                  <el-menu-item index="/manageUser/admin">管理员信息</el-menu-item>
                  <el-menu-item index="/manageUser/student">学生信息</el-menu-item>
                </el-sub-menu>
              </el-menu>
            </el-col>
          </el-row>
        </el-aside>
        <el-main>
          <el-button type="success" style="margin-top: 40px;margin-left: 40px" plain @click="passMultiple">
            <el-icon size="18"><FolderDelete /></el-icon>
            <el-text type="success">批量通过</el-text>
          </el-button>
          <el-button type="warning" style="margin-top: 40px" plain @click="refuseMultiple">
            <el-icon size="18"><FolderDelete /></el-icon>
            <el-text type="warning">批量拒绝</el-text>
          </el-button>
          <el-button type="danger" style="margin-top: 40px" plain @click="deleteMultiple">
            <el-icon size="18"><FolderDelete /></el-icon>
            <el-text type="danger">批量删除</el-text>
          </el-button>
          <el-input
            v-model="inputSearch"
            style="width: 240px;margin-top:40px;margin-left:30px"
            placeholder="输入名称查询"
            @change="searchClub"
            clearable
          />
          <el-button type="success" style="margin-top: 40px;margin-left: 10px" plain @click="searchClub">
            <el-icon size="18"><Search /></el-icon>
            <el-text type="success">搜索</el-text>
          </el-button>
          <el-table :data="PageData.list"style="margin-top:30px;margin-left: 40px;width: fit-content" @selection-change="handleSelection">
            <el-table-column type="selection" width="30"></el-table-column>
            <el-table-column property="clubId" label="ID" width="110" header-align="center" align="center" sortable/>
            <el-table-column property="clubName" label="社团名称" width="120" header-align="center" align="center" sortable/>
            <el-table-column property="category" label="分类" width="120" header-align="center" align="center" sortable/>
            <el-table-column property="presidentId" label="负责人ID" width="110" header-align="center" align="center" sortable/>
            <el-table-column property="president" label="负责人" width="120" header-align="center" align="center" sortable/>
            <el-table-column property="createdAt" label="申请时间" width="120" header-align="center" align="center" show-overflow-tooltip sortable/>
            <el-table-column property="requirements" label="加入条件" width="120" header-align="center" align="center" show-overflow-tooltip/>
            <el-table-column property="description" label="介绍" width="120" header-align="center" align="center" show-overflow-tooltip/>
            <el-table-column property="status" label="状态" width="120" header-align="center" align="center"/>
            <el-table-column v-slot="scope" width="105">
              <el-button type="success" plain @click="passSingle(scope.row)">
                <el-icon><Select /></el-icon>
                <el-text type="success">批准</el-text>
              </el-button>
            </el-table-column>
            <el-table-column v-slot="scope" width="105">
              <el-button type="warning" plain @click="refuseSingle(scope.row)">
                <el-icon><CloseBold /></el-icon>
                <el-text type="warning">拒绝</el-text>
              </el-button>
            </el-table-column>
            <el-table-column v-slot="scope" width="105">
              <el-button type="danger" plain @click="deleteSingle(scope.row)">
                <el-icon><Delete /></el-icon>
                <el-text type="danger">删除</el-text>
              </el-button>
            </el-table-column>
          </el-table>
          <el-pagination
            v-model:current-page="paginationData.pageNum"
            :page-size="paginationData.pageSize"
            :background="background"
            layout="total, prev, pager, next, jumper"
            :total="PageData.total"
            @current-change="handleCurrentChange"
            style="text-align: center;margin-left: 40px"
          />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script lang="ts">
import {onMounted, ref} from 'vue'
import { reactive } from "vue"
import axios from 'axios'
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting, UserFilled, Menu, Delete, Search, Refresh, Edit, CloseBold,
} from '@element-plus/icons-vue'
import {ElMessage, ElMessageBox, paginationProps} from 'element-plus'
import NavBar from "@/views/Home/component/NavBar.vue";

export default{
  computed: {
    Search() {
      return Search
    }
  },
  components: {CloseBold, Edit, NavBar, Refresh, Delete, Menu, Location, UserFilled},
  data(){
    return{
      userData:[],
      iniTable:[],
      inputSearch:'',
      PageData,
      paginationData,
      clubData,
      addDialog:false,
      editDialog:false,
      selection:[]
    }
  },
  methods:{
    handleCurrentChange(newPage: number){
      paginationData.pageNum = newPage
      this.updateTableData()
    },
    passSingle(row){
      axios.put("http://localhost:8080/passClub/"+row.clubId).then(res => {
        if(res.data==false) {
          ElMessage.error("批准失败")
          return
        }
        axios.get("http://localhost:8080/allClubToOperate").then(res => {
          this.clubData.list=res.data;
          this.clubData.total=res.data.length;
          this.updateTableData()
          ElMessage.success("已批准")
        })
      })
    },
    refuseSingle(row){
      axios.put("http://localhost:8080/refuseClub/"+row.clubId).then(res => {
        if(res.data==false) {
          ElMessage.error("拒绝失败")
          return
        }
        axios.get("http://localhost:8080/allClubToOperate").then(res => {
          this.clubData.list=res.data;
          this.clubData.total=res.data.length;
          this.updateTableData()
          ElMessage.success("已拒绝")
        })
      })
    },
    updateTableData(){
      const start = (paginationData.pageNum - 1) * paginationData.pageSize
      const end = start + paginationData.pageSize
      PageData.list = clubData.list.slice(start, end)
      PageData.total = clubData.total
    },
    passMultiple(){
      if(this.selection.length==0)
      {
        ElMessage.warning("未选中申请")
        return
      }
      ElMessageBox.confirm(
        '确定要批准选中的申请吗?',
        '警告',
        {
          confirmButtonText: '确认',
          cancelButtonText: '取消',
          type: 'warning',
        }
      )
        .then(() => {
          for(let i=0;i<this.selection.length;i++){
            axios.put("http://localhost:8080/passClub/"+this.selection[i].clubId).then(res => {
              if(res.data==false)
                ElMessage.error("批准失败")
              if(i==this.selection.length-1){
                axios.get("http://localhost:8080/allClubToOperate").then(res => {
                  this.clubData.list=res.data;
                  this.clubData.total=res.data.length;
                  this.updateTableData()
                  ElMessage.success("已批准")
                })
              }
            })
          }
        })
    },
    refuseMultiple(){
      if(this.selection.length==0)
      {
        ElMessage.warning("未选中申请")
        return
      }
      ElMessageBox.confirm(
        '确定要拒绝选中的申请吗?',
        '警告',
        {
          confirmButtonText: '确认',
          cancelButtonText: '取消',
          type: 'warning',
        }
      )
        .then(() => {
          for(let i=0;i<this.selection.length;i++){
            axios.put("http://localhost:8080/refuseClub/"+this.selection[i].clubId).then(res => {
              if(res.data==false)
                ElMessage.error("拒绝失败")
              if(i==this.selection.length-1){
                axios.get("http://localhost:8080/allClubToOperate").then(res => {
                  this.clubData.list=res.data;
                  this.clubData.total=res.data.length;
                  this.updateTableData()
                  ElMessage.success("已拒绝")
                })
              }
            })
          }
        })
    },
    deleteMultiple(){
      if(this.selection.length==0)
      {
        ElMessage.warning("未选中申请")
        return
      }
      ElMessageBox.confirm(
        '确定要删除选中的申请吗?',
        '警告',
        {
          confirmButtonText: '确认',
          cancelButtonText: '取消',
          type: 'warning',
        }
      )
        .then(() => {
          for(let i=0;i<this.selection.length;i++){
            axios.delete("http://localhost:8080/deleteClub/"+this.selection[i].clubId).then(res => {
              if(res.data==false)
                ElMessage.error("删除失败")
              if(i==this.selection.length-1){
                axios.get("http://localhost:8080/allClubToOperate").then(res => {
                  this.clubData.list=res.data;
                  this.clubData.total=res.data.length;
                  this.updateTableData()
                  ElMessage.success("已删除")
                })
              }
            })
          }
        })
    },
    handleSelection(val){
      this.selection=val
    },
    searchClub(){
      if(this.inputSearch=="")
      {
        this.resetSearch()
        return
      }
      axios.get("http://localhost:8080/searchClubToOperate/"+this.inputSearch).then((res) => {
        this.clubData.list=res.data
        this.clubData.total=res.data.length
        this.updateTableData()
      })
    },
    resetSearch(){
      axios.get("http://localhost:8080/allClubToOperate").then((res)=>{
        this.clubData.list=res.data
        this.clubData.total=res.data.length
        this.updateTableData()
      })
    },
    deleteSingle(row){
      axios.delete("http://localhost:8080/deleteClub/"+row.clubId).then(res => {
        if(res.data==false) {
          ElMessage.error("删除失败")
          return
        }
        axios.get("http://localhost:8080/allClubToOperate").then(res => {
          this.clubData.list=res.data;
          this.clubData.total=res.data.length;
          this.updateTableData()
          ElMessage.success("删除成功")
        })
      })
    }
  },
  mounted(){
    axios.get("http://localhost:8080/allClubToOperate").then((res)=>{
      this.clubData.list=res.data
      this.clubData.total=res.data.length
      this.updateTableData()
    })
  }
}

const clubData = reactive({
  list: [],
  total: 0
})

const PageData = reactive({
  list: [],
  total: 0
})

const paginationData = reactive({
  pageNum: 1,
  pageSize: 10
})
</script>

<style scoped>
.common-layout {
  position:absolute;
  top:0;
  right:0;
  bottom:0;
  left:0;
}
.el-container {
  height: 100%;
}
.el-row {
  margin-bottom: 20px;
}

.el-row:last-child {
  margin-bottom: 0;
}

.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.el-menu-main{
  background: linear-gradient(to right,lightblue,white);
}
.el-menu--horizontal > .el-menu-item:nth-child(6) {
  margin-right: auto;
}
.border {
  width: 49px;
  height: 20px;
  margin-top: 0px;
  margin-left: 0px;
  border: 2px solid red;
  border-radius: 20px;
}
.border-title {
  width: 50px;
  height: 30px;
  text-align: center;
  margin-top: 0px;
  margin-left: 0px;
  color:red;
  font-weight: bold;
  font-size: 13px;
}
.title-tag{
  font-weight: bold;
}
.user-name {
  margin-left: 2px;
  font-weight: bold;
}
.el-dropdown-link {
  cursor: pointer;
  display: flex;
  align-items: center;
}
.el-dropdown-link:focus{
  outline: none;
}
</style>
