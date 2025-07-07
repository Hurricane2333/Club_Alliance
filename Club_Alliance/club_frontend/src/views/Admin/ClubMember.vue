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
                default-active="/manageClub/member"
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
          <span type="primary" style="font-size: 20px;position: relative;top: 20px;left: 30px">「 请选择一个社团 」</span>
          <el-input
            v-model="inputSearch"
            style="width: 240px;margin-top:40px;margin-left:50px"
            placeholder="输入名称查询"
            @change="searchClub"
            clearable
          />
          <el-button type="success" style="margin-top: 40px;margin-left: 10px" plain @click="searchClub">
            <el-icon size="18"><Search /></el-icon>
            <el-text type="success">搜索</el-text>
          </el-button>
          <el-table :data="PageData.list"style="margin-top:30px;margin-left: 40px;width: fit-content" @selection-change="handleSelection">
            <el-table-column property="clubId" label="ID" width="120" header-align="center" align="center" sortable/>
            <el-table-column property="clubName" label="社团名称" width="120" header-align="center" align="center" sortable/>
            <el-table-column property="category" label="分类" width="120" header-align="center" align="center" sortable/>
            <el-table-column property="currentMembers" label="成员数" width="120" header-align="center" align="center" sortable/>
            <el-table-column property="presidentId" label="负责人ID" width="120" header-align="center" align="center" sortable/>
            <el-table-column property="president" label="负责人" width="120" header-align="center" align="center" sortable/>
            <el-table-column property="createdAt" label="创建时间" width="120" header-align="center" align="center" show-overflow-tooltip sortable/>
            <el-table-column property="requirements" label="加入条件" width="120" header-align="center" align="center" show-overflow-tooltip/>
            <el-table-column v-slot="scope" width="110">
              <el-button type="primary" plain @click="clubSelected(scope.row)">
                <el-icon size="18"><Select /></el-icon>
                <el-text type="primary">选择</el-text>
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
  Setting, UserFilled, Menu, Delete, Search, Refresh, Select,
} from '@element-plus/icons-vue'
import {ElMessage, ElMessageBox, paginationProps} from 'element-plus'
import NavBar from "@/views/Home/component/NavBar.vue";

export default{
  computed: {
    Search() {
      return Search
    }
  },
  components: {NavBar, Select, Refresh, Delete, Menu, Location, UserFilled},
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
      selection:[],
      form:{
        clubName: '',
        category: '',
        presidentId :undefined,
        requirements:'',
        createdAt:'',
        description:'',
        presidenttemp:undefined
      },
      editForm:{
        clubName: '',
        category: '',
        presidentId :undefined,
        requirements:'',
        createdAt:'',
        description:'',
        presidenttemp:undefined
      },
      member:{
        userId:undefined,
        stuId:undefined,
        stuName:''
      }
    }
  },
  methods:{
    handleCurrentChange(newPage: number){
      paginationData.pageNum = newPage
      this.updateTableData()
    },
    getNowTime() {
      var date = new Date();
      var time = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate() + ' ' + date.getHours() + ':' + this.addZero(date.getMinutes()) + ':' + date.getSeconds();
      this.form.createdAt = time
    },
    addZero(s) {
      return s < 10 ? ('0' + s) : s;
    },
    updateTableData(){
      const start = (paginationData.pageNum - 1) * paginationData.pageSize
      const end = start + paginationData.pageSize
      PageData.list = clubData.list.slice(start, end)
      PageData.total = clubData.total
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
      axios.get("http://localhost:8080/searchClub/"+this.inputSearch).then((res) => {
        this.clubData.list=res.data
        this.clubData.total=res.data.length
        this.updateTableData()
      })
    },
    resetSearch(){
      axios.get("http://localhost:8080/allClub").then((res)=>{
        this.clubData.list=res.data
        this.clubData.total=res.data.length
        this.updateTableData()
      })
    },
    clubSelected(row){
      this.$router.push({
        path:`/manageClub/member/${row.clubId}`
      })
    }
  },
  mounted(){
    axios.get("http://localhost:8080/allClub").then((res)=>{
      this.clubData.list=res.data
      this.clubData.total=res.data.length
      this.updateTableData()
    })
    this.getNowTime()
    clearInterval(myTimeDisplay)
    var myTimeDisplay = setInterval(() => {
      this.getNowTime();
    }, 1000)
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
