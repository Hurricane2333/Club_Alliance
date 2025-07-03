import { defineStore } from 'pinia';

export const useUserStore = defineStore('user', {
  // state: () => ({
  //   user: null,
  //   token: localStorage.getItem('token') || '',
  // }),
  state: () => {
    const userItem = localStorage.getItem('user');
    const user = userItem && userItem !== 'undefined' ? JSON.parse(userItem) : null;

    const token = localStorage.getItem('token') || '';

    const isPresidentItem = localStorage.getItem('isPresident');
    const isPresident = isPresidentItem && isPresidentItem !== 'undefined' ? JSON.parse(isPresidentItem) : false;

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
      this.isPresident = !!isPresident;
      this.clubName = clubName || '';
      this.clubId = clubId || null;

      if (user && token) {
        localStorage.setItem('user', JSON.stringify(user));
        localStorage.setItem('token', token);
        localStorage.setItem('isPresident', JSON.stringify(!!isPresident));

        if (clubName) {
          localStorage.setItem('clubName', clubName);
        } else {
          localStorage.removeItem('clubName');
        }

        if (clubId) {
          localStorage.setItem('clubId', clubId);
        } else {
          localStorage.removeItem('clubId');
        }
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
