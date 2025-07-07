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
                default-active="/manageUser/admin"
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
          <el-button type="primary" style="margin-top:40px;margin-left: 40px" plain @click="addDialog=true">
            <el-icon size="18"><DocumentAdd /></el-icon>
            <el-text type="primary">新增</el-text>
          </el-button>
          <el-button type="danger" style="margin-top: 40px" plain @click="deleteMultiple">
            <el-icon size="18"><FolderDelete /></el-icon>
            <el-text type="danger">批量删除</el-text>
          </el-button>
          <el-dialog v-model="addDialog" title="新增管理员" width="800">
            <el-form :model="form">
              <el-form-item label="用户名" :label-width="70">
                <el-input v-model="form.stuName" placeholder="输入用户名"/>
              </el-form-item>
              <el-form-item label="学号" :label-width="70">
                <el-input v-model="form.stuId" placeholder="输入学号"/>
              </el-form-item>
              <el-form-item label="头像" :label-width="70">
                <el-upload v-model="uploadPic" action="http://localhost:8080/uploadPic">
                  <el-button type="primary">
                    上传<el-icon size="18"><Upload /></el-icon>
                  </el-button>
                </el-upload>
              </el-form-item>
              <el-form-item label="密码" :label-width="70">
                <el-input v-model="form.password" placeholder="输入密码" show-password />
              </el-form-item>
              <el-form-item label="邮箱" :label-width="70">
                <el-input v-model="form.email" placeholder="输入邮箱" />
              </el-form-item>
              <el-form-item label="创建时间" :label-width="70">
                <el-input v-model="form.createdAt" disabled />
              </el-form-item>
            </el-form>
            <template #footer>
              <el-button type="primary" @click="submitCreate()">确认</el-button>
              <el-button @click="clearForm()">取消</el-button>
            </template>
          </el-dialog>
          <el-dialog v-model="editDialog" title="编辑管理员" width="800">
            <el-form :model="editForm">
              <el-form-item label="用户名" :label-width="70">
                <el-input v-model="editForm.stuName" placeholder="输入用户名"/>
              </el-form-item>
              <el-form-item label="学号" :label-width="70">
                <el-input v-model="editForm.stuId" placeholder="输入学号"/>
              </el-form-item>
              <el-form-item label="头像" :label-width="70">
                <el-upload v-model="uploadPic" action="http://localhost:8080/uploadPic">
                  <el-button type="primary">
                    上传<el-icon size="18"><Upload /></el-icon>
                  </el-button>
                </el-upload>
              </el-form-item>
              <el-form-item label="密码" :label-width="70">
                <el-input v-model="editForm.password" placeholder="输入密码" show-password />
              </el-form-item>
              <el-form-item label="邮箱" :label-width="70">
                <el-input v-model="editForm.email" placeholder="输入邮箱" />
              </el-form-item>
              <el-form-item label="创建时间" :label-width="70">
                <el-input v-model="editForm.createdAt" disabled />
              </el-form-item>
            </el-form>
            <template #footer>
              <el-button type="primary" @click="submitEdit()">确认</el-button>
              <el-button @click="editDialog=false">取消</el-button>
            </template>
          </el-dialog>
          <el-input
            v-model="inputSearch"
            style="width: 240px;margin-top:40px;margin-left:30px"
            placeholder="输入名称查询"
            @change="searchUser"
            clearable
          />
          <el-button type="success" style="margin-top: 40px;margin-left: 10px" plain @click="searchUser">
            <el-icon size="18"><Search /></el-icon>
            <el-text type="success">搜索</el-text>
          </el-button>
          <el-table :data="PageData.list"style="margin-top:30px;margin-left: 40px;width: fit-content" @selection-change="handleSelection">
            <el-table-column type="selection" width="30"></el-table-column>
            <el-table-column property="userId" label="ID" width="100" header-align="center" align="center" sortable/>
            <el-table-column property="stuId" label="学号" width="200" header-align="center" align="center"/>
            <el-table-column property="stuName" label="用户名" width="200" header-align="center" align="center" sortable/>
            <el-table-column property="joinedClub" label="加入的社团" width="200" header-align="center" align="center" sortable/>
            <el-table-column property="email" label="邮箱" width="200" header-align="center" align="center"/>
            <el-table-column property="createdAt" label="创建时间" width="200" header-align="center" align="center" show-overflow-tooltip sortable/>
            <el-table-column v-slot="scope" width="110">
              <el-button type="warning" plain @click="iniEdit(scope.row)">
                <el-icon size="18"><Edit /></el-icon>
                <el-text type="warning">编辑</el-text>
              </el-button>
            </el-table-column>
            <el-table-column v-slot="scope" width="110">
              <el-button type="danger" plain @click="deleteSingle(scope.row)">
                <el-icon size="18"><Delete /></el-icon>
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
  Setting, UserFilled, Menu, Delete, Search, Refresh,
} from '@element-plus/icons-vue'
import {ElMessage, ElMessageBox, paginationProps} from 'element-plus'
import NavBar from "@/views/Home/component/NavBar.vue";

