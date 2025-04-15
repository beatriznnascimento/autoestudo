<template>
    <v-dialog v-model="dialog" persistent max-width="600">
      <v-card>
        <v-toolbar color="primary" dark>
          <v-toolbar-title>
            {{ form.id ? 'Editar Aluno' : 'Novo Aluno' }}
          </v-toolbar-title>
        </v-toolbar>
  
        <v-card-text>
          <v-form ref="formRef" v-model="isValid" lazy-validation>
            <v-text-field
              label="Nome"
              v-model="form.nome"
              :rules="[rules.required]"
              required
            />
  
            <v-text-field
              label="Email"
              v-model="form.email"
              :rules="[rules.required, rules.email]"
              required
            />
  
            <v-checkbox
              v-model="form.ativo"
              label="Aluno Ativo?"
            />
  
          </v-form>
        </v-card-text>
  
        <v-card-actions class="justify-end">
          <v-btn color="grey" @click="fechar">Cancelar</v-btn>
          <v-btn color="primary" :disabled="!isValid" @click="salvar">Salvar</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </template>
  
  <script setup lang="ts">
  import { ref, watch, onMounted } from 'vue'
  import { createUser, updateUser } from '@/service/usuarioService'
  import { getAllCourses } from '@/service/cursoService'
  import type { User } from '@/types/UsuarioDTO'
  import type { Curso } from '@/types/CursoDTO'
  
  const props = defineProps<{
    editedUser: User | null
  }>()
  
  const emit = defineEmits(['close', 'saved'])
  
  const dialog = ref(true)
  const isValid = ref(false)
  const formRef = ref()
  const cursos = ref<Curso[]>([])
  
  const form = ref<User & { cursoId?: Curso | number }>({
    nome: '',
    email: '',
    ativo: true,
    cursoId: undefined
  })
  
  const rules = {
    required: (v: string) => !!v || 'Campo obrigatório',
    email: (v: string) => /.+@.+\..+/.test(v) || 'Email inválido',
  }
  
  watch(
    () => props.editedUser,
    (val) => {
      if (val) {
        form.value = { ...val }
      }
    },
    { immediate: true }
  )
  
  const fechar = () => emit('close')
  
  const salvar = async () => {
    const payload: User = {
      nome: form.value.nome,
      email: form.value.email,
      ativo: form.value.ativo,
      cursoId:
        typeof form.value.cursoId === 'object'
          ? (form.value.cursoId as Curso).id
          : form.value.cursoId ?? undefined,
    }
  
    if (form.value.id) {
      await updateUser(form.value.id, payload)
    } else {
      await createUser(payload)
    }
  
    emit('saved')
    fechar()
  }
  
  onMounted(async () => {
    cursos.value = await getAllCourses()
  })
  </script>
  