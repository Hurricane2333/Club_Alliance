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
                <el-dropdown-item>Action 1</el-dropdown-item>
                <el-dropdown-item>Action 2</el-dropdown-item>
                <el-dropdown-item>Action 3</el-dropdown-item>
                <el-dropdown-item disabled>Action 4</el-dropdown-item>
                <el-dropdown-item divided>Action 5</el-dropdown-item>
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
                default-active="/dashboard"
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
                <el-sub-menu>
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
          <el-row :gutter="20" type="flex" justify="center">
            <el-text type="primary" size="large">您好，管理员，欢迎使用本系统！</el-text>
          </el-row>
          <div class="titles">基本信息</div>
          <el-space wrap :size="75">
            <el-card class="box-card" style="width: 300px">
              <template #header>
                <span>社团总数</span>
              </template>
              <div class="card-text">{{ iniTable[1] }}</div>
            </el-card>
            <el-card class="box-card" style="width: 300px">
              <template #header>
                <span>用户总数</span>
              </template>
              <div class="card-text">{{ iniTable[0] }}</div>
            </el-card>
            <el-card class="box-card" style="width: 300px">
              <template #header>
                <span>活动总数</span>
              </template>
              <div class="card-text">100</div>
            </el-card>
            <el-card class="box-card" style="width: 300px">
              <template #header>
                <span>待审核</span>
              </template>
              <div class="card-text">100</div>
            </el-card>
          </el-space>
          <div class="titles">最近创建的社团</div>
          <div v-if="clubData.length < 4">
            <el-space wrap :size="75">
              <el-card v-for="item in clubData" class="box-card" style="width: 300px">
                <template #header>
                  <div class="card-text">{{ item.clubName }}</div>
                </template>
                <span>创建时间：{{ item.createdAt }}</span>
              </el-card>
            </el-space>
          </div>
          <div v-else>
            <el-space wrap :size="75">
              <el-card v-for="i in 4" :key="i" class="box-card" style="width: 300px">
                <template #header>
                  <div class="card-text">{{ clubData[i - 1].clubName }}</div>
                </template>
                <span>创建时间：{{ clubData[i - 1].createdAt }}</span>
              </el-card>
            </el-space>
            <el-space wrap :size="75">
              <el-card v-for="i in clubData.length-4" :key="i" class="box-card"
                       style="width: 300px">
                <template #header>
                  <div class="card-text">{{ clubData[i + 3].clubName }}</div>
                </template>
                <span>创建时间：{{ clubData[i + 3].createdAt }}</span>
              </el-card>
            </el-space>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script lang="ts">
import ClubInfo from "@/views/Admin/ClubInfo.vue";
import axios from 'axios'
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting, UserFilled, Menu,
} from '@element-plus/icons-vue'
import NavBar from "@/views/Home/component/NavBar.vue";

export default {
  components: {NavBar, Menu, Location, UserFilled, ClubInfo},
  data() {
    return {
      userData: [],
      clubData: [],
      iniTable: [],
    }
  },
  methods: {},
  mounted() {
    axios.get("http://localhost:8080/initial").then((res) => {
      console.log(res);
      this.iniTable = res.data;
    }),
      axios.get("http://localhost:8080/recentClub").then((res) => {
        console.log(res);
        this.clubData = res.data;
      })
  }
}

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

.titles {
  font-weight: bold;
  font-size: 25px;
  margin-top: 20px;
}

.card-text {
  font-weight: bold;
  font-size: 30px;
}

.box-card {
  margin-top: 20px;
}

.box-card:hover {
  box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.3);
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
