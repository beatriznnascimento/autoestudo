export interface UsuarioDTO {
  id: number
  nome: string
  email: string
  ativo: boolean
  cursoId: number | null
  curso: string | null
}
