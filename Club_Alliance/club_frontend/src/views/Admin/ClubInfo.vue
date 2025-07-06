<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <el-menu
          default-active="5"
          class="el-menu-main"
          mode="horizontal"
          :ellipsis="false"
          @select="handleTop"
        >
          <el-menu-item index="0">
            <el-icon>
              <UserFilled/>
            </el-icon>
            <el-text class="title-tag" type="primary" size="large">社团联盟后台管理</el-text>
          </el-menu-item>
          <el-menu-item index="1">首页</el-menu-item>
          <el-sub-menu index="2">
            <template #title>我的社团</template>
            <el-menu-item index="2-1">item one</el-menu-item>
            <el-menu-item index="2-2">item two</el-menu-item>
            <el-menu-item index="2-3">item three</el-menu-item>
          </el-sub-menu>
          <el-menu-item index="3">我的活动</el-menu-item>
          <el-menu-item index="4">个人中心</el-menu-item>
          <el-menu-item index="5">后台管理</el-menu-item>
          <el-space wrap :size="'large'">
            <el-image
              style="width: 40px; height: 40px; border-radius:40px; box-shadow:3px 3px 10px 0px dimgray"
              src="/avatar.jpg"></el-image>
            <div class="border">
              <div class="border-title">管理员</div>
            </div>
          </el-space>
          <el-dropdown>
            <span class="el-dropdown-link">
              <el-text class="user-name" size="large">荷塘月色</el-text>
              <el-icon class="el-icon--right">
                <arrow-down-bold/>
              </el-icon>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </el-menu>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <el-row class="tac">
            <el-col :span="23">
              <h5 class="mb-2"></h5>
              <el-menu
                class="el-menu-vertical"
                @select="handleSide"
                default-active="/manageClub/info"
                router="true"
              >
                <el-menu-item index="/dashboard">
                  <el-icon>
                    <Menu/>
                  </el-icon>
                  <span>控制台</span>
                </el-menu-item>
                <el-sub-menu>
                  <template #title>
                    <el-icon>
                      <location/>
                    </el-icon>
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
                    <el-icon>
                      <Menu/>
                    </el-icon>
                    <span>用户管理</span>
                  </template>
                  <el-menu-item index="/manageUser/admin">管理员信息</el-menu-item>
                  <el-menu-item index="/manageUser/student">学生信息</el-menu-item>
                </el-sub-menu>
                <el-menu-item index="3">
                  <el-icon>
                    <Menu/>
                  </el-icon>
                  <span>公告管理</span>
                </el-menu-item>
                <el-menu-item index="4">
                  <el-icon>
                    <Menu/>
                  </el-icon>
                  <span>系统设置</span>
                </el-menu-item>
              </el-menu>
            </el-col>
          </el-row>
        </el-aside>
        <el-main>
          <el-button type="primary" style="margin-top:40px;margin-left: 40px" plain
                     @click="addDialog=true">
            <el-icon size="18">
              <DocumentAdd/>
            </el-icon>
            <el-text type="primary">新增</el-text>
          </el-button>
          <el-button type="danger" style="margin-top: 40px" plain @click="deleteMultiple">
            <el-icon size="18">
              <FolderDelete/>
            </el-icon>
            <el-text type="danger">批量删除</el-text>
          </el-button>
          <el-dialog v-model="addDialog" title="新增社团" width="800">
            <el-form :model="form">
              <el-form-item label="社团名称" :label-width="70">
                <el-input v-model="form.clubName" placeholder="输入社团名称"/>
              </el-form-item>
              <el-form-item label="社团图片" :label-width="70">
                <el-upload v-model="uploadPic" action="http://localhost:8080/uploadPic">
                  <el-button type="primary">
                    上传
                    <el-icon size="18">
                      <Upload/>
                    </el-icon>
                  </el-button>
                </el-upload>
              </el-form-item>
              <el-form-item label="社团分类" :label-width="70">
                <el-select v-model="form.category" placeholder="输入社团分类" filterable>
                  <el-option label="ACADEMIC" value="ACADEMIC"/>
                  <el-option label="ARTS" value="ARTS"/>
                  <el-option label="SPORTS" value="SPORTS"/>
                  <el-option label="VOLUNTEER" value="VOLUNTEER"/>
                  <el-option label="HOBBY" value="HOBBY"/>
                  <el-option label="CAREER" value="CAREER"/>
                  <el-option label="DEBATE" value="DEBATE"/>
                  <el-option label="MAKER" value="MAKER"/>
                  <el-option label="OTHER" value="OTHER"/>
                </el-select>
              </el-form-item>
              <el-form-item label="负责人" :label-width="70">
                <el-input v-model="form.presidentId" placeholder="输入负责人ID">
                  <template #append>
                    <el-button :icon="Search" @click="searchLeader()">查询</el-button>
                  </template>
                </el-input>
              </el-form-item>
              <el-form-item label="加入条件" :label-width="70">
                <el-input v-model="form.requirements" placeholder="输入加入条件"/>
              </el-form-item>
              <el-form-item label="创建时间" :label-width="70">
                <el-input v-model="form.createdAt" disabled/>
              </el-form-item>
              <el-form-item label="社团介绍" :label-width="70">
                <el-input type="textarea" v-model="form.description" resize="none"
                          placeholder="输入社团介绍" :rows="4"/>
              </el-form-item>
            </el-form>
            <template #footer>
              <el-button type="primary" @click="submitCreate()">确认</el-button>
              <el-button @click="clearForm()">取消</el-button>
            </template>
          </el-dialog>
          <el-dialog v-model="editDialog" title="编辑社团" width="800">
            <el-form :model="editForm">
              <el-form-item label="社团名称" :label-width="70">
                <el-input v-model="editForm.clubName" placeholder="输入社团名称"/>
              </el-form-item>
              <el-form-item label="社团图片" :label-width="70">
                <el-upload v-model="editPic" action="http://localhost:8080/editPic">
                  <el-button type="primary">
                    上传
                    <el-icon size="18">
                      <Upload/>
                    </el-icon>
                  </el-button>
                </el-upload>
              </el-form-item>
              <el-form-item label="社团分类" :label-width="70">
                <el-select v-model="editForm.category" placeholder="输入社团分类" filterable>
                  <el-option label="ACADEMIC" value="ACADEMIC"/>
                  <el-option label="ARTS" value="ARTS"/>
                  <el-option label="SPORTS" value="SPORTS"/>
                  <el-option label="VOLUNTEER" value="VOLUNTEER"/>
                  <el-option label="HOBBY" value="HOBBY"/>
                  <el-option label="CAREER" value="CAREER"/>
                  <el-option label="DEBATE" value="DEBATE"/>
                  <el-option label="MAKER" value="MAKER"/>
                  <el-option label="OTHER" value="OTHER"/>
                </el-select>
              </el-form-item>
              <el-form-item label="负责人" :label-width="70">
                <el-input v-model="editForm.presidentId" placeholder="输入负责人ID">
                  <template #append>
                    <el-button :icon="Search" @click="editLeader()">查询</el-button>
                  </template>
                </el-input>
              </el-form-item>
              <el-form-item label="加入条件" :label-width="70">
                <el-input v-model="editForm.requirements" placeholder="输入加入条件"/>
              </el-form-item>
              <el-form-item label="创建时间" :label-width="70">
                <el-input v-model="editForm.createdAt" disabled/>
              </el-form-item>
              <el-form-item label="社团介绍" :label-width="70">
                <el-input type="textarea" v-model="editForm.description" resize="none"
                          placeholder="输入社团介绍" :rows="4"/>
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
            @change="searchClub"
            clearable
          />
          <el-button type="success" style="margin-top: 40px;margin-left: 10px" plain
                     @click="searchClub">
            <el-icon size="18">
              <Search/>
            </el-icon>
            <el-text type="success">搜索</el-text>
          </el-button>
          <el-table :data="PageData.list"
                    style="margin-top:30px;margin-left: 40px;width: fit-content"
                    @selection-change="handleSelection">
            <el-table-column type="selection" width="30"></el-table-column>
            <el-table-column property="clubId" label="ID" width="120" header-align="center"
                             align="center" sortable/>
            <el-table-column property="clubName" label="社团名称" width="120" header-align="center"
                             align="center" sortable/>
            <el-table-column property="category" label="分类" width="120" header-align="center"
                             align="center" sortable/>
            <el-table-column property="currentMembers" label="成员数" width="120"
                             header-align="center" align="center" sortable/>
            <el-table-column property="presidentId" label="负责人ID" width="120"
                             header-align="center" align="center" sortable/>
            <el-table-column property="president" label="负责人" width="120" header-align="center"
                             align="center" sortable/>
            <el-table-column property="createdAt" label="创建时间" width="120" header-align="center"
                             align="center" show-overflow-tooltip sortable/>
            <el-table-column property="requirements" label="加入条件" width="120"
                             header-align="center" align="center"/>
            <el-table-column v-slot="scope" width="100">
              <el-button type="warning" plain @click="iniEdit(scope.row)">
                <el-icon size="18">
                  <Edit/>
                </el-icon>
                <el-text type="warning">编辑</el-text>
              </el-button>
            </el-table-column>
            <el-table-column v-slot="scope" width="100">
              <el-button type="danger" plain @click="deleteSingle(scope.row)">
                <el-icon size="18">
                  <Delete/>
                </el-icon>
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
import {reactive} from "vue"
import axios from 'axios'
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting, UserFilled, Menu, Delete, Search, Refresh,
} from '@element-plus/icons-vue'
import {ElMessage, ElMessageBox, paginationProps} from 'element-plus'

