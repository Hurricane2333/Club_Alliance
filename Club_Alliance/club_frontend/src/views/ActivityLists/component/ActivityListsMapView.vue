<template>
  <div class="map-wrapper">
    <div id="activity-lists-map-container" class="map" style="width:800px;height:600px"></div>
  </div>
</template>

<script setup>
import { onMounted, onUnmounted, defineProps, watch } from 'vue';
import AMapLoader from '@amap/amap-jsapi-loader';
import axios from 'axios';

let map = null;
let amap = null;
let markers = [];

const props = defineProps({
  activities: {
    type: Array,
    default: () => []
  }
});

const initMap = () => {
  AMapLoader.load({
    key: 'a5ccaa53d9b0ff4781628973c94f907e',
    version: '2.0',
    plugins: ['AMap.Geocoder', 'AMap.InfoWindow'],
  })
    .then(AMap => {
      amap = AMap;
      map = new AMap.Map('activity-lists-map-container', {
        showLabel: true,  // 确保地图标注可见
        zoom: 15,
        center: [114.36524, 30.53681],
        resizeEnable: true
      });
      // 调试标记数据
console.log('Received activities:', props.activities);
if(props.activities.length > 0) {
  addMarkersToMap(props.activities);
} else {
  console.warn('No activities data available');
}
    })
    .catch(e => {
      console.error(e);
    });
};

// 地理编码和标记逻辑
const geocodeAddress = async (address) => {
  const fullAddress = `湖北省武汉市武汉大学${address}`;
  const url = `https://restapi.amap.com/v3/geocode/geo?key=a5ccaa53d9b0ff4781628973c94f907e&address=${encodeURIComponent(fullAddress)}&city=武汉`;

  try {
    const response = await axios.get(url);
    if (response.data.status === '1' && response.data.geocodes.length > 0) {
      const location = response.data.geocodes[0].location;
      return new amap.LngLat(...location.split(','));
    }
  } catch (error) {
    console.error('地理编码错误:', error);
  }
  return null;
};

const addMarkersToMap = async (activities) => {
  if (markers.length > 0) {
    map.remove(markers);
    markers = [];
  }

  for (const activity of activities) {
    if (activity.location && activity.location.trim()) {
      const position = await geocodeAddress(activity.location);
      if (position) {
        const marker = new amap.Marker({
          position,
          map,
          title: activity.title
        });

        const infoWindow = new amap.InfoWindow({
          content: `<div>
                      <h4>${activity.clubName}</h4>
                      <p>活动: ${activity.title}</p>
                      <p>地点: ${activity.location}</p>
                    </div>`,
          offset: new amap.Pixel(0, -30)
        });

        marker.on('mouseover', () => infoWindow.open(map, position));
        marker.on('mouseout', () => infoWindow.close());
        markers.push(marker);
      }
    }
  }

  if (markers.length > 0) {
    map.setFitView();
  }
};

watch(() => props.activities, (newVal) => {
  if (map && amap) addMarkersToMap(newVal);
}, { deep: true });

onMounted(() => {
  initMap();
});

onUnmounted(() => {
  map?.destroy();
});
</script>

<style scoped>
.map-wrapper {
  width: 600px;
  height: 600px;
  margin: 20px auto;
}
.map {
  width: 100%;
  height: 100%;
}
</style>
