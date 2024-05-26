import type ICommunity from "./ICommunity"
import type ITipoGasto from "./ITipoGasto"

export default interface IGasto {
    id: number,
    monto: number
    fecha: Date,
    tipoGasto: ITipoGasto,
    comunidad: ICommunity
}