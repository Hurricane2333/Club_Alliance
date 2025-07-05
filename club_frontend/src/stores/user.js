import { defineStore } from 'pinia';

export const useUserStore = defineStore('user', {
  // state: () => ({
  //   user: null,
  //   token: localStorage.getItem('token') || '',
  // }),
  state: () => {
    const user = JSON.parse(localStorage.getItem('user') || 'null');
    const token = localStorage.getItem('token') || '';
    return {
      user,
      token,
    };
  },
  actions: {
    setUser(userData) {
      this.user = userData;
      if (userData) {
        localStorage.setItem('user', JSON.stringify(userData));
      } else {
        localStorage.removeItem('user');
      }
    },
    setToken(token) {
      this.token = token;
      if (token) {
        localStorage.setItem('token', token);
      } else {
        localStorage.removeItem('token');
      }
    },
    logout() {
      this.user = null;
      this.token = '';
      localStorage.removeItem('user');
      localStorage.removeItem('token');
    },
    loadUserFromStorage() {
      const user = localStorage.getItem('user');
      if (user) {
        this.user = JSON.parse(user);
      }
    }
  },
});
