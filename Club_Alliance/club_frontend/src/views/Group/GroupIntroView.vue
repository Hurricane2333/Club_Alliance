<template>

  <div>
    <NavBar />
  </div>

  <div class="group-home">

    <div class="group-container">
      <div class="group-info">
        <router-view/>
      </div>

      <div class="group-post">
        <GroupPostCard v-for="item in post.info" :key="item.post_id" :post="item"/>
      </div>
    </div>

  </div>
</template>

<script setup>

import {reactive} from "vue";
import {useRoute} from "vue-router";
import GroupPostCard from "@/views/Group/components/GroupPostCard.vue";
import request from "@/utils/request.js";
import NavBar from "@/views/Home/component/NavBar.vue";

const route = useRoute();

const post = reactive({
  info: [],
})

request.get(`/group/post/selectClubId/${route.params.id}`).then(res => {
  post.info = res
})

</script>

<style>

.group-home {
  display: flex;
  flex-direction: column;
  background-color: #f5f5f5;
}

.group-container {
  margin: auto;
  width: 70%;
  min-width: 700px;
  max-width: 1200px;
  padding: 1px;
}

.group-info {
  display: flex;
  flex-direction: column;
  background-color: white;
  max-width: 100%;
  padding: 50px max(80px, 10%);
  margin: 50px auto;
  justify-content: center;
}

.group-post {
  display: flex;
  background-color: white;
  max-width: 100%;
  padding: 20px 20px;
  margin: 50px auto;
}

</style>
