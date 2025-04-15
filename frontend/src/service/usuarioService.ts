import api from './api'
import type { User } from '@/types/UsuarioDTO'

export const getAllUsers = async (): Promise<User[]> => {
  const res = await api.get('/usuarios')
  return res.data
}

export const createUser = async (user: User) => {
  return await api.post('/usuarios', user)
}

export const updateUser = async (id: number, user: User) => {
  return await api.put(`/usuarios/${id}`, user)
}

export const deleteUserById = async (id: number) => {
  return await api.delete(`/usuarios/${id}`)
}
