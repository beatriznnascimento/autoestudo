import api from './api'
import type { Curso } from '@/types/CursoDTO'

export const getAllCourses = async (): Promise<Curso[]> => {
  const response = await api.get('/cursos')
  return response.data
}
