import { createApp } from 'vue'
import { createPinia } from 'pinia'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import store from './store/index.js'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import zhCn from 'element-plus/es/locale/lang/zh-cn'

zhCn.el.pagination.total = '共 {total} 条'
zhCn.el.pagination.goto = '跳至'
const app = createApp(App)
app.use(createPinia()).use(router).use(ElementPlus, { locale: zhCn }).use(store).mount('#app')
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
