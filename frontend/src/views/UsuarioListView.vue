<template>
  <v-container>
    <v-card class="pa-4">
      <v-card-title class="text-h5">Usuários Cadastrados</v-card-title>

      <v-data-table
        :headers="headers"
        :items="usuarios"
        :loading="carregando"
        loading-text="Carregando usuários..."
        class="mt-4"
      >
        <template #item.ativo="{ item }">
          <v-chip :color="item.ativo ? 'green' : 'red'" dark>
            {{ item.ativo ? 'Ativo' : 'Inativo' }}
          </v-chip>
        </template>

        <template #item.curso="{ item }">
          {{ item.curso || '—' }}
        </template>
      </v-data-table>
    </v-card>
  </v-container>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { listarUsuarios } from '@/service/usuarioService'
import type { UsuarioDTO } from '@/types/UsuarioDTO'

const usuarios = ref<UsuarioDTO[]>([])
const carregando = ref(true)

const headers = [
  { text: 'Nome', value: 'nome' },
  { text: 'Email', value: 'email' },
  { text: 'Status', value: 'ativo' },
  { text: 'Curso', value: 'curso' }
]

onMounted(async () => {
  try {
    usuarios.value = await listarUsuarios()
  } finally {
    carregando.value = false
  }
})
</script>
