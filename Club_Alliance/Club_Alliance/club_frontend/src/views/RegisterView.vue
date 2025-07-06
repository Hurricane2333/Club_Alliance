<template>
  <div class="register-container">
    <div class="register-form">
      <h2>注册</h2>
      <form @submit.prevent="handleRegister">
        <div class="form-group">
          <label for="stuId">学号</label>
          <input type="text" id="stuId" v-model="stuId" required>
        </div>
        <div class="form-group">
          <label for="stuName">姓名</label>
          <input type="text" id="stuName" v-model="stuName" required>
        </div>
        <div class="form-group">
          <label for="email">邮箱</label>
          <input type="email" id="email" v-model="email" required>
        </div>
        <div class="form-group">
          <label for="password">密码</label>
          <input type="password" id="password" v-model="password" required>
        </div>
        <div class="form-group">
          <label for="confirmPassword">确认密码</label>
          <input type="password" id="confirmPassword" v-model="confirmPassword" required>
        </div>
        <button type="submit">注册</button>
        <p v-if="error" class="error-message">{{ error }}</p>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import request from '@/utils/request';
import { ElMessage } from 'element-plus'; // 新增

const stuId = ref('');
const stuName = ref('');
const email = ref('');
const password = ref('');
const confirmPassword = ref('');
const error = ref('');
const router = useRouter();

const handleRegister = async () => {
  if (password.value !== confirmPassword.value) {
    error.value = '两次输入的密码不一致';
    return;
  }
  error.value = '';
  try {
    const res = await request.post('/user/register', {
      stuId: stuId.value,
      stuName: stuName.value,
      email: email.value,
      password: password.value,
    });
    // 这里判断后端返回的 code
    if (res.code === 'success') {
      router.push('/login');
    } else {
      if (res.msg === '学号已存在') {
        ElMessage.error(res.msg);
      } else {
        error.value = res.msg || '注册失败';
      }
    }
  } catch (err) {
    const msg = (err.response && err.response.data && err.response.data.msg) || '注册失败';
    error.value = msg;
    console.error('注册失败:', err);
  }
};
</script>

<style scoped>
.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f4f4f4;
}
.register-form {
  padding: 2rem;
  background: white;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
}
.form-group {
  margin-bottom: 1rem;
}
label {
  display: block;
  margin-bottom: 0.5rem;
}
input {
  width: 100%;
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
}
button {
  width: 100%;
  padding: 0.75rem;
  border: none;
  background-color: #007bff;
  color: white;
  border-radius: 4px;
  cursor: pointer;
}
button:hover {
  background-color: #0056b3;
}
.error-message {
  color: red;
  margin-top: 1rem;
  text-align: center;
}
</style>
