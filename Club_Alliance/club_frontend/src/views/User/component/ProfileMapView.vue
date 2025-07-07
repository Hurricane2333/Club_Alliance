<template>
  <div>
    <div id="profile-map-container" class="map"></div>
  </div>
</template>

<script setup>
import { onMounted, onUnmounted, defineProps, watch } from 'vue';
import AMapLoader from '@amap/amap-jsapi-loader';
import axios from 'axios';

const props = defineProps({
  activities: {
    type: Array,
    default: () => []
  }
});

let map = null;
let amap = null;
let markers = [];

const initMap = () => {
  AMapLoader.load({
    key: 'f93df160e22b00bb8295ef13ab5b4cf5',
    version: '2.0',
    plugins: ['AMap.Geocoder', 'AMap.InfoWindow'],
  })
    .then(AMap => {
      amap = AMap;
      map = new AMap.Map('profile-map-container', {
        zoom: 15,
        center: [114.36524, 30.53681], // Default center
        resizeEnable: true
      });
      addMarkersToMap(props.activities);
    })
    .catch(e => {
      console.error(e);
    });
};

const geocodeAddress = async (address) => {
  if (!address) return null;
  const fullAddress = `湖北省武汉市武汉大学${address}`;
  const url = `https://restapi.amap.com/v3/geocode/geo?key=f93df160e22b00bb8295ef13ab5b4cf5&address=${encodeURIComponent(fullAddress)}&city=武汉`;

  try {
    const response = await axios.get(url);
    if (response.data.status === '1' && response.data.geocodes.length > 0) {
      const location = response.data.geocodes[0].location;
      const [lng, lat] = location.split(',');
      return new amap.LngLat(lng, lat);
    } else {
      console.error('Geocoding failed:', response.data.info);
      return null;
    }
  } catch (error) {
    console.error('Error during geocoding:', error);
    return null;
  }
};

const addMarkersToMap = async (activities) => {
  // Clear existing markers
  if (markers.length > 0) {
    map.remove(markers);
    markers = [];
  }

  if (!activities || activities.length === 0) return;

  for (const activity of activities) {
    if (activity.location) {
      const position = await geocodeAddress(activity.location);
      if (position) {
                const marker = new amap.Marker({
          position: position,
          map: map,
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

        marker.on('mouseover', () => {
          infoWindow.open(map, marker.getPosition());
        });

        marker.on('mouseout', () => {
          infoWindow.close();
        });

        markers.push(marker);
      }
    }
  }

  if (markers.length > 0) {
    map.setFitView(); // Adjust map to fit all markers
  }
};

watch(() => props.activities, (newActivities) => {
  if (map && amap) {
    addMarkersToMap(newActivities);
  }
}, { deep: true, immediate: true });

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
