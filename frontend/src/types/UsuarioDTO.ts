export interface User {
  id?: number
  nome: string
  email: string
  ativo: boolean
  cursoId?: number 
  cursoDTO?: string // nome do curso (string mesmo)
}