export default {
  computed: {
    Search() {
      return Search
    }
  },
  components: {Refresh, Delete, Menu, Location, UserFilled},
  data() {
    return {
      userData: [],
      iniTable: [],
      inputSearch: '',
      PageData,
      paginationData,
      clubData,
      addDialog: false,
      editDialog: false,
      selection: [],
      form: {
        clubName: '',
        category: '',
        presidentId: undefined,
        requirements: '',
        createdAt: '',
        description: '',
        presidenttemp: undefined
      },
      editForm: {
        clubName: '',
        category: '',
        presidentId: undefined,
        requirements: '',
        createdAt: '',
        description: '',
        presidenttemp: undefined
      }
    }
  },
  methods: {
    handleCurrentChange(newPage: number) {
      paginationData.pageNum = newPage
      this.updateTableData()
    },
    deleteSingle(row) {
      axios.delete("http://localhost:8080/deleteClub/" + row.clubId).then(res => {
        if (res.data == false) {
          ElMessage.error("删除失败")
          return
        }
        axios.get("http://localhost:8080/allClub").then(res => {
          this.clubData.list = res.data;
          this.clubData.total = res.data.length;
          this.updateTableData()
          ElMessage.success("删除成功")
        })
      })
    },
    searchLeader() {
      if (this.form.presidentId == null)
        return
      axios.get("http://localhost:8080/searchLeader/" + this.form.presidentId).then((res) => {
        if (res.data != "") {
          this.form.presidenttemp = this.form.presidentId
          this.form.presidentId = res.data
        } else ElMessage.error("查询失败")
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
    updateTableData() {
      const start = (paginationData.pageNum - 1) * paginationData.pageSize
      const end = start + paginationData.pageSize
      PageData.list = clubData.list.slice(start, end)
      PageData.total = clubData.total
    },
    clearForm() {
      this.addDialog = false
      this.form.clubName = ''
      this.form.category = ''
      this.form.presidentId = undefined
      this.form.requirements = ''
      this.form.description = ''
    },
    submitCreate() {
      this.addDialog = false
      this.form.createdAt = null
      this.form.presidentId = this.form.presidenttemp
      axios.post("http://localhost:8080/createClub", this.form).then(res => {
        if (res.data == true) {
          this.clearForm()
          axios.get("http://localhost:8080/allClub").then(res => {
            this.clubData.list = res.data
            this.clubData.total = res.data.length
            this.updateTableData()
            ElMessage.success("添加成功")
          })
        } else {
          ElMessage.error("添加失败")
        }
      })
    },
    deleteMultiple() {
      if (this.selection.length == 0) {
        ElMessage.warning("未选中社团")
        return
      }
      ElMessageBox.confirm(
        '确定要删除选中的社团吗?',
        '警告',
        {
          confirmButtonText: '确认',
          cancelButtonText: '取消',
          type: 'warning',
        }
      )
        .then(() => {
          for (let i = 0; i < this.selection.length; i++) {
            axios.delete("http://localhost:8080/deleteClub/" + this.selection[i].clubId).then(res => {
              if (res.data == false)
                ElMessage.error("删除失败")
              if (i == this.selection.length - 1) {
                axios.get("http://localhost:8080/allClub").then(res => {
                  this.clubData.list = res.data;
                  this.clubData.total = res.data.length;
                  this.updateTableData()
                  ElMessage.success("删除成功")
                })
              }
            })
          }
        })
    },
    handleSelection(val) {
      this.selection = val
    },
    iniEdit(row) {
      this.editForm = row
      this.editDialog = true
      this.editLeader()
    },
    editLeader() {
      axios.get("http://localhost:8080/searchLeader/" + this.editForm.presidentId).then((res) => {
        if (res.data != "") {
          this.editForm.presidenttemp = this.editForm.presidentId
          this.editForm.presidentId = res.data
          axios.get("http://localhost:8080/allClub").then((res) => {
            this.clubData.list = res.data
            this.clubData.total = res.data.length
            this.updateTableData()
          })
        } else ElMessage.error("查询失败")
      })
    },
    submitEdit() {
      this.editDialog = false
      this.editForm.presidentId = this.editForm.presidenttemp
      axios.put("http://localhost:8080/editClub", this.editForm).then(res => {
        if (res.data == true) {
          axios.get("http://localhost:8080/allClub").then(res => {
            this.clubData.list = res.data
            this.clubData.total = res.data.length
            this.updateTableData()
            ElMessage.success("编辑成功")
          })
        } else {
          ElMessage.error("编辑失败")
        }
      })
    },
    searchClub() {
      if (this.inputSearch == "") {
        this.resetSearch()
        return
      }
      axios.get("http://localhost:8080/searchClub/" + this.inputSearch).then((res) => {
        this.clubData.list = res.data
        this.clubData.total = res.data.length
        this.updateTableData()
      })
    },
    resetSearch() {
      axios.get("http://localhost:8080/allClub").then((res) => {
        this.clubData.list = res.data
        this.clubData.total = res.data.length
        this.updateTableData()
      })
    }
  },
  mounted() {
    axios.get("http://localhost:8080/allClub").then((res) => {
      this.clubData.list = res.data
      this.clubData.total = res.data.length
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
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
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

.el-menu-main {
  background: linear-gradient(to right, lightblue, white);
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
  color: red;
  font-weight: bold;
  font-size: 13px;
}

.title-tag {
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

.el-dropdown-link:focus {
  outline: none;
}
</style>
