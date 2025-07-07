import {fileURLToPath, URL} from 'node:url'
import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'

export default defineConfig({
    plugins: [vue(), vueDevTools()],
    resolve: {
      alias: {
        '@': fileURLToPath(new URL('./src', import.meta.url))
      }
    },
    server: {
      proxy: {
        '/api': {
          target: 'http://localhost:8080',
          changeOrigin: true,
          rewrite: path => path.replace(/^\/api/, '')
        },
        '/club_member': {
          target: 'http://localhost:8080', // 后端服务地址
          changeOrigin: true,
        },
        '/picgo': {  // 使用新的前缀
          target: 'https://www.picgo.net',
          changeOrigin: true,
          rewrite: (path) => path.replace(/^\/picgo/, ''),
          secure: false,  // 如果目标使用 HTTPS 但证书有问题
        }
      }
    },
  }
)
