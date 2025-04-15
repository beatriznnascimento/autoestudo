import api from './api'
import type { User } from '@/types/UsuarioDTO'

export const getAllUsers = async (): Promise<User[]> => {
  const res = await api.get('/usuarios')
  return res.data
}

export const createUser = async (data: any) => {
  return await api.post('/usuarios', data)
}

export const updateUser = async (id: number, data: any) => {
  return await api.put(`/usuarios/${id}`, data)
}


export const deleteUserById = async (id: number) => {
  return await api.delete(`/usuarios/${id}`)
}
