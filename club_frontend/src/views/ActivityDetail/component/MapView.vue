<template>
  <div>
    <div id="container" class="map"></div>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted, watch } from 'vue';
import AMapLoader from '@amap/amap-jsapi-loader';
import axios from 'axios';

const props = defineProps({
  location: String
});

let map = null;
let amap = null;

const initMap = () => {
  AMapLoader.load({
    key: 'a5ccaa53d9b0ff4781628973c94f907e',
    version: '2.0',
    plugins: ['AMap.Geocoder'],
  })
    .then(AMap => {
      amap = AMap;
      map = new AMap.Map('container', {
        zoom: 15,
        center: [114.36524, 30.53681], // Default center
        resizeEnable: true
      });
      if (props.location) {
        geocodeAddress(props.location);
      }
    })
    .catch(e => {
      console.error(e);
    });
};

const geocodeAddress = async (address) => {
  if (!address) return;
  const fullAddress = `湖北省武汉市武汉大学${address}`;
  const url = `https://restapi.amap.com/v3/geocode/geo?key=a5ccaa53d9b0ff4781628973c94f907e&address=${encodeURIComponent(fullAddress)}&city=武汉`;

  try {
    const response = await axios.get(url);
    if (response.data.status === '1' && response.data.geocodes.length > 0) {
      const location = response.data.geocodes[0].location;
      const [lng, lat] = location.split(',');
      const position = new amap.LngLat(lng, lat);
      map.setCenter(position);
      new amap.Marker({
        position: position,
        map: map
      });
    } else {
      console.error('Geocoding failed:', response.data.info);
    }
  } catch (error) {
    console.error('Error during geocoding:', error);
  }
};

watch(() => props.location, (newLocation) => {
  if (map && amap) {
    geocodeAddress(newLocation);
  }
});



onMounted(() => {
  initMap();
});

onUnmounted(() => {
  map?.destroy();
});
</script>

<style scoped>
.map {
  width: 600px;
  height: 600px;
  margin: 0 auto;
}
</style>