export default{
  computed: {
    Search() {
      return Search
    }
  },
  components: {NavBar, Refresh, Delete, Menu, Location, UserFilled},
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
        stuId:undefined,
        stuName:'',
        password:'',
        email:'',
        createdAt:'',
        isAdmin:1
      },
      editForm:{
        stuId:undefined,
        stuName:'',
        password:'',
        email:'',
        createdAt:'',
        isAdmin:1
      }
    }
  },
  methods:{
    handleCurrentChange(newPage: number){
      paginationData.pageNum = newPage
      this.updateTableData()
    },
    deleteSingle(row){
      axios.delete("http://localhost:8080/deleteUser/"+row.userId).then(res => {
        if(res.data==false) {
          ElMessage.error("删除失败")
          return
        }
        axios.get("http://localhost:8080/allAdmin").then(res => {
          this.clubData.list=res.data;
          this.clubData.total=res.data.length;
          this.updateTableData()
          ElMessage.success("删除成功")
        })
      })
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
    clearForm(){
      this.addDialog = false
      this.form.stuId=undefined
      this.form.stuName=''
      this.form.password=''
      this.form.email=''
      this.form.isAdmin=1
    },
    submitCreate(){
      this.addDialog = false
      this.form.createdAt=null
      axios.post("http://localhost:8080/createUser",this.form).then(res => {
        if(res.data==true)
        {
          this.clearForm()
          axios.get("http://localhost:8080/allAdmin").then(res => {
            this.clubData.list=res.data
            this.clubData.total=res.data.length
            this.updateTableData()
            ElMessage.success("添加成功")
          })
        }
        else{
          ElMessage.error("添加失败")
        }
      })
    },
    deleteMultiple(){
      if(this.selection.length==0)
      {
        ElMessage.warning("未选中用户")
        return
      }
      ElMessageBox.confirm(
        '确定要删除选中的用户吗?',
        '警告',
        {
          confirmButtonText: '确认',
          cancelButtonText: '取消',
          type: 'warning',
        }
      )
        .then(() => {
          for(let i=0;i<this.selection.length;i++){
            axios.delete("http://localhost:8080/deleteUser/"+this.selection[i].userId).then(res => {
              if(res.data==false)
                ElMessage.error("删除失败")
              if(i==this.selection.length-1){
                axios.get("http://localhost:8080/allAdmin").then(res => {
                  this.clubData.list=res.data;
                  this.clubData.total=res.data.length;
                  this.updateTableData()
                  ElMessage.success("删除成功")
                })
              }
            })
          }
        })
    },
    handleSelection(val){
      this.selection=val
    },
    iniEdit(row){
      this.editForm=row
      this.editDialog = true
    },
    submitEdit(){
      this.editDialog = false
      axios.put("http://localhost:8080/editAdmin",this.editForm).then(res => {
        if(res.data==true)
        {
          axios.get("http://localhost:8080/allAdmin").then(res => {
            this.clubData.list=res.data
            this.clubData.total=res.data.length
            this.updateTableData()
            ElMessage.success("编辑成功")
          })
        }
        else{
          ElMessage.error("编辑失败")
        }
      })
    },
    searchUser(){
      if(this.inputSearch=="")
      {
        this.resetSearch()
        return
      }
      axios.get("http://localhost:8080/searchAdmin/"+this.inputSearch).then((res) => {
        this.clubData.list=res.data
        this.clubData.total=res.data.length
        this.updateTableData()
      })
    },
    resetSearch(){
      axios.get("http://localhost:8080/allAdmin").then((res)=>{
        this.clubData.list=res.data
        this.clubData.total=res.data.length
        this.updateTableData()
      })
    }
  },
  mounted(){
    axios.get("http://localhost:8080/allAdmin").then((res)=>{
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
