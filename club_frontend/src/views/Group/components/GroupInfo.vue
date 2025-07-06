<template>
  <div>
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
          <!-- 只有未加入时显示按钮 -->
          <el-button
            v-if="!hasJoined"
            type="primary"
            style="font-size: 20px; margin: 40px 0 0 40px"
            @click="dialogVisible = true"
          >
            申请加入
          </el-button>

          <el-button
            v-else
            type="success"
            style="font-size: 20px; margin: 40px 0 0 40px"
            @click="router.push(`/groupHome/${club.clubId}`)"
          >
            社团主页
          </el-button>
          <el-button
            type="warning"
            style="font-size: 20px; margin: 40px 0 0 20px"
            @click="handleFavorite"
          >
            {{ isFavorited ? '已收藏' : '添加收藏' }}
          </el-button>
          <el-dialog v-model="dialogVisible" title="申请加入社团" width="400px">
            <el-form label-width="120px">
              <el-form-item label="申请理由">
                <el-input type="textarea" v-model="applyReason" placeholder="请输入申请理由" />
              </el-form-item>
            </el-form>
            <template #footer>
              <el-button @click="dialogVisible = false">取消</el-button>
              <el-button type="primary" @click="submitApplication" :loading="loading">提交申请</el-button>
            </template>
          </el-dialog>
        </div>
        <div style="margin-top: 40px">
          <el-text truncated style="font-size: 20px; font-weight: 500; width: min(100px,20%); margin-left:10px; margin-right: 20px">
            <el-icon color="#006bb5">
              <Avatar/>
            </el-icon>
            {{ presidentName }}
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
  </div>
</template>

<script setup>

import {reactive} from "vue";
import {useRoute} from "vue-router";
import {Avatar, StarFilled, UserFilled} from "@element-plus/icons-vue";
import request from "@/utils/request.js";
import { ref } from "vue";
import { useUserStore } from "@/stores/user";
import { ElMessage } from "element-plus";
import router from "@/router/index.js";
import {getUserName} from "../../../utils/userCache.js";


const dialogVisible = ref(false);
const applyReason = ref('');
const loading = ref(false);

const userStore = useUserStore();
const route = useRoute();

const isFavorited = ref(false);

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

request.get('/group/selectId/' + group.id).then(async res => {
  Object.assign(club, res);
  presidentName.value = await getUserName(club.presidentId);
})

const presidentName = ref('加载中...')

const submitApplication = async () => {
  if (!applyReason.value.trim()) {
    ElMessage.warning("请填写申请理由");
    return;
  }
  loading.value = true;
  try {
    await request.post('/club_member/apply', {
      userId: userStore.user.userId,
      clubId: club.clubId,
      applyReason: applyReason.value
    });
    ElMessage.success("申请已提交，等待审核");
    dialogVisible.value = false;
    applyReason.value = '';
  } catch  {
    ElMessage.error("提交失败，请重试");
  } finally {
    loading.value = false;
  }
};

const hasJoined = ref(false); // 新增：是否已加入
// 查询社团信息
request.get('/group/selectId/' + group.id).then(res => {
  Object.assign(club, res);
  // 查询当前用户是否已加入该社团
  if (userStore.user && club.clubId) {
    request.get(`/group/check?userId=${userStore.user.userId}&clubId=${club.clubId}`)
      .then(res => {
        console.log('check接口返回数据:', res);
        hasJoined.value = res && res.joined; // 后端返回 { joined: true/false }
      });
  }
});

const handleFavorite = async () => {
    if (!userStore.user?.userId) {
        ElMessage.warning('请先登录');
        return;
    }

    try {
        const res = await request.post('/favorite/add', null, {
            params: {
                userId: userStore.user.userId,
                clubId: club.clubId
            }
        });

        if (res.code === 'success') {
            ElMessage.success(res.msg);
            isFavorited.value = true;
            club.favoriteCount += 1;
        } else {
            ElMessage.warning(res.msg);
        }
    } catch  {
        ElMessage.error('操作失败');
    }
};

// 初始化时检查收藏状态
request.get('/favorite/list', {
    params: { userId: userStore.user?.userId }
}).then(res => {
    isFavorited.value = res.data?.some(c => c.clubId === club.clubId);
});

</script>

<style>

</style>
