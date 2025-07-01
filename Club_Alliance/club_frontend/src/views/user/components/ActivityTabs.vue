<template>
  <el-tabs v-model="localActiveTab" @tab-click="handleTabClick" class="activity-tabs">
    <el-tab-pane label="我的动态" :name="0"></el-tab-pane>
    <el-tab-pane label="参与的活动" :name="1"></el-tab-pane>
    <el-tab-pane label="发表的评论" :name="2"></el-tab-pane>
  </el-tabs>
</template>

<script setup>
import { ref, watch, defineProps, defineEmits } from 'vue';

const props = defineProps({
  activeTab: {
    type: Number,
    default: 0
  }
});

const emit = defineEmits(['tab-change']);

const localActiveTab = ref(props.activeTab);

watch(() => props.activeTab, (newVal) => {
  localActiveTab.value = newVal;
});

const handleTabClick = (tab) => {
  emit('tab-change', Number(tab.props.name));
};
</script>

<style scoped>
.activity-tabs {
  margin-bottom: 24px;
}
</style>