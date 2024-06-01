import type ICommunity from "./ICommunity";
import type IReunion from "./IReunion";
import type IVivienda from "./IVivienda";

export default interface IBloque {
    id: number,
    numero: Number,
    comunidad: ICommunity,
    viviendas: Array<IVivienda>,
    reuniones: Array<IReunion>
}