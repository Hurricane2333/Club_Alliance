<template>
  <div class="home-container">
    <NavBar />
    <div class="main-content">
      <div class="club-lists-view">

        <ClubList />
      </div>
    </div>
  </div>
</template>

<script setup>
import ClubList from './component/ClubList.vue'
import NavBar from '../Home/component/NavBar.vue'
</script>

<style scoped>
.title {
  font-size: 2rem;
  font-weight: bold;
  margin-bottom: 24px;
}
.club-lists-view {
  padding: 32px;
}
.home-container {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background: linear-gradient(rgba(238, 241, 242, 0.9), rgba(231, 247, 250, 0.9)),
              url('@/assets/homeback.jpg') center/cover no-repeat;
  position: relative;
}
.background-overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(231, 235, 236, 0.6);
  z-index: 1;
}
</style>
