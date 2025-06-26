<!-- src/components/EventLists.vue -->
<template>
  <section id="events" class="py-16 bg-white">
    <div class="container mx-auto px-4">
      <div class="flex flex-col md:flex-row justify-between items-start md:items-center mb-12">
        <div>
          <h2 class="text-[clamp(1.5rem,3vw,2.5rem)] font-bold text-dark mb-4">最新活动</h2>
          <p class="text-gray-600 max-w-2xl">参与社团活动，拓展人脉，丰富大学生活</p>
        </div>
        <div class="mt-4 md:mt-0 flex space-x-2">
          <button class="bg-light text-gray-600 px-4 py-2 rounded-lg text-sm font-medium hover:bg-gray-200 transition-custom">
            全部
          </button>
          <button class="bg-primary text-white px-4 py-2 rounded-lg text-sm font-medium hover:bg-primary/90 transition-custom">
            近期
          </button>
        </div>
      </div>

      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
        <!-- 活动卡片 1 -->
        <div class="bg-white rounded-xl shadow-card overflow-hidden hover:shadow-hover transition-custom">
          <div class="relative h-48">
            <img src="https://picsum.photos/id/1047/800/600" alt="科技创新大赛活动照片" class="w-full h-full object-cover">
            <div class="absolute bottom-0 left-0 right-0 bg-gradient-to-t from-black/70 to-transparent p-4">
              <div class="text-white">
                <div class="text-sm font-medium">6月25日 14:00-17:00</div>
                <div class="font-semibold">科技创新大赛</div>
              </div>
            </div>
          </div>
          <div class="p-6">
            <div class="flex items-center mb-4">
              <img src="https://picsum.photos/id/1005/200/200" alt="科技创新社logo" class="w-10 h-10 rounded-full object-cover">
              <div class="ml-3">
                <div class="font-medium">科技创新社</div>
                <div class="text-gray-500 text-xs">发布于 2天前</div>
              </div>
            </div>
            <p class="text-gray-600 text-sm mb-4">
              面向全校学生的科技创新大赛，旨在激发学生创新思维，提高实践能力，促进科技交流。
            </p>
            <div class="flex items-center justify-between">
              <div class="flex items-center text-sm text-gray-500">
                <div class="flex items-center mr-4">
                  <i class="fa fa-map-marker mr-1"></i>
                  <span>科技楼 301 会议室</span>
                </div>
              </div>
              <button class="bg-primary text-white px-4 py-2 rounded-lg text-sm font-medium hover:bg-primary/90 transition-custom">
                报名参加
              </button>
            </div>
          </div>
        </div>

        <!-- 活动卡片 2 -->
        <div class="bg-white rounded-xl shadow-card overflow-hidden hover:shadow-hover transition-custom">
          <div class="relative h-48">
            <img src="https://picsum.photos/id/1058/800/600" alt="校园音乐会活动照片" class="w-full h-full object-cover">
            <div class="absolute bottom-0 left-0 right-0 bg-gradient-to-t from-black/70 to-transparent p-4">
              <div class="text-white">
                <div class="text-sm font-medium">6月28日 19:30-21:30</div>
                <div class="font-semibold">校园音乐会</div>
              </div>
            </div>
          </div>
          <div class="p-6">
            <div class="flex items-center mb-4">
              <img src="https://picsum.photos/id/1012/200/200" alt="音乐爱好者协会logo" class="w-10 h-10 rounded-full object-cover">
              <div class="ml-3">
                <div class="font-medium">音乐爱好者协会</div>
                <div class="text-gray-500 text-xs">发布于 3天前</div>
              </div>
            </div>
            <p class="text-gray-600 text-sm mb-4">
              一场精彩的校园音乐会，邀请校内外优秀音乐人演出，为你带来视听盛宴。
            </p>
            <div class="flex items-center justify-between">
              <div class="flex items-center text-sm text-gray-500">
                <div class="flex items-center mr-4">
                  <i class="fa fa-map-marker mr-1"></i>
                  <span>礼堂</span>
                </div>
              </div>
              <button class="bg-primary text-white px-4 py-2 rounded-lg text-sm font-medium hover:bg-primary/90 transition-custom">
                报名参加
              </button>
            </div>
          </div>
        </div>

        <!-- 活动卡片 3 -->
        <div class="bg-white rounded-xl shadow-card overflow-hidden hover:shadow-hover transition-custom">
          <div class="relative h-48">
            <img src="https://picsum.photos/id/1061/800/600" alt="户外登山活动照片" class="w-full h-full object-cover">
            <div class="absolute bottom-0 left-0 right-0 bg-gradient-to-t from-black/70 to-transparent p-4">
              <div class="text-white">
                <div class="text-sm font-medium">7月2日 8:00-17:00</div>
                <div class="font-semibold">户外登山活动</div>
              </div>
            </div>
          </div>
          <div class="p-6">
            <div class="flex items-center mb-4">
              <img src="https://picsum.photos/id/1006/200/200" alt="户外运动俱乐部logo" class="w-10 h-10 rounded-full object-cover">
              <div class="ml-3">
                <div class="font-medium">户外运动俱乐部</div>
                <div class="text-gray-500 text-xs">发布于 5天前</div>
              </div>
            </div>
            <p class="text-gray-600 text-sm mb-4">
              亲近自然，挑战自我，锻炼身体，增强团队协作能力的户外活动。
            </p>
            <div class="flex items-center justify-between">
              <div class="flex items-center text-sm text-gray-500">
                <div class="flex items-center mr-4">
                  <i class="fa fa-map-marker mr-1"></i>
                  <span>西山森林公园</span>
                </div>
              </div>
              <button class="bg-primary text-white px-4 py-2 rounded-lg text-sm font-medium hover:bg-primary/90 transition-custom">
                报名参加
              </button>
            </div>
          </div>
        </div>
      </div>

      <div class="text-center mt-12">
        <button class="bg-white border border-primary text-primary px-6 py-3 rounded-full font-medium hover:bg-primary hover:text-white transition-custom">
          查看更多活动 <i class="fa fa-arrow-right ml-1"></i>
        </button>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: 'EventLists'
}
</script>

<style scoped>
/* 可以添加组件特定的样式 */
.transition-custom {
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}
.shadow-card {
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
}
.hover\:shadow-hover:hover {
  box-shadow: 0 8px 30px rgba(0, 0, 0, 0.12);
}
</style>
