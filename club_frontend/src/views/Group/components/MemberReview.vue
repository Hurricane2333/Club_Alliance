<template>
  <div class="member-review-container">
    <el-table :data="pendingMembers.data" style="width: 100%">
      <el-table-column prop="userName" label="申请人"/>
      <el-table-column prop="applyTime" label="申请时间"/>
      <el-table-column prop="applyReason" label="申请理由"/>
      <el-table-column label="状态/操作">
        <template #default="scope">
          <template v-if="scope.row.status === 'PENDING'">
            <el-button type="success" @click="approveMember(scope.row)">通过</el-button>
            <el-button type="danger" @click="rejectMember(scope.row)">拒绝</el-button>
          </template>
          <el-button v-else-if="scope.row.status === 'APPROVED'" type="success" disabled>已通过
          </el-button>
          <el-button v-else type="danger" disabled>已拒绝</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup>
import {inject, reactive, watchEffect} from 'vue'
import request from '@/utils/request.js'
import {ElMessage, ElMessageBox} from "element-plus";
import {useRoute} from "vue-router";
import {getUserName} from "@/utils/userCache.js";

const route = useRoute()

const clubData = inject('clubData', {})

const pendingMembers = reactive({
  data: [],
})

const loadApplications = async () => {
  try {
    const res = await request.get(`/group/member/selectClubId/${clubData.clubId}`)

    for (const member of res) {
      member.userName = await getUserName(member.userId);
    }

    pendingMembers.data = res.sort((a, b) => {

      if (a.status === 'PENDING' && b.status !== 'PENDING') return -1;
      if (b.status === 'PENDING' && a.status !== 'PENDING') return 1;

      return new Date(b.applyTime) - new Date(a.applyTime);
    })
  } catch (error) {
    console.error('加载申请列表失败:', error)
  }
}

// 监听路由参数变化
watchEffect(() => {
  if (route.params.id) {
    // 初始化加载
    watchEffect(() => {
      if (clubData.clubId) {
        loadApplications()
      }
    })
  }
})

const approveMember = async (member) => {
  try {
    await ElMessageBox.confirm('确定通过该成员的申请吗？', '确认操作', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })

    await request.put('/group/member/update', {
      relationId: member.relationId,
      status: 'APPROVED',
    })

    ElMessage.success('已通过申请')
    loadApplications()
  } catch (error) {
    if(error !== 'cancel') {
      ElMessage.error(error.response?.data?.message || '操作失败')
    }
  }
}

const rejectMember = async (member) => {
  try {
    const { value } = await ElMessageBox.prompt('请输入拒绝理由', '拒绝申请', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      inputPattern: /\S+/,
      inputErrorMessage: '必须输入拒绝理由'
    })

    await request.put('/group/member/update', {
      relationId: member.relationId,
      status: 'REJECTED',
      rejectReason: value,
    })

    ElMessage.success('已拒绝申请')
    loadApplications()
  } catch (error) {
    if(error !== 'cancel') {
      ElMessage.error(error.response?.data?.message || '操作失败')
    }
  }
}

// 初始化加载
watchEffect(() => {
  if (clubData.clubId) {
    loadApplications()
  }
})
</script>

<style scoped>
.member-review-container {
  padding: 20px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}
</style>
