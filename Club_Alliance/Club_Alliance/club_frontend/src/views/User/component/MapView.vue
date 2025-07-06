<template>
  <div id="container" style="width:600px; height:600px;"></div>
</template>

<script>
export default {
  name: 'MapView',
  data() {
    return {
      map: null
    };
  },
  mounted() {
    this.initMap();
  },
  methods: {
    initMap() {
      const wuhanUniversityCoords = [114.36524, 30.53681]; // 武汉大学坐标
      this.map = new AMap.Map('container', {
        zoom: 15, // 调高缩放级别以便更清楚地看到校园
        center: wuhanUniversityCoords,
        resizeEnable: true
      });


    }
  }
};
</script>

<style scoped>
#container {
  margin-top: 20px;
}
</style>
