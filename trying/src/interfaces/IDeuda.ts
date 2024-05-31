import type ICommunity from "./ICommunity";
import type ITipoDeuda from "./ITipoDeuda";

export default interface IDeuda {
    id: number,
    tipoDeuda: ITipoDeuda,
    comunidad: ICommunity,
    fecha: Date,
    monto: number
}