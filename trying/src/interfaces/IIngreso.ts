import type ICommunity from "./ICommunity"
import type ITipoIngreso from "./ITipoIngreso"

export default interface IIngreso {
    id: number,
    monto: number
    fecha: Date,
    tipoIngreso: ITipoIngreso,
    comunidad: ICommunity
}