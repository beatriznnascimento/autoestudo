// src/router/index.ts
import { createRouter, createWebHistory } from 'vue-router'
import UsuarioListView from '@/views/UsuarioListView.vue'

const routes = [
  {
    path: '/',
    name: 'usuarios',
    component: UsuarioListView
  }
]

export default createRouter({
  history: createWebHistory(),
  routes
})
