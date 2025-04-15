<template>
  <v-container>
    <v-card class="pa-4" color="black" dark>
      <v-toolbar color="primary" flat>
        <v-toolbar-title>Lista de Alunos</v-toolbar-title>
      </v-toolbar>

      <v-btn class="my-4" color="grey" @click="openForm()">+ Novo Aluno</v-btn>

      <v-card
        v-for="user in users"
        :key="user.id"
        class="mb-4 pa-4"
        color="grey darken-3"
      >
        <div class="text-h6">{{ user.nome }}</div>
        <div>Status: {{ user.ativo ? 'ATIVO' : 'INATIVO' }}</div>
        <div>Email: {{ user.email }}</div>
        <div>Curso: {{ user.cursoDTO ?? 'Nenhum' }}</div>



        <v-btn color="grey" class="mt-2 mr-2" @click="openForm(user)">Editar</v-btn>
        <v-btn
          color="red"
          class="mt-2"
          v-if="!user.ativo"
          @click="deleteUser(user.id!)"
        >
          Excluir
        </v-btn>
      </v-card>
    </v-card>

    <UsuarioForm
      :editedUser="selectedUser"
      v-if="showForm"
      @close="closeForm"
      @saved="loadUsers"
    />

  </v-container>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { getAllUsers, deleteUserById } from '@/service/usuarioService'
import UsuarioForm from '../components/UsuarioForm.vue'
import type { User } from '@/types/UsuarioDTO'

const users = ref<User[]>([])
const showForm = ref(false)
const selectedUser = ref<User | null>(null)

const loadUsers = async () => {
  users.value = await getAllUsers()
}

const openForm = (user: User | null = null) => {
  selectedUser.value = user
  showForm.value = true
}

const closeForm = () => {
  selectedUser.value = null
  showForm.value = false
  loadUsers()
}

const deleteUser = async (id: number) => {
  await deleteUserById(id)
  loadUsers()
}

onMounted(loadUsers)
</script>
