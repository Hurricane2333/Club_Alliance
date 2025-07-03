import { defineStore } from 'pinia';

export const useUserStore = defineStore('user', {
  // state: () => ({
  //   user: null,
  //   token: localStorage.getItem('token') || '',
  // }),
  state: () => {
    const user = JSON.parse(localStorage.getItem('user') || 'null');
    const token = localStorage.getItem('token') || '';
    const isPresident = JSON.parse(localStorage.getItem('isPresident') || 'false');
    const clubName = localStorage.getItem('clubName') || '';
    const clubId = localStorage.getItem('clubId') || null;
    return {
      user,
      token,
      isPresident,
      clubName,
      clubId,
    };
  },
  actions: {
    setAuthData({ user, token, isPresident, clubName, clubId }) {
      this.user = user;
      this.token = token;
      this.isPresident = isPresident;
      this.clubName = clubName;
      this.clubId = clubId;

      if (user && token) {
        localStorage.setItem('user', JSON.stringify(user));
        localStorage.setItem('token', token);
        localStorage.setItem('isPresident', JSON.stringify(isPresident));
        if (clubName) localStorage.setItem('clubName', clubName);
        if (clubId) localStorage.setItem('clubId', clubId);
      } else {
        this.logout();
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
      this.isPresident = false;
      this.clubName = '';
      this.clubId = null;
      localStorage.removeItem('user');
      localStorage.removeItem('token');
      localStorage.removeItem('isPresident');
      localStorage.removeItem('clubName');
      localStorage.removeItem('clubId');
    },
    loadUserFromStorage() {
      const user = localStorage.getItem('user');
      if (user) {
        this.user = JSON.parse(user);
      }
    }
  },
});
