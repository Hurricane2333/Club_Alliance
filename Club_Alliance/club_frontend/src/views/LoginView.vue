<template>
  <div class="login-container">
    <div class="login-form">
      <h2>登录</h2>
      <form @submit.prevent="login">
        <div class="form-group">
          <label for="stu_id">学号</label>
          <input type="text" id="stu_id" v-model="stu_id" required>
        </div>
        <div class="form-group">
          <label for="password">密码</label>
          <input type="password" id="password" v-model="password" required>
        </div>
        <button type="submit">登录</button>
        <p v-if="error" class="error-message">{{ error }}</p>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';


const stu_id = ref('');
const password = ref('');
const error = ref('');
const router = useRouter();


const login = async () => {
  error.value = ''; // Reset error message
  console.log('Attempting to log in with stu_id:', stu_id.value);
  try {
    const response = await axios.post('/api/user/login', {
      stu_id: stu_id.value,
      password: password.value
    });
    console.log('Login response received:', response);
    const result = response.data;
    console.log('Response data:', result);
    if (result.code === '200' && result.data && result.data.user && result.data.user.user_id) {
      const userId = result.data.user.user_id;
      console.log('Login successful, user ID:', userId);
      localStorage.setItem('token', result.data.token);
      localStorage.setItem('user', JSON.stringify(result.data.user));
      router.push(`/user/${userId}`);
    } else {
      console.error('Login failed with response:', result);
      error.value = result.msg || '学号或密码错误';
    }
  } catch (err) {
    console.error('An error occurred during login:', err);
    if (err.response) {
      console.error('Error response data:', err.response.data);
      console.error('Error response status:', err.response.status);
      console.error('Error response headers:', err.response.headers);
    } else if (err.request) {
      console.error('Error request:', err.request);
    } else {
      console.error('Error message:', err.message);
    }
    error.value = '登录失败，请检查网络或联系管理员';
  }
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f4f4f4;
}
.login-form {
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