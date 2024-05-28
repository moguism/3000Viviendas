import type IGasto from "./IGasto"

export default interface ITipoGasto {
    id: number
    nombre: string,
    gastos: Array<IGasto>
}