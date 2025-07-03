<template>
  <div style="display: flex; flex-direction: row">
    <div
        style="width: 200px; height: 200px; margin: 0 15px 0 max(50px,10%); align-content: center; justify-content: center">
      <el-avatar :size="150" :src="club.icon"/>
    </div>
    <div style="width: 500px; height: 200px; margin: 0 max(50px,10%) 0 15px">
      <div style="display: flex">
        <el-text truncated style="font-size: 40px; font-weight: 600; color: black; margin-top: 25px">
          {{ club.clubName }}
        </el-text>
        <el-button type="primary" style="font-size: 20px; margin: 40px 0 0 40px">
          申请加入
        </el-button>
      </div>
      <div style="margin-top: 40px">
        <el-text truncated style="font-size: 20px; font-weight: 500; width: min(100px,20%); margin-left:10px">
          <el-icon color="#006bb5">
            <Avatar/>
          </el-icon>
          {{ club.presidentId }}
        </el-text>
        <el-text truncated style="font-size: 20px; font-weight: 500; width: min(100px,20%); margin-left:10px">
          <el-icon color="#4eb3f4">
            <UserFilled/>
          </el-icon>
          {{ club.currentMembers }}
        </el-text>
        <el-text truncated style="font-size: 20px; font-weight: 500; width: min(100px,20%); margin-left:10px">
          <el-icon color="#edda05">
            <star-filled/>
          </el-icon>
          {{ club.favoriteCount }}
        </el-text>
      </div>
    </div>
  </div>
  <div style="border-top: 1px solid #e0e0e0; margin: 10px 50px 10px 50px"/>
  <div style="max-width: 1000px; padding: 0 50px 0 50px">
    <el-text style="white-space: pre-wrap; font-size: 19px; color: black">
      {{ club.description }}
    </el-text>
  </div>
  <router-view/>
</template>

<script setup>

import {reactive} from "vue";
import {useRoute} from "vue-router";
import {Avatar, StarFilled, UserFilled} from "@element-plus/icons-vue";
import request from "@/utils/request.js";

const route = useRoute();

const group = reactive({
  id: route.params.id,
})

const club = reactive({
  clubId: 0,
  clubName: '',
  description: '',
  category: '',
  icon: '',
  presidentId: 0,
  requirements: '',
  favoriteCount: 0,
  currentMembers: 0,
  createdAt: '0',
})

request.get(`/group/selectId/${route.params.id}`).then(res => {
  Object.assign(club, res)
})

</script>

<style>

</style>
