import axios from 'axios'
import type { UsuarioDTO } from '@/types/UsuarioDTO'

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
})

export const listarUsuarios = async (): Promise<UsuarioDTO[]> => {
  const res = await api.get('/usuarios')
  return res.data
}
/*
export const criarUsuario = async (usuario: UsuarioDTO): Promise<UsuarioDTO> => {
  const res = await api.post('/usuarios', usuario)
  return res.data
}

export const atualizarUsuario = async (id: number, usuario: UsuarioDTO): Promise<UsuarioDTO> => {
  const res = await api.put(`/usuarios/${id}`, usuario)
  return res.data
}

export const excluirUsuario = async (id: number): Promise<void> => {
  await api.delete(`/usuarios/${id}`)
}
*/